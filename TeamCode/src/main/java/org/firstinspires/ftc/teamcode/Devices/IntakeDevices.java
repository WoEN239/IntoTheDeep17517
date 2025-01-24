package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/*
 Writing by @MrFrosty1234
*/

public class IntakeDevices {


    public static Servo backWallServo;

    public static Servo flipServoRight;
    public static Servo flipServoLeft;

    public static Servo transferServoLeft;
    public static Servo transferServoRight;

    public static Servo gripServo;
    public static Servo twistServo;

    public static Servo innerTransfer;
    public static Motor brushMotor = new Motor();


    public static void init(HardwareMap hardwareMap) {
        backWallServo      = hardwareMap.get(Servo.class, "grabberServo"      );
        transferServoLeft  = hardwareMap.get(Servo.class, "transferServoLeft" );
        flipServoRight     = hardwareMap.get(Servo.class, "flipServoRight"    );
        transferServoRight = hardwareMap.get(Servo.class, "transferServRight" );
        gripServo          = hardwareMap.get(Servo.class, "afterTransferServo");
        twistServo         = hardwareMap.get(Servo.class, "twistServo"        );
        innerTransfer      = hardwareMap.get(Servo.class, "outRobotServo"     );
        flipServoLeft      = hardwareMap.get(Servo.class, "flipServoLeft"     );

        brushMotor.init("yOdometer", hardwareMap);

        brushMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brushMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
