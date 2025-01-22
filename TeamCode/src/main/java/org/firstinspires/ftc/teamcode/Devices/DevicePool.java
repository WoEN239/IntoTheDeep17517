package org.firstinspires.ftc.teamcode.Devices;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;

/*
  Writing by @MrFrosty1234
*/
public class DevicePool {
    private static boolean isLiftInit = false;
    public static void init (HardwareMap hardwareMap) {
        IntakeDevices.init(hardwareMap);
        DriveTrainMotors.init(hardwareMap);
        Sensors.init(hardwareMap);
        if(!isLiftInit) {
            LiftHangingMotors.init(hardwareMap);
            isLiftInit = true;
        }
        Gyro.getInstance().init(hardwareMap);
        Battery.getInstance().init(hardwareMap);
    }
}
