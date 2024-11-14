package org.firstinspires.ftc.teamcode.Modules.Intake;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.RotateServoPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot;

public class IntakeStateMachine {
    IntakeState state  = IntakeState.WAIT_DOWN;
    IntakeState target = IntakeState.WAIT_DOWN;

    LiftPosition upPos = LiftPosition.LOW_AXIS;
    double       rotateServoPos = RotateServoPosition.normal;

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
    public void setRotateServoPos(double rotateServoPos) {
        this.rotateServoPos = rotateServoPos;
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
        grabber       .openSampleGrabber();
        grabber       .normalRotateServo() ;
        grabber       .upFlipServo()       ;
        grabber       .transferToNormal()  ;
    }

    private void waitDown(){
        liftController.setDownPos()        ;
        grabber       .closeSimpleGrabber();
        grabber       .normalRotateServo() ;
        grabber       .upFlipServo()       ;
        grabber       .transferToNormal()  ;
    }

    private void waitEat(){
        liftController.setDownPos()        ;
        grabber       .openSampleGrabber() ;
        grabber       .setRotateServoPosition(rotateServoPos);
        grabber       .targetingFLipServo()     ;
        grabber       .transferToEat()     ;
    }

    private void fromUpWaitToDownWait(){

        grabber.openSampleGrabber();
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

        grabber       .closeSimpleGrabber();
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
            grabber.closeSimpleGrabber();
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
        grabber.openSampleGrabber();
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
