package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/*
 Writing by @MrFrosty1234
*/

public class GrabberAndTransferServo {

    public static Servo grabberServo       ;
    public static Servo flipServo          ;
    public static Servo transferServo      ;
    public static Servo transferServoSecond;


    public GrabberAndTransferServo(HardwareMap hardwareMap) {
        grabberServo = hardwareMap.get(Servo.class, "fakeServo");
        transferServo = hardwareMap.get(Servo.class, "fakeServo");
        transferServoSecond = hardwareMap.get(Servo.class, "fakeServo");
        flipServo = hardwareMap.get(Servo.class, "fakeServo");
    }
}
