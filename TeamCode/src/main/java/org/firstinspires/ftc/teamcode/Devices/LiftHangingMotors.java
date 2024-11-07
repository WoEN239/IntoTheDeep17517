package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class LiftHangingMotors {

    public static Motor liftRightMotor = new Motor();
    public static Motor liftMotor = new Motor();
    public static Motor hangingMotor   = new Motor();

    public static void init(HardwareMap hardwareMap) {

        liftMotor.init("liftMotor", hardwareMap);


        liftMotor.setDir(MotorDirection.BACK);

        liftMotor.dev.setZeroPowerBehavior (DcMotor.ZeroPowerBehavior.FLOAT);

        liftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}
