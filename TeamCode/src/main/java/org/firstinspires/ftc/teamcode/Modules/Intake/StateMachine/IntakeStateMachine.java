package org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.SampleSensor.ColorDetective;
import org.firstinspires.ftc.teamcode.Modules.Intake.SampleSensor.ColorSensorListener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
 Writing by EgorKhvostikov
*/

public class IntakeStateMachine {
    IntakeState state  = IntakeState.WAIT_DOWN;
    IntakeState target = IntakeState.WAIT_DOWN;

    LiftPosition upPos = LiftPosition.LOW_AXIS;



    Robot robot                            ;
    Grabber grabber                        ;
    LiftController liftController          ;
    LiftListener liftListener              ;
    ColorSensorListener colorSensorListener;

    public void setTarget(IntakeState target) {
        this.target = target;
    }
    private void setState(IntakeState state){
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
        grabber       .transferToNormal()  ;
        grabber       .outOutServo();
        grabber       .closeAfterTransferServo();
    }

    private void waitDown(){
        liftController.setInPos();
        grabber       .stopBrush();
        grabber       .toEatFromWallTwistServo();
        grabber       .openSampleGrabber();
        grabber       .downFlipServo()       ;
        grabber       .transferToNormal()  ;
        grabber       .openAfterTransferServo();
        grabber       .inOutServo();
    }

    private void waitEat(){
        liftController.setInPos();
        grabber       .forwrdBrush();
        grabber       .toEatTwistServo();
        grabber       .openSampleGrabber();
        grabber       .downFlipServo();
        grabber       .transferToEat();
        grabber       .openAfterTransferServo();
        grabber       .inOutServo();
    }



    private void fromUpWaitToDownWait(){
        grabber.openSampleGrabber();
        if(timer.seconds() > 0.5) {
            grabber.inOutServo();
            grabber.transferToNormal();
            grabber.openAfterTransferServo();
            grabber.toEatTwistServo();
        }
        if(timer.seconds()>1) {
            liftController.setInPos();
        }
        if(timer.seconds()>2) {
            setState(IntakeState.WAIT_DOWN);
        }
    }

    private void fromDownWaitToUpWait(){
        grabber       .transferToNormal()  ;
        grabber       .downFlipServo()     ;
        grabber       .closeSampleGrabber();
        grabber.closeAfterTransferServo();

        if(timer.seconds()>1) {
            liftController.setPosition(upPos);
        }
        if(timer.seconds()>2) {
            setState(IntakeState.WAIT_UP);
        }
    }

    private void fromEatWaitToDownWait(){
        grabber.transferToEat()     ;
        grabber.upFlipServo()       ;
        grabber.closeSampleGrabber();
        liftController.setInPos();
        if(timer.seconds()>0.5) {
            grabber.downFlipServo();
        }
        if(timer.seconds()>1) {
            grabber.transferToNormal();
        }
        if(timer.seconds()>1.2) {
            setState(IntakeState.WAIT_DOWN);
        }

    }

    private void fromDownWaitToEatWait(){
        grabber.upFlipServo();
        if(timer.seconds() > 0.5) grabber.transferToEat();
        if(timer.seconds()>1) {
            setState(IntakeState.WAIT_EAT);
        }
    }

    private void changeState(IntakeState target){
        switch (target) {
            case WAIT_EAT:
                if(state == IntakeState.WAIT_DOWN)
                    fromDownWaitToEatWait();
                else{
                    setTarget(IntakeState.WAIT_EAT);
                }
                break;
            case WAIT_DOWN:
                if(state == IntakeState.WAIT_EAT)
                    fromEatWaitToDownWait();
                else if (state == IntakeState.WAIT_UP)
                    fromUpWaitToDownWait();
                break;
            case WAIT_UP:
                if(state == IntakeState.WAIT_DOWN)
                    fromDownWaitToUpWait();
                else{
                    setTarget(IntakeState.WAIT_UP);
                }
                break;
        }
    }

    public void updateState(){
        switch (state){
            case WAIT_UP:
                waitUp();
                break;
            case WAIT_DOWN:
                waitDown();
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
