package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class LiftHangingMotors {

    public static Motor liftRightMotor = new Motor();
    public static Motor liftLeftMotor  = new Motor();
    public static Motor hangingMotor   = new Motor();

    public static void init(HardwareMap hardwareMap) {

        liftLeftMotor.init("fakeMotor", hardwareMap);
        liftRightMotor.init("fakeMotor", hardwareMap);
        hangingMotor.init("fakeMotor", hardwareMap);

        liftRightMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftLeftMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        hangingMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftLeftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeftMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRightMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRightMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        hangingMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
