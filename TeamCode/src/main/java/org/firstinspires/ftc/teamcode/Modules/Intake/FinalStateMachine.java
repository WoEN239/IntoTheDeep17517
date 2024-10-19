package org.firstinspires.ftc.teamcode.Modules.Intake;


import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Robot;

public class FinalStateMachine {

    Robot robot;
    Grabber grabber;
    LiftListener lift;

    public FinalStateMachine(Robot robot){
        this.robot = robot;
        grabber = robot.grabber;
        lift = robot.liftListener;
    }

    State state = State.TO_DOWN;

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    private boolean isOn = false;

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
                    if(lift.getPosition() > 200) {
                        grabber.transferToGrab();
                        grabber.openSimpleGrabber();
                        grabber.openUpGrabber();
                    }
                    break;
                case TO_HIGH_BASKET:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    lift.setHighBasket();
                    break;
                case TO_LOW_BASKET:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    lift.setLowBasket();
                    break;
                case TO_LOW_AXIS:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    lift.setLowAxis();
                    break;
                case TO_HIGH_AXIS:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    lift.setHighAxis();
                    break;
                case GRAB:
                    grabber.transferToGrab();
                    grabber.openSimpleGrabber();
                    grabber.openUpGrabber();
                    lift.setDownPos();
                    break;
                case TO_DOWN:
                    lift.setDownPos();
                    break;
                case RIDE:
                    grabber.transferToNormal();
                    grabber.closeSimpleGrabber();
                    grabber.closeUpGrabber();
                    lift.setDownPos();
                    break;
            }
        }
    }
}
