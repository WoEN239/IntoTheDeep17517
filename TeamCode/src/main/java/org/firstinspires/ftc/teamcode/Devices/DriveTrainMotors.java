package org.firstinspires.ftc.teamcode.Devices;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */
@Config
public class DriveTrainMotors {
    public static Motor rightForwardDrive = new Motor();
    public static Motor rightBackDrive = new Motor();
    public static Motor leftForwardDrive = new Motor();
    public static Motor leftBackDrive = new Motor();
    public static Motor yOdometer = new Motor();
    public static Motor rightOdometer = new Motor();
    public static Motor leftOdometer = new Motor();

    public DriveTrainMotors(HardwareMap hardwareMap) {

        leftBackDrive.init("motor", hardwareMap);
        rightForwardDrive.init("fakeMotor", hardwareMap);
        rightBackDrive.init("fakeMotor", hardwareMap);
        leftForwardDrive.init("fakeMotor", hardwareMap);
        yOdometer.init("fakeMotor", hardwareMap);
        leftOdometer.init("fakeMotor", hardwareMap);
        rightOdometer.init("fakeMotor", hardwareMap);
        reset();
    }

    public void reset() {
        leftBackDrive.dev.setDirection(DcMotorSimple.Direction.FORWARD);
        leftForwardDrive.dev.setDirection(DcMotorSimple.Direction.FORWARD);

        rightBackDrive.dev.setDirection(DcMotorSimple.Direction.FORWARD);
        rightForwardDrive.dev.setDirection(DcMotorSimple.Direction.FORWARD);

        leftBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stop() {
        leftBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void start() {
        leftBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
