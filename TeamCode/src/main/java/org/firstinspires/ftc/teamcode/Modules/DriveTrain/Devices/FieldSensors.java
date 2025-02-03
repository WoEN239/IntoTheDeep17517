package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices;

import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Robot.Robot;
public class FieldSensors  {

    public static double fieldVoltageLeft = 100;
    public static double fieldVoltageRight = 100;

    public FieldSensorPosition fieldSensorPosition = FieldSensorPosition.NOT_SEEN;

    AnalogInput leftSensor;
    AnalogInput rightSensor;

    public void init() {
        leftSensor  = Sensors.leftColorSensor;
        rightSensor = Sensors.rightColorSensor;
    }

    private double getVoltage(AnalogInput sensor) {
        return sensor.getVoltage();
    }

    private void updateState() {
        if (getVoltage(leftSensor) < fieldVoltageLeft && getVoltage(rightSensor) < fieldVoltageRight) {
            fieldSensorPosition = FieldSensorPosition.ALL_SEEN;
        } else if (getVoltage(leftSensor) > fieldVoltageLeft && getVoltage(rightSensor) < fieldVoltageRight) {
            fieldSensorPosition = FieldSensorPosition.RIGHT_SEE;
        } else if (getVoltage(leftSensor) < fieldVoltageLeft && getVoltage(rightSensor) > fieldVoltageRight) {
            fieldSensorPosition = FieldSensorPosition.LEFT_SEE;
        } else {
            fieldSensorPosition = FieldSensorPosition.NOT_SEEN;
        }
    }

    public void read() {
        updateState();
    }

}
