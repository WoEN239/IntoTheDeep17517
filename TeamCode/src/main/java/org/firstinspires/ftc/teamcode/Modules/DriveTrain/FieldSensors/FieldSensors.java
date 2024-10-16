package org.firstinspires.ftc.teamcode.Modules.DriveTrain.FieldSensors;

import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Robot;

public class FieldSensors implements Controller {
   Robot robot;

   AnalogInput leftSensor;
   AnalogInput rightSensor;

    public void init(Robot robot) {
        this.robot = robot;

        leftSensor = robot.devicePool.sensors.leftColorSensor;
        rightSensor = robot.devicePool.sensors.rightColorSensor;
    }

    private boolean isField = false;


}
