package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.Button;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

public class LiftListener implements Listener {

    Robot robot;

    DigitalChannel buttonDown;
    Motor liftLeftMotor;

    public LiftPosition targetPosition = LiftPosition.DOWN;
    public boolean liftAtTarget = false;
    public LiftPosition liftPosition;


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        buttonDown = robot.devicePool.sensors.downButton;
        liftLeftMotor = robot.devicePool.liftHangingMotors.liftLeftMotor;
        targetPosition = LiftPosition.DOWN;
    }

    public double getPosition() {
        return liftLeftMotor.dev.getCurrentPosition();
    }

    public LiftPosition setDownPos() {
        return targetPosition = LiftPosition.DOWN;
    }

    public LiftPosition setUpPos() {
        return targetPosition = LiftPosition.UP;
    }

    public LiftPosition setLowAxisPos() {
        return targetPosition = LiftPosition.LOW_AXIS_GET;
    }

    public LiftPosition setHighAxis() {
        return targetPosition = LiftPosition.HIGHEST_AXIS;
    }

    public LiftPosition setLowBasket() {
        return targetPosition = LiftPosition.LOWEST_BASKET;
    }

    public LiftPosition setHighBasket() {
        return targetPosition = LiftPosition.HIGHEST_BASKET;
    }


    public void updatePosition() {
        liftPosition = targetPosition;
    }

    public boolean isLiftAtTarget() {
        if ((targetPosition.get() - getPosition()) < 5 && buttonDown.getState()) {
            return liftAtTarget = true;
        }
        else
            return liftAtTarget = false;
    }

    @Override
    public void read() {
        updatePosition();
    }

}
