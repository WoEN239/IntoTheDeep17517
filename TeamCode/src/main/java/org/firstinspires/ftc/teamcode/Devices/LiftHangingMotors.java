package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class LiftHangingMotors {
    HardwareMap hardwareMap;

    public DcMotorEx liftRightMotor;
    public DcMotorEx liftLeftMotor;

    public DcMotorEx hangingMotor;

    public  LiftHangingMotors (HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        liftLeftMotor = hardwareMap.get(DcMotorEx.class,  "fakeMotor");
        liftRightMotor = hardwareMap.get(DcMotorEx.class, "fakeMotor");
        hangingMotor = hardwareMap.get(DcMotorEx.class,   "fakeMotor");

        liftRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        liftRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        hangingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        hangingMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangingMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
