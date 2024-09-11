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
//        rightBackDrive = new Motor("",robot.hardwareMap,robot.timer);
//        rightForwardDrive = new Motor("",robot.hardwareMap,robot.timer);
        leftBackDrive = new Motor("motor",robot.hardwareMap,robot.timer);
//        leftForwardDrive = new Motor("",robot.hardwareMap,robot.timer);
//        rightOdometer = new Motor("",robot.hardwareMap,robot.timer);
//        leftOdometer  = new Motor("",robot.hardwareMap,robot.timer);
//        yOdometer     = new Motor("",robot.hardwareMap,robot.timer);
//
    }
}
