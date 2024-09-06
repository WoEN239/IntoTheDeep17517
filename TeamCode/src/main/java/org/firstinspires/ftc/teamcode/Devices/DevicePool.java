package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DevicePool {
    public Motor rightForwardDrive;
    public Motor rightBackDrive;
    public Motor leftForwardDrive;
    public Motor leftBackDrive;
    public  DevicePool(HardwareMap map){
        rightBackDrive = new Motor("",map);
        rightForwardDrive = new Motor("",map);
        leftBackDrive = new Motor("",map);
        leftForwardDrive = new Motor("",map);
    }
}
