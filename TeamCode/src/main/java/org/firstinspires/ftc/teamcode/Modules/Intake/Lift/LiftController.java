package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Writing by @MrFrosty1234
 */
@Config
public class LiftController implements Controller {
    Robot robot;
    public static double g = 2;
    private LiftMode mode = LiftMode.AUTO;
    public void setMode(LiftMode mode) {
        this.mode = mode;
    }

    private double manualTarget = 0;
    public void setManualTarget(double manualTarget) {
        this.manualTarget = manualTarget;
    }

    private Motor liftLeftMotor;
    private Motor liftRightMotor;

    LiftListener liftListener;

    public LiftPosition targetPosition = LiftPosition.DOWN;

    public static PidStatus pidStatus = new PidStatus(0.06, 0.005, 0.002, 0, 0, 0, 0, 3, 0);
    Pid pid = new Pid(pidStatus);

    public static PidStatus pidStatusSync = new PidStatus(0.0, 0, 0, 0, 0, 0, 0, 0, 0);
    Pid pidSync = new Pid(pidStatusSync);


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        liftListener = robot.liftListener;

        liftLeftMotor = LiftHangingMotors.liftLeftMotor;
        liftRightMotor = LiftHangingMotors.liftRightMotor;
    }

    public void setPower() {
        liftLeftMotor.setVoltage(uMove - uSync + g);
        liftRightMotor.setVoltage(uMove + uSync + g);
    }


    public boolean isAtTarget() {
        return abs(liftListener.getPosition() - targetPosition.get()) < 25;
    }

    private double uMove = 0;
    private double uSync = 0;

    public void updateLift() {
        pidSync.setTarget(0);
        pidSync.setPos(-liftListener.errSync);
        pidSync.update();
        uSync = pidSync.getU();

        pid.setTarget(targetPosition.get());
        pid.setPos(liftListener.getPosition());
        pid.update();
        uMove = pid.getU();

        if(isAtTarget()) {
            if (liftListener.getPosition() == LiftPosition.DOWN.get()) {
                uMove = 0;
                uSync = 0;
            }
            if (liftListener.getPosition() == LiftPosition.UP.get()) {
                uMove = 0;
                uSync = 0;
            }
        }
    }

    @Override
    public void update() {
        liftRightMotor.update();
        liftLeftMotor .update();

        switch (mode){
            case AUTO:
                updateLift();
                break;
            case MANUAL:
                uMove = manualTarget;
                uSync = 0;
                break;
        }

        setPower();
    }

    public void setDownPos() {
        targetPosition = LiftPosition.DOWN;
    }

    public void setTargetPosition(LiftPosition targetPosition) {
        this.targetPosition = targetPosition;
    }

    public void setInPos(){targetPosition = LiftPosition.IN_ROBOT;}


    public LiftPosition getTargetPosition(){
        return targetPosition;
    }

    public void setHighBasket() {
        targetPosition = LiftPosition.HIGHEST_BASKET;
    }

    public void setPosition(LiftPosition liftPosition) {
        this.targetPosition = liftPosition;
    }
}
