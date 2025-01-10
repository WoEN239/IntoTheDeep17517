package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/*
 Writing by @MrFrosty1234
*/

public class IntakeServo {

    public static Servo grabberServo;
    public static Servo flipServoRight;
    public static Servo transferServoLeft;
    public static Servo transferServoRight;
    public static Servo afterTransferServo;
    public static Servo outRobotServo;
    public static Servo flipServoLeft;
    public static Servo twistServo;



    public static void init(HardwareMap hardwareMap) {
        grabberServo = hardwareMap.get(Servo.class, "grabberServo");
        transferServoLeft = hardwareMap.get(Servo.class, "transferServoLeft");
        flipServoRight = hardwareMap.get(Servo.class, "flipServoRight");
        transferServoRight = hardwareMap.get(Servo.class, "transferServRight");
        afterTransferServo = hardwareMap.get(Servo.class, "afterTransferServo");
        outRobotServo = hardwareMap.get(Servo.class, "outRobotServo");
        flipServoLeft = hardwareMap.get(Servo.class, "flipServoLeft");
        twistServo = hardwareMap.get(Servo.class, "twistServo");
    }
}
