package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Writing by @MrFrosty1234
 */

public class GrabberAndTransferServo {

    public static Servo grabberServo       ;
    public static Servo grabberServoUp     ;
    public static Servo transferServoFirst ;
    public static Servo transferServoSecond;


    public GrabberAndTransferServo(HardwareMap hardwareMap) {
        grabberServo = hardwareMap.get(Servo.class, "fakeServo");
        transferServoFirst = hardwareMap.get(Servo.class, "fakeServo");
        transferServoSecond = hardwareMap.get(Servo.class, "fakeServo");
        grabberServoUp = hardwareMap.get(Servo.class, "fakeServo");
    }
}
