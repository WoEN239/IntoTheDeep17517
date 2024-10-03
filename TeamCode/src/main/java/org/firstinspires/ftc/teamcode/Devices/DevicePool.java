package org.firstinspires.ftc.teamcode.Devices;


import org.firstinspires.ftc.teamcode.Robot;

public class DevicePool {
    public Motor rightForwardDrive;
    public Motor rightBackDrive;
    public Motor leftForwardDrive;
    public Motor leftBackDrive;
    public Motor rightOdometer;
    public Motor leftOdometer ;
    public Motor yOdometer    ;

    public  DevicePool(Robot robot){
        rightBackDrive = new Motor("fake",robot.hardwareMap);
        rightForwardDrive = new Motor("fake",robot.hardwareMap);
        leftBackDrive = new Motor("motor",robot.hardwareMap);
        leftForwardDrive = new Motor("fake",robot.hardwareMap);
        rightOdometer = new Motor("fake",robot.hardwareMap);
        leftOdometer  = new Motor("fake",robot.hardwareMap);
        yOdometer     = new Motor("fake",robot.hardwareMap);

    }
}
