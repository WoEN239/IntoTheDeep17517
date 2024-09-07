package org.firstinspires.ftc.teamcode.Devices;


import org.firstinspires.ftc.teamcode.Robot;

public class DevicePool {
    public Motor rightForwardDrive;
    public Motor rightBackDrive;
    public Motor leftForwardDrive;
    public Motor leftBackDrive;
    public  DevicePool(Robot robot){
        rightBackDrive = new Motor("",robot.hardwareMap,robot.timer);
        rightForwardDrive = new Motor("",robot.hardwareMap,robot.timer);
        leftBackDrive = new Motor("",robot.hardwareMap,robot.timer);
        leftForwardDrive = new Motor("",robot.hardwareMap,robot.timer);
    }
}
