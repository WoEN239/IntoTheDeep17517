package org.firstinspires.ftc.teamcode.Modules.Intake;


import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Robot;

public class GrabberStateMachine {

    Robot robot;
    Grabber grabber;
    LiftController liftController;
    LiftListener   liftListener;

    public void init(Robot robot){
        this.robot = robot;
        grabber = robot.grabber;
        liftController = robot.liftController;
        liftListener = robot.liftListener;
    }

    State state = State.TO_DOWN;

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    private boolean isOn = true;

    public void on() {
        isOn = true;
    }

    public void off(){
        isOn = false;
    }


    public void update(){
        if(isOn){
            switch (state){
                case DROP_SIMPLES:
                    if(liftListener.getPosition() > 200) {
                        grabber.transferToGrab();
                        grabber.openSimpleGrabber();
                        grabber.openUpGrabber();
                    }
                    setState(State.TO_DOWN);
                    break;
                case TO_HIGH_BASKET:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    liftController.setHighBasket();
                    break;
                case TO_LOW_BASKET:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    liftController.setLowBasket();
                    break;
                case TO_LOW_AXIS:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    liftController.setLowAxis();
                    break;
                case TO_HIGH_AXIS:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    liftController.setHighAxis();
                    break;
                case GRAB:
                    grabber.transferToGrab();
                    grabber.openSimpleGrabber();
                    grabber.openUpGrabber();
                    liftController.setDownPos();
                    break;
                case TO_DOWN:
                    liftController.setDownPos();
                    break;
                case RIDE:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    liftController.setDownPos();
                    break;
            }
        }
    }
}
