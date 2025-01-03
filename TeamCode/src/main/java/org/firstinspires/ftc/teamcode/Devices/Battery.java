package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.ArrayExtra;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
  Writing by EgorKhvostikov
*/
public class Battery {
    private static Battery instance;
    public static Battery getInstance() {
        if(instance == null){
            instance = new Battery();
        }
        return instance;
    }
    private Battery(){}
    private VoltageSensor battery;
    private final ElapsedTime timer = new ElapsedTime();

    private boolean isUnInit = true;
    public void init(HardwareMap hardwareMap) {
        if(isUnInit) {
            battery = hardwareMap.voltageSensor.get("Control Hub");
        }
        isUnInit = false;
    }
    private final double [] reads = new double[5];

    public void update(){
        if(!Robot.isDebug) {
            if (timer.seconds() > 0.5) {
                ArrayExtra.updateLikeBuffer(battery.getVoltage(), reads);
                Robot.voltage = ArrayExtra.findMedian(reads);
                timer.reset();
            }
        }
    }
}
