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
    public static Servo rotateServo        ;


    public static void init(HardwareMap hardwareMap) {
        grabberServo = hardwareMap.get(Servo.class, "grabberServo");
        transferServo = hardwareMap.get(Servo.class, "transferServo");
        flipServo = hardwareMap.get(Servo.class, "flipServo");
        rotateServo = hardwareMap.get(Servo.class, "rotateServo");
    }
}
