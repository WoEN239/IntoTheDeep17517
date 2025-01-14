package org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.SampleSensor.ColorSensorListener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
 Writing by EgorKhvostikov
*/

public class IntakeStateMachine {
    Robot robot;
    IntakeState state = IntakeState.WAIT_ROBOT;
    IntakeState target = IntakeState.WAIT_ROBOT;
    LiftPosition upPos = LiftPosition.HIGHEST_BASKET;
    SampleState sampleState = SampleState.NOT_CLAIMED;
    LiftPosition liftPosition = LiftPosition.IN_ROBOT;

    public double transferPos = 0;

    ColorSensorListener colorSensorListener = new ColorSensorListener();
    Grabber grabber = new Grabber();
    LiftManager liftManager = new LiftManager();

    public void setTarget(IntakeState target) {
        this.target = target;
    }

    public void setState(IntakeState state) {
        this.state = state;
    }

    public void setLiftHigh(LiftPosition liftPosition) {
        this.liftPosition = liftPosition;
    }

    public void init(Robot robot) {
        this.robot = robot;
        grabber.init(robot);
        liftManager.init();
        colorSensorListener.init(robot);
    }

    ElapsedTime timer = new ElapsedTime();
    private boolean f = true;

    public void update() {
        Robot.telemetry.addData("State :", state.toString());
        Robot.telemetry.addData("Target state :", target.toString());
        if (target != state) {
            if (f) {
                timer.reset();
            }
            changeState(target);
            f = false;
            isDone = false;
        } else {
            updateState();
            f = true;
            isDone = true;
        }
    }

    boolean isDone = false;

    public boolean isDone() {
        return isDone;
    }

    private void waitEat() {
        liftManager.setTarget(LiftPosition.IN_ROBOT);
        grabber.intake.forwardBrush();
        grabber.intake.eatTransfer();
        grabber.intake.upFlipServo();
        grabber.intake.openSampleGrabber();
        grabber.transfer.openAfterTransferServo();
        grabber.transfer.inOutServo();
        grabber.transfer.toEatTwistServo();
        sampleState = SampleState.FROM_BRUSHS;
    }

    private void inRobot() {
        liftManager.setTarget(LiftPosition.IN_ROBOT);
        grabber.intake.stopBrush();
        grabber.intake.normalTrasnfer();
        grabber.intake.downFlipServo();
        grabber.intake.openSampleGrabber();
        grabber.transfer.openAfterTransferServo();
        grabber.transfer.inOutServo();
        grabber.transfer.toEatTwistServo();
    }

    private void eatFromWall() {
        liftManager.setTarget(LiftPosition.WALL_EAT);
        grabber.intake.stopBrush();
        grabber.intake.normalTrasnfer();
        grabber.intake.upFlipServo();
        grabber.intake.openSampleGrabber();
        grabber.transfer.openAfterTransferServo();
        grabber.transfer.outOutServo();
        grabber.transfer.toEatFromWallTwistServo();
        sampleState = SampleState.FROM_WALL;
    }

    private void toScoreSample() {
        liftManager.setTarget(liftPosition);
        grabber.intake.stopBrush();
        grabber.intake.normalTrasnfer();
        grabber.intake.upFlipServo();
        grabber.intake.openSampleGrabber();
        grabber.transfer.closeAfterTransferServo();
        grabber.transfer.outOutServo();
        grabber.transfer.toDropTwistServo();
        sampleState = SampleState.NOT_CLAIMED;
    }

    private void fromEatToRobot() {
        liftManager.setTarget(LiftPosition.IN_ROBOT);
        grabber.intake.stopBrush();
        grabber.intake.upFlipServo();
        grabber.intake.openSampleGrabber();
        if (timer.seconds() > 0.5)
            grabber.intake.normalTrasnfer();
        if (timer.seconds() > 1)
            setState(IntakeState.WAIT_ROBOT);
    }

    private void fromRobotToEat() {
        liftManager.setTarget(LiftPosition.IN_ROBOT);
        grabber.intake.upFlipServo();
        grabber.intake.forwardBrush();
        if (timer.seconds() > 0.5)
            grabber.intake.eatTransfer();
        if (timer.seconds() > 1)
            setState(IntakeState.WAIT_EAT);
    }

    private void fromRobotToWallEat() {
        liftManager.setTarget(LiftPosition.WALL_EAT);
        grabber.intake.upFlipServo();
        grabber.intake.stopBrush();
        ;
        if (timer.seconds() > 0.5) {
            grabber.transfer.outOutServo();
            grabber.transfer.toEatFromWallTwistServo();
        }
        if (timer.seconds() > 1)
            setState(IntakeState.WAIT_EAT);
    }

    private void scoreSample() {
        grabber.intake.normalTrasnfer();
        grabber.intake.upFlipServo();
        if (sampleState == SampleState.FROM_WALL) {
            grabber.transfer.closeAfterTransferServo();
            if (timer.seconds() > 0.5)
                liftManager.setTarget(liftPosition);
        } else {
            if (sampleState == SampleState.FROM_BRUSHS) {
                grabber.intake.openSampleGrabber();
                grabber.transfer.closeAfterTransferServo();
                if (timer.seconds() > 0.5)
                    liftManager.setTarget(liftPosition);
                if (timer.seconds() > 1) {
                    grabber.transfer.toDropTwistServo();
                    grabber.transfer.outOutServo();
                }
            }
        }
    }

    public void fromScoreToRobot() {
        grabber.transfer.openAfterTransferServo();
        if (timer.seconds() > 0.5){
            grabber.transfer.inOutServo();
            grabber.transfer.toEatTwistServo();
        }
        if(timer.seconds() > 0.75)
            liftManager.setTarget(LiftPosition.IN_ROBOT);
    }

    private void changeState(IntakeState target) {
        switch (target) {
            case WAIT_WALL_EAT:
                if (state == IntakeState.WAIT_ROBOT)
                    fromRobotToWallEat();
                else {
                    setTarget(IntakeState.WAIT_WALL_EAT);
                }
                break;
            case WAIT_ROBOT:
                if (state == IntakeState.WAIT_SAMPLE_SCORE)
                    fromScoreToRobot();
                else if (state == IntakeState.WAIT_EAT)
                    fromEatToRobot();
                break;
            case WAIT_SAMPLE_SCORE:
                if (state == IntakeState.WAIT_ROBOT || state == IntakeState.WAIT_WALL_EAT)
                    toScoreSample();
                else {
                    setTarget(IntakeState.WAIT_SAMPLE_SCORE);
                }
                break;
            case WAIT_EAT:
                if(state == IntakeState.WAIT_ROBOT)
                    fromRobotToEat();
                else
                    setTarget(IntakeState.WAIT_EAT);
        }
    }

    public void updateState() {
        switch (state) {
            case WAIT_ROBOT:
                inRobot();
                break;
            case WAIT_SAMPLE_SCORE:
                toScoreSample();
                break;
            case WAIT_WALL_EAT:
                eatFromWall();
                break;
            case WAIT_EAT:
                waitEat();
                break;
        }
    }

    public IntakeState getState() {
        return state;
    }
}
