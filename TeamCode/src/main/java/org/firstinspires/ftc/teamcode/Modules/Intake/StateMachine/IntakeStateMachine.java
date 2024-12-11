package org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class IntakeStateMachine {
    IntakeState state  = IntakeState.WAIT_DOWN;
    IntakeState target = IntakeState.WAIT_DOWN;

    LiftPosition upPos = LiftPosition.LOW_AXIS;

    Robot robot                  ;
    Grabber grabber              ;
    LiftController liftController;
    LiftListener liftListener    ;

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
        }else {
            updateState();
            f = true;
        }
    }

    private void waitUp(){
        liftController.setPosition(upPos)  ;
        grabber       .forwardSampleGrabber();
        grabber       .closeAfterTransferServo();
        grabber       .downFlipServo();
        grabber       .transferToNormal()  ;
        grabber       .outOutServo();
    }

    private void waitDown(){
        liftController.setDownPos()        ;
        grabber       .stopSimpleGrabber();
        grabber       .upFlipServo()       ;
        grabber       .transferToNormal()  ;
        grabber       .openAfterTransferServo();
        grabber       .inOutServo();
    }

    private void waitEat(){
        liftController.setDownPos();
        grabber       .stopSimpleGrabber();
        grabber       .downFlipServo();
        grabber       .transferToEat();
        grabber       .openAfterTransferServo();
        grabber       .inOutServo();
    }

    private void fromUpWaitToDownWait(){
        grabber.closeSampleGrabber();
        grabber.normalRotateServo();
        grabber.targetingFLipServo()    ;
        grabber.transferToNormal();
        if(timer.seconds()>1) {
            liftController.setDownPos();
        }
        if(timer.seconds()>2) {
            setState(IntakeState.WAIT_DOWN);
        }
    }

    private void fromDownWaitToUpWait(){

        grabber       .reverseSimpleGrabber();
        grabber       .normalRotateServo() ;
        grabber       .downFlipServo()     ;
        grabber       .transferToNormal()  ;

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
        grabber.setRotateServoPosition(rotateServoPos);
        liftController.setDownPos() ;
        if(timer.seconds()>0.5) {
            grabber.downFlipServo();
            grabber.reverseSimpleGrabber();
        }
        if(timer.seconds()>1) {
            grabber.transferToNormal();
        }
        if(timer.seconds()>1.2) {
            setState(IntakeState.WAIT_DOWN);
        }

    }

    private void fromDownWaitToEatWait(){
        grabber.targetingFLipServo();

        grabber.setRotateServoPosition(rotateServoPos); ;
        grabber.closeSampleGrabber();
        grabber.transferToEat();

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
