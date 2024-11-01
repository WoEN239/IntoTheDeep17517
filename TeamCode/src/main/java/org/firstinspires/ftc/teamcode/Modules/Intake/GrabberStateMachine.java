package org.firstinspires.ftc.teamcode.Modules.Intake;


import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Robot;

public class GrabberStateMachine {

    Robot robot;
    Grabber grabber;
    LiftController liftController;
    LiftListener liftListener;

    public void init(Robot robot) {
        this.robot = robot;
        grabber = robot.grabber;
        liftController = robot.liftController;
        liftListener = robot.liftListener;
    }

    IntakeState state = IntakeState.TO_DOWN;

    public IntakeState getState() {
        return state;
    }

    public void setState(IntakeState state) {
        this.state = state;
    }

    private boolean isOn = true;

    public void on() {
        isOn = true;
    }

    public void off() {
        isOn = false;
    }


    public void update() {
        if (!isOn) {
            return;
        }
        switch (state) {
            case DROP_SIMPLES:
                if (liftListener.getPosition() > 200) {
                    grabber.transferToNormal();
                    grabber.openSimpleGrabber();
                    grabber.closeFlipServo();
                }
                setState(IntakeState.TO_DOWN);
                break;
            case TO_HIGH_BASKET:
                grabber.transferToNormal();
                grabber.closeSimpleGrabber();
                grabber.closeFlipServo();
                liftController.setHighBasket();
                break;
            case TO_LOW_BASKET:
                grabber.transferToNormal();
                grabber.closeSimpleGrabber();
                grabber.closeFlipServo();
                liftController.setLowBasket();
                break;
            case TO_LOW_AXIS:
                grabber.transferToNormal();
                grabber.closeSimpleGrabber();
                grabber.closeFlipServo();
                liftController.setLowAxis();
                break;
            case TO_HIGH_AXIS:
                grabber.transferToNormal();
                grabber.closeSimpleGrabber();
                grabber.closeFlipServo();
                liftController.setHighAxis();
                break;
            case GRAB:
                grabber.transferToGrab();
                grabber.openSimpleGrabber();
                grabber.openFlipServo();
                liftController.setDownPos();
                break;
            case TO_DOWN:
                liftController.setDownPos();
                break;
            case RIDE:
                grabber.transferToNormal();
                grabber.closeSimpleGrabber();
                grabber.closeFlipServo();
                liftController.setDownPos();
                break;
            case PERPENDICULAR:
                grabber.perpendicularRotatePos();
            case NORMAL_ROTATE:
                grabber.normalRotatePos();
        }
    }
}