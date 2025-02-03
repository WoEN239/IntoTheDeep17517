package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners;

/*
  Writing by EgorKhvostikov
*/
public class DeviceValueMap {
    public double rightOdometer = 0;
    public double leftOdometer  = 0;
    public double yOdometer     = 0;
    public double gyro          = 0;

    public void copyFrom(DeviceValueMap m){
        this.rightOdometer  = m.rightOdometer;
        this.leftOdometer   = m.leftOdometer ;
        this.yOdometer      = m.yOdometer    ;
        this.gyro           = m.gyro         ;
    }
}
