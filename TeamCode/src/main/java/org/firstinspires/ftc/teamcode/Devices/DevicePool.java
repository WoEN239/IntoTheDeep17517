package org.firstinspires.ftc.teamcode.Devices;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
  Writing by @MrFrosty1234
*/
public class DevicePool {
    public static void init (HardwareMap hardwareMap) {
        GrabberAndTransferServo.init(hardwareMap);
        DriveTrainMotors.init(hardwareMap);
        Sensors.init(hardwareMap);
        LiftHangingMotors.init(hardwareMap);

    }
}
