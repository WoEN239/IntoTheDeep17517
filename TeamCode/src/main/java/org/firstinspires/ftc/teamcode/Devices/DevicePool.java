package org.firstinspires.ftc.teamcode.Devices;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.GrabberPosition;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by @MrFrosty1234
 */
public class DevicePool {

    HardwareMap hardwareMap;

    public GrabberAndTransferServo grabber;
    public DriveTrainMotors driveTrainMotors;
    public Sensors sensors;
    public LiftHangingMotors liftHangingMotors;

    public  DevicePool(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        grabber = new GrabberAndTransferServo(hardwareMap);
        driveTrainMotors = new DriveTrainMotors(hardwareMap);
        sensors = new Sensors(hardwareMap);
        liftHangingMotors = new LiftHangingMotors(hardwareMap);

    }
}
