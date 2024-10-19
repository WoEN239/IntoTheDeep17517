package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by @MrFrosty1234
 */
@Config
public class LiftController implements Controller {
    LiftPosition liftPosition;
    Robot robot;

    Motor liftLeftMotor;
    Motor liftRightMotor;

    LiftListener liftListener;

    LiftPosition targetPosition = LiftPosition.DOWN;

    public static PidStatus pidStatus = new PidStatus(0,0,0,0,0,0);
    Pid pid = new Pid(pidStatus);
    public static double gravity = 0.1;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        liftListener = robot.liftListener;

        liftLeftMotor = robot.devicePool.liftHangingMotors.liftLeftMotor;
        liftRightMotor = robot.devicePool.liftHangingMotors.liftRightMotor;

    }

    public void setPower(double powerToSet) {
        liftLeftMotor.setVoltage(powerToSet);
        liftRightMotor.setVoltage(powerToSet);
    }


    private double powerToSet = 0;

    private boolean isManual = true;

    private double manPower = 0;

    public void manual(double power) {
        isManual = true;
        manPower = power;
    }

    public void auto() {
        isManual = false;
    }

    private boolean isAtTarget() {
        return abs(liftListener.getPosition() - targetPosition.get())>5;
    }
    public void updateLift() {
        if ((liftListener.getPosition() > -10) && !liftListener.buttonDown.getState()) {
            if (!isAtTarget()) {
                powerToSet = pid.getU();
            } else {
                if(liftListener.buttonDown.getState())
                    powerToSet = 0;
                else
                    powerToSet = gravity;
            }
        } else {
            powerToSet = 0.1;
        }
        if (isManual) {
            powerToSet = manPower;
        }
    }

    @Override
    public void update() {
        setPower(powerToSet);
    }

    public void setDownPos() {
        targetPosition = LiftPosition.DOWN;
    }

    public void setLowAxis() {
        targetPosition = LiftPosition.LOW_AXIS_GET;
    }

    public void setHighAxis() {
        targetPosition = LiftPosition.HIGHEST_AXIS;
    }

    public void setLowBasket() {
        targetPosition = LiftPosition.LOWEST_BASKET;
    }

    public void setHighBasket() {
        targetPosition = LiftPosition.HIGHEST_BASKET;
    }

}
