package org.firstinspires.ftc.teamcode.Devices;


import com.qualcomm.robotcore.hardware.HardwareMap;

/*
  Writing by @MrFrosty1234
*/
public class DevicePool {
    private static boolean isInit = false;
    public static void init (HardwareMap hardwareMap) {
        IntakeServo.init(hardwareMap);
        DriveTrainMotors.init(hardwareMap);
        Sensors.init(hardwareMap);
        if(!isInit) {
            LiftHangingMotors.init(hardwareMap);
            isInit = true;
        }
    }
}
