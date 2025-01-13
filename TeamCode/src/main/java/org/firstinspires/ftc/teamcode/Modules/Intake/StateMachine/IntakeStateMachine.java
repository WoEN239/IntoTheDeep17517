package org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.AfterTransferGrabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.FlipGrabberPositionLeft;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.FlipGrabberPositionRight;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.OutServoPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.PowerBrush;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.TransferPositionRight;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.TwistServo;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.SampleSensor.ColorSensorListener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
 Writing by EgorKhvostikov
*/

public class IntakeStateMachine {
    IntakeState state  = IntakeState.WAIT_CENTRE_EAT;
    IntakeState target = IntakeState.WAIT_CENTRE_EAT;

    LiftPosition upPos = LiftPosition.LOW_AXIS;
    public double transferPos = 0;

    ColorSensorListener colorSensorListener = new ColorSensorListener();

    Robot robot                            ;
    Grabber grabber                        ;
    LiftController liftController          ;
    LiftListener liftListener              ;

    public void setTarget(IntakeState target) {
        this.target = target;
    }
    public void setState(IntakeState state){
        this.state = state;
    }

    public void init(Robot robot) {
        this.robot = robot;
        grabber = robot.grabber;
        liftController = robot.liftController;
        liftListener = robot.liftListener;
        colorSensorListener = robot.colorSensorListener;
    }

    ElapsedTime timer = new ElapsedTime();
    private boolean f = true;
    public void update(){
        Robot.telemetry.addData("State :", state.toString());
        Robot.telemetry.addData("Target state :", target.toString());
        if(target!=state){
            if(f){
                timer.reset();
            }
            changeState(target);
            f = false;
            isDone = false;
        }else {
            updateState();
            f = true;
            isDone = true;
        }
    }
    boolean isDone = false;
    public boolean isDone(){
        return isDone;
    }

    private void waitUp(){
        liftController.setPosition(upPos)  ;
        grabber       .stopBrush();
        grabber       .toDropTwistServo();
        grabber       .closeSampleGrabber();
        grabber       .downFlipServo();
       // grabber       .transferToNormal()  ;
        grabber       .outOutServo();
        grabber       .closeAfterTransferServo();
    }

    private void waitAxis(){
        liftController.setTargetPosition(LiftPosition.HIGHEST_AXIS);
        grabber       .flipGrabberPositonLeft = FlipGrabberPositionLeft.UNSPREADOUT;
        grabber       .flipGrabberPositonRight = FlipGrabberPositionRight.UNSPREADOUT;
        grabber       .afterTransferGrabberPosition = AfterTransferGrabber.CLOSE;
        if(liftListener.getPosition()>500) {
            grabber.outServoPosition = OutServoPosition.OUT_ROBOT;
        }
            grabber.twistServoPosition = TwistServo.EAT_FROM_WALL;

    }

    private void waitWallEat(){
        liftController.setTargetPosition(LiftPosition.WALL_EAT);
        grabber       .flipGrabberPositonLeft = FlipGrabberPositionLeft.UNSPREADOUT;
        grabber       .flipGrabberPositonRight = FlipGrabberPositionRight.UNSPREADOUT;
        grabber       .outServoPosition = OutServoPosition.OUT_ROBOT;
        grabber       .twistServoPosition = TwistServo.EAT_FROM_WALL;
        grabber       .afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
    }



    private void fromUpWaitToAxisWait(){
        grabber.openSampleGrabber();
        if(timer.seconds() > 0.5) {
            grabber.inOutServo();
          //  grabber.transferToNormal();
            grabber.openAfterTransferServo();
            grabber.toEatTwistServo();
        }
        if(timer.seconds()>1) {
            liftController.setInPos();
        }
        if(timer.seconds()>2) {
            setState(IntakeState.WAIT_AXIS);
        }
    }

    private void fromDownWaitToUpWait(){
       // grabber       .transferToNormal()  ;
        grabber       .downFlipServo()     ;
        grabber       .closeSampleGrabber();
        grabber.closeAfterTransferServo();

        if(timer.seconds()>1) {
            liftController.setPosition(upPos);
        }
        if(timer.seconds()>2) {
            setState(IntakeState.WAIT_BASKET);
        }
    }

    private void fromEatWallWaitToAxisWait(){
        grabber.afterTransferGrabberPosition = AfterTransferGrabber.CLOSE;
        if(timer.seconds()>0.5) {
            liftController.setTargetPosition(LiftPosition.HIGHEST_AXIS);
            if (liftController.isAtTarget()) {
                setState(IntakeState.WAIT_AXIS);
            }
        }
    }

    private void fromAxisWaitToEatWallWait(){
        liftController.setTargetPosition(LiftPosition.SCORE_AXIS);
        if(timer.seconds()>1) {
            if (liftController.isAtTarget() || timer.seconds()>2) {

                grabber.afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
                setState(IntakeState.WAIT_WALL_EAT);
            }
        }
    }


    private void waitCentreEat(){
        double p  = Range.clip(transferPos,TransferPositionRight.NORMAL.get(),TransferPositionRight.EAT.get());
        grabber.transferPositionRight = p;

        grabber.flipGrabberPositonLeft  = FlipGrabberPositionLeft.SPREADOUT;
        grabber.flipGrabberPositonRight = FlipGrabberPositionRight.SPREADOUT;
        grabber.brushPower = PowerBrush.FORWARD;


    }

    private void changeState(IntakeState target){
        switch (target) {
            case WAIT_WALL_EAT:
                if(state == IntakeState.WAIT_AXIS)
                    fromAxisWaitToEatWallWait();
                else{
                    setTarget(IntakeState.WAIT_WALL_EAT);
                }
                break;
            case WAIT_AXIS:
                if(state == IntakeState.WAIT_WALL_EAT)
                    fromEatWallWaitToAxisWait();
                else if (state == IntakeState.WAIT_BASKET)
                    fromUpWaitToAxisWait();
                break;
            case WAIT_BASKET:
                if(state == IntakeState.WAIT_AXIS)
                    fromDownWaitToUpWait();
                else{
                    setTarget(IntakeState.WAIT_BASKET);
                }
                break;
        }
    }

    public void updateState(){
        switch (state){
            case WAIT_BASKET:
                waitUp();
                break;
            case WAIT_AXIS:
                waitAxis();
                break;
            case WAIT_WALL_EAT:
                waitWallEat();
                break;
            case WAIT_CENTRE_EAT:
                waitCentreEat();
                break;
        }
    }

    public IntakeState getState() {
        return state;
    }
}
