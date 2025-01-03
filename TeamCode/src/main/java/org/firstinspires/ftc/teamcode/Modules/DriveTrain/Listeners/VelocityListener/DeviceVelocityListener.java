package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.VelocityListener;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.DeviceValueMap;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class DeviceVelocityListener {
    private Motor rightOdometer;
    private Motor leftOdometer ;
    private Motor yOdometer    ;

    private final DeviceValueMap valuesMap = new DeviceValueMap();
    public DeviceValueMap getValuesMap() {return valuesMap;}

    public void init() {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer ;
        yOdometer    = DriveTrainMotors.yOdometer    ;
    }

    private void deviceUpdate(){
        rightOdometer.update();
        leftOdometer .update();
        yOdometer    .update();
    }

    public void updateValuesMap(){
        deviceUpdate();
        valuesMap.leftOdometer  = leftOdometer.getVelocity();
        valuesMap.rightOdometer = rightOdometer.getVelocity();
        valuesMap.yOdometer     = yOdometer.getVelocity();

    }
}
