package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.DeviceValueMap;

/*
  Writing by EgorKhvostikov
*/
public class DevicePositionListener {
    private Motor rightOdometer;
    private Motor leftOdometer ;
    private Motor yOdometer    ;
    private Gyro  gyro         ;

    private final DeviceValueMap valuesMap = new DeviceValueMap();
    public DeviceValueMap getValuesMap() {return valuesMap;}

    public void init() {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer ;
        yOdometer    = DriveTrainMotors.yOdometer    ;
        gyro         = Gyro.getInstance()            ;
    }

    private void deviceUpdate(){
        rightOdometer.update();
        leftOdometer .update();
        yOdometer    .update();
        gyro         .update();
    }

    public void updateValuesMap(){
        deviceUpdate();
        valuesMap.leftOdometer  = leftOdometer.getPosition() ;
        valuesMap.rightOdometer = rightOdometer.getPosition();
        valuesMap.yOdometer     = yOdometer.getPosition()    ;
        valuesMap.gyro          = gyro.getAngle()            ;
    }

}
