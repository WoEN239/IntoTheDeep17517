package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.Button;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by @MrFrosty1234
 */

public class LiftController implements Controller {
    LiftPosition liftPosition;
    Robot robot;

    Motor liftLeftMotor;
    Motor liftRightMotor;

    LiftListener liftListener = new LiftListener();


    private double voltage;

    public PidStatus pidStatus;
    Pid pid = new Pid(pidStatus);

    @Override
    public void init(Robot robot) {
        this.robot = new Robot(robot.opMode);

        liftListener.init(robot);

        liftLeftMotor = robot.devicePool.liftHangingMotors.liftLeftMotor;
        liftRightMotor = robot.devicePool.liftHangingMotors.liftRightMotor;

        voltage = 12;
    }

    public void reset() {
        liftRightMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRightMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftLeftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeftMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRightMotor.dev.setDirection(DcMotorSimple.Direction.FORWARD);

        liftLeftMotor.dev.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void setPower(double power) {
        liftLeftMotor.setPower(power);
        liftRightMotor.setPower(power);
    }


    private double power = 0;

    private boolean isManual = true;

    private double manPower = 0;

    public void manual(double power) {
        isManual = true;
        manPower = power;
    }

    private void auto() {
        isManual = false;
    }


    public void updateLift() {
        pid = new Pid(pidStatus);

        if ((liftListener.getPosition() > -10) && !liftListener.buttonDown.getState()) {
            if (liftListener.liftPosition != LiftPosition.DOWN) {
                power = pid.getU();
            } else {
                if(liftListener.liftAtTarget)
                    power = 0;
                else
                    power = 1;
            }
        } else {
            power = 0.1;
        }
        if (isManual) {
            setPower(manPower);
        } else {
            setPower(power);
        }
    }

}
