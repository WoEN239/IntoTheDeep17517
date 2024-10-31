package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
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

    public static PidStatus pidStatus = new PidStatus(0, 0, 0, 0, 0, 0, 0, 0, 0);
    Pid pid = new Pid(pidStatus);

    public static PidStatus pidStatusSync = new PidStatus(0, 0, 0, 0, 0, 0, 0, 0, 0);
    Pid pidSync = new Pid(pidStatusSync);
    public static double gravity = 0.1;


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        liftListener = robot.liftListener;

        liftLeftMotor = LiftHangingMotors.liftLeftMotor;
        liftRightMotor = LiftHangingMotors.liftRightMotor;
    }

    public void setPower() {
        liftLeftMotor.setPower(powerToMotor - uSync);
        liftRightMotor.setPower(powerToMotor + uSync);
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
        return abs(liftListener.getPosition() - targetPosition.get()) > 5;
    }

    private double err = targetPosition.get() - liftListener.getPosition();

    double powerToMotor = 0;

    double uSync = 0;

    public void updateLift() {
        if ((liftListener.getPosition() > -10) && !liftListener.leftButtonDown.getState() && !liftListener.rightButtonDown.getState()) {
            if (!isAtTarget()) {
                pid.setTarget(targetPosition.get());
                pid.setPos(liftListener.getPosition());
                pid.update();
                powerToMotor = pid.getU();

                pidSync.setTarget(0);
                pid.setPos(liftListener.errSync);
                pid.update();
                uSync = pid.getU();
            } else {
                if (liftListener.rightButtonDown.getState() && liftListener.leftButtonDown.getState()) {
                    powerToMotor = 0;
                    uSync = 0;
                }
                else {
                    powerToMotor = gravity;
                    uSync = 0;
                }
            }
        }
    }

    @Override
    public void update() {
      setPower();
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
