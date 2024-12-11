package org.firstinspires.ftc.teamcode.Devices;


import com.qualcomm.robotcore.hardware.HardwareMap;

/*
  Writing by @MrFrosty1234
*/
public class DevicePool {
    public static void init (HardwareMap hardwareMap) {
        IntakeServo.init(hardwareMap);
        DriveTrainMotors.init(hardwareMap);
        Sensors.init(hardwareMap);
        LiftHangingMotors.init(hardwareMap);

    }
}
