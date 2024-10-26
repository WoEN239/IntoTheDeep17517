package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

public class Battery implements Listener {


    Robot robot;
    public VoltageSensor battery;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        battery = robot.hardwareMap.voltageSensor.get("Control Hub");
    }

    @Override
    public void read(){
        Robot.voltage = battery.getVoltage();
    }
}
