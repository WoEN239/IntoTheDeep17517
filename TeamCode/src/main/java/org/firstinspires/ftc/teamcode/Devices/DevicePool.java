package org.firstinspires.ftc.teamcode.Devices;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.GrabberPosition;
import org.firstinspires.ftc.teamcode.Robot;

public class DevicePool {
    public Motor rightOdometer;
    public Motor leftOdometer ;
    public Motor yOdometer    ;
    public Motor leftLiftMotor;
    public Motor rightLiftMotor;

    HardwareMap hardwareMap;

    public GrabberAndTransferServo grabber;
    public DriveTrainMotors driveTrainMotors;
    public  Odometrs odometrs;

    public  DevicePool(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        grabber = new GrabberAndTransferServo(hardwareMap);
        driveTrainMotors = new DriveTrainMotors(hardwareMap);
        odometrs = new Odometrs(hardwareMap);


    }
}
