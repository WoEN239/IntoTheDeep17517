package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class LiftHangingMotors {

    public static Motor liftRightMotor = new Motor();
    public static Motor liftLeftMotor = new Motor();
    public static Motor brushMotor = new Motor();

    public static void init(HardwareMap hardwareMap) {

        liftLeftMotor.init("liftLeftMotor", hardwareMap);
        liftRightMotor.init("liftRightMotor", hardwareMap);
        brushMotor.init("brushMotor", hardwareMap);


        liftLeftMotor.setDir(MotorDirection.BACK);
        liftRightMotor.setDir(MotorDirection.FORWARD);
        brushMotor.setDir(MotorDirection.BACK);

        liftLeftMotor.dev.setZeroPowerBehavior (DcMotor.ZeroPowerBehavior.FLOAT);
        liftRightMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        brushMotor.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        liftLeftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeftMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRightMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRightMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        brushMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brushMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}
