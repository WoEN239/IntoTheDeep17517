package org.firstinspires.ftc.teamcode.Modules.DriveTrain.FieldSensors;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

@Config

public class FieldSensors implements Listener {
    Robot robot;

    public static double fieldVoltage = 3;

    public FieldSensorPosition fieldSensorPosition = FieldSensorPosition.NOT_SEEN;

    AnalogInput leftSensor;
    AnalogInput rightSensor;


    public void init(Robot robot) {
        this.robot = robot;
        leftSensor = robot.devicePool.sensors.leftColorSensor;
        rightSensor = robot.devicePool.sensors.rightColorSensor;
    }


    private double getVoltage(AnalogInput sensor) {
        return sensor.getVoltage();
    }

    private void updateState() {
        if (getVoltage(leftSensor) < fieldVoltage && getVoltage(rightSensor) < fieldVoltage) {
            fieldSensorPosition = FieldSensorPosition.ALL_SEEN;
        } else {
            if (getVoltage(leftSensor) > fieldVoltage && getVoltage(rightSensor) < fieldVoltage) {
                fieldSensorPosition = FieldSensorPosition.RIGHT_SEE;
            } else {
                if (getVoltage(leftSensor) < fieldVoltage && getVoltage(rightSensor) > fieldVoltage) {
                    fieldSensorPosition = FieldSensorPosition.LEFT_SEE;
                } else {
                    fieldSensorPosition = FieldSensorPosition.NOT_SEEN;
                }
            }
        }
    }


    @Override
    public void read() {
        updateState();
    }

}
