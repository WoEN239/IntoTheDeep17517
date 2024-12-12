package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.ArrayExtra;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
  Writing by EgorKhvostikov
*/
public class Battery implements Listener {

    Robot robot;
    public VoltageSensor battery;
    private final ElapsedTime timer = new ElapsedTime();
    @Override
    public void init(Robot robot) {
        this.robot = robot;
        battery = robot.hardwareMap.voltageSensor.get("Control Hub");
    }
    private final double [] reads = new double[5];

    @Override
    public void read(){
        if(timer.seconds()>0.5) {
            ArrayExtra.updateLikeBuffer(battery.getVoltage(),reads);
            Robot.voltage = ArrayExtra.findMedian(reads);
            timer.reset();
        }
    }
}
