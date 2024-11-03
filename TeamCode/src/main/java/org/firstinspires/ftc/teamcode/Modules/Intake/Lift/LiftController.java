package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Robot;

/*
 * Writing by @MrFrosty1234
*/
@Config
public class LiftController implements Controller {
    LiftPosition liftPosition;
    Robot robot;

    Motor liftLeftMotor;
    Motor liftRightMotor;

    LiftListener liftListener;

    public LiftPosition targetPosition = LiftPosition.DOWN;

    public static PidStatus pidStatus = new PidStatus(0.06, 0.001, 0.00001, 0, 0, 0, 0, 2, 0.5);
    Pid pid = new Pid(pidStatus);

    public static PidStatus pidStatusSync = new PidStatus(0.00001, 0, 0, 0, 0, 0, 0, 0, 0);
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
        updateLift();
        liftLeftMotor.setVoltage(powerToLeftMotor - uSync);
        liftRightMotor.setVoltage(powerToRightMotor + uSync);
    }


    public boolean isAtTarget() {
        return abs(liftListener.getPosition() - targetPosition.get()) < 20;
    }


    double powerToLeftMotor = 0;
    double powerToRightMotor = 0;

    double uSync = 0;

    public void updateLift() {
        pidSync.setTarget(0);
        pidSync.setPos(-liftListener.errSync);
        pidSync.update();
        uSync = pidSync.getU();
        if (!isAtTarget()) {
            pid.setTarget(targetPosition.get());
            pid.setPos(liftListener.getPosition());
            pid.update();
            powerToLeftMotor = pid.getU();
            powerToRightMotor = pid.getU();

        } else {
            if (liftListener.rightButtonDown.getState()) {
                powerToRightMotor = gravity;
                uSync = 0;
            }
            if (liftListener.leftButtonDown.getState()) {
                powerToLeftMotor = gravity;
                uSync = 0;
            }
            if (isAtTarget() && (!liftListener.rightButtonDown.getState() && !liftListener.leftButtonDown.getState())) {
                uSync = 0;
                powerToLeftMotor = powerToRightMotor = gravity;
            }
        }
        pid.update();
    }

    @Override
    public void update() {
        liftRightMotor.update();
        liftLeftMotor.update();
        setPower();
    }

    public LiftPosition getTargetPosition() {
        return targetPosition;
    }

    public void setDownPos() {
        targetPosition = LiftPosition.DOWN;
    }

    public void setLowAxis() {
        targetPosition = LiftPosition.LOW_AXIS;
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
    public void setPosition(LiftPosition pos){targetPosition = pos;}
}
