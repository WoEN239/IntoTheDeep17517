package org.firstinspires.ftc.teamcode.Devices;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;

/*
  Writing by @MrFrosty1234
*/
public class DevicePool {
    public static void init (HardwareMap hardwareMap) {
        IntakeDevices.init(hardwareMap);
        DriveTrainMotors.init(hardwareMap);
        Sensors.init(hardwareMap);
        LiftHangingMotors.init(hardwareMap);
        Gyro.getInstance().init(hardwareMap);
        Battery.getInstance().init(hardwareMap);
    }
}
