package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Robot;

import java.awt.font.NumericShaper;

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
        updateLift();
        liftLeftMotor.setVoltage(powerToLeftMotor - uSync);
        liftRightMotor.setVoltage(powerToRightMotor + uSync);
    }


    private boolean isAtTarget() {
        return abs(liftListener.getPosition() - targetPosition.get()) > 5;
    }


    double powerToLeftMotor = 0;
    double powerToRightMotor = 0;

    double uSync = 0;

    public void updateLift() {
        if (targetPosition != LiftPosition.DOWN) {
            pid.setTarget(targetPosition.get());
            pid.setPos(liftListener.getPosition());
            pid.update();
            powerToLeftMotor = powerToRightMotor = pid.getU();

            powerToLeftMotor = Range.clip(powerToLeftMotor, -1, 1);
            powerToRightMotor = Range.clip(powerToRightMotor, -1, 1);

            pidSync.setTarget(0);
            pidSync.setPos(-liftListener.errSync);
            pidSync.update();
            uSync = pidSync.getU();
        } else {
            if (liftListener.rightButtonDown.getState()) {
                powerToRightMotor = 0;
                uSync = 0;
            }
            if (liftListener.leftButtonDown.getState()) {
                powerToLeftMotor = 0;
                uSync = 0;
            }
            if (isAtTarget() && (!liftListener.rightButtonDown.getState() && !liftListener.leftButtonDown.getState())) {
                uSync = 0;
                powerToLeftMotor = powerToRightMotor = gravity;
            }
            pidSync.update();
            pid.update();
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
