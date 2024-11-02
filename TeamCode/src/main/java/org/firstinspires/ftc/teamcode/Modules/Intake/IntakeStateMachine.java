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

    public void update(){
        if(target!=state){
            changeState(target);
        }

    }

    private void waitUp(){
        liftController.setPosition(upPos)  ;
        grabber       .closeSimpleGrabber();
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
        grabber       .openSimpleGrabber() ;
        grabber       .setRotateServoPosition(rotateServoPos);
        grabber       .moveFLipServo()     ;
        grabber       .transferToEat()     ;
    }

    private void fromUpWaitToDownWait(){
        waitTimer.reset();

        grabber.openSimpleGrabber();
        grabber.normalRotateServo();
        grabber.moveFLipServo()    ;

        if(waitSeconds(1)){
            grabber.transferToNormal();
            liftController.setDownPos();
            waitTimer.reset();
        }

        if(waitSeconds(2)){
            setState(IntakeState.WAIT_DOWN);
            waitTimer.reset();
        }
    }

    private void fromDownWaitToUpWait(){
        waitTimer.reset();

        grabber       .closeSimpleGrabber();
        grabber       .normalRotateServo() ;
        grabber       .downFlipServo()     ;
        grabber       .transferToNormal()  ;
        liftController.setPosition(upPos);
        if(waitSeconds(2)){
            waitTimer.reset();
            setState(IntakeState.WAIT_UP);
        }
    }

    private void fromEatWaitToDownWait(){
        waitTimer.reset();

        grabber.upFlipServo()       ;
        grabber.setRotateServoPosition(rotateServoPos); ;
        grabber.downFlipServo()     ;
        grabber.closeSimpleGrabber();
        grabber.transferToNormal()  ;

        liftController.setDownPos();
        if(waitSeconds(2)){
            waitTimer.reset();
            setState(IntakeState.WAIT_DOWN);
        }
    }

    private void fromDownWaitToEatWait(){
        waitTimer.reset();
        grabber.moveFLipServo();       ;
        grabber.setRotateServoPosition(rotateServoPos); ;
        grabber.openSimpleGrabber();
        grabber.transferToEat();
        if (waitSeconds(1)){
            setState(IntakeState.WAIT_EAT);
        }
    }

    private void changeState(IntakeState target){
        switch (target) {
            case WAIT_EAT:
                if(state == IntakeState.WAIT_DOWN)
                    fromDownWaitToEatWait();
            case WAIT_DOWN:
                if(state == IntakeState.WAIT_EAT)
                    fromEatWaitToDownWait();
                else if (state == IntakeState.WAIT_UP)
                    fromUpWaitToDownWait();
                break;
            case WAIT_UP:
                if(state == IntakeState.WAIT_DOWN)
                    fromDownWaitToEatWait();
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

    ElapsedTime waitTimer = new ElapsedTime();
    public boolean waitSeconds(double s){
        return waitTimer.seconds()>s;
    }

    public IntakeState getState() {
        return state;
    }
}
