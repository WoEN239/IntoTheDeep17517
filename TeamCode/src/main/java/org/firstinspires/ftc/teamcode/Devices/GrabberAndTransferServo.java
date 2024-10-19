package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Writing by @MrFrosty1234
 */

public class GrabberAndTransferServo {
    HardwareMap hardwareMap;

    public Servo grabberServo;
    public Servo grabberServoUp;

    public Servo transferServoFirst;
    public Servo transferServoSecond;


    public GrabberAndTransferServo (HardwareMap hardwareMap){
        this.hardwareMap    = hardwareMap;
        grabberServo        = hardwareMap.get(Servo.class,   "fakeServo");
        transferServoFirst  = hardwareMap.get(Servo.class,   "fakeServo");
        transferServoSecond = hardwareMap.get(Servo.class,   "fakeServo");
        grabberServoUp      = hardwareMap.get(Servo.class,   "fakeServo");
    }
}
