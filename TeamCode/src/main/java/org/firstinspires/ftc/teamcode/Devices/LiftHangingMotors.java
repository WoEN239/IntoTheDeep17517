package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class LiftHangingMotors {
    HardwareMap hardwareMap;

    public Motor liftRightMotor;
    public Motor liftLeftMotor;

    public Motor hangingMotor;

    public LiftHangingMotors(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        liftLeftMotor.init("fake", hardwareMap);
        liftRightMotor.init("fake", hardwareMap);
        hangingMotor.init("fake", hardwareMap);

        liftRightMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftRightMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangingMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftLeftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeftMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRightMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRightMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        hangingMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
