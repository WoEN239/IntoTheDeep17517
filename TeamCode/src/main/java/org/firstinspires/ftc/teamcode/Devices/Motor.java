package org.firstinspires.ftc.teamcode.Devices;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;


import org.firstinspires.ftc.teamcode.Math.Filter;
import org.firstinspires.ftc.teamcode.Math.FilterStatus;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;

import java.util.Objects;


@Config
public class Motor{
    public DcMotorEx dev;
    private double velocity = 0;
    private double position = 0;

    private boolean isInit = true;
    private int dir = 1;
    private final VoltageSensor battery;

    public Motor(String name, HardwareMap map) {
        this.battery = map.voltageSensor.get("Control hub");
        if (!Objects.equals(name, "")){
            this.dev = map.get(DcMotorEx.class,name);
        }else{
            isInit = false;
        }
    }
    public void update(){
        updatePos();
        updateVel();
    }

    public void setDir(int i){
        if (i == 1 || i ==-1) {
            dir = i;
        }
    }
    public static PidStatus pidStatus = new PidStatus(0,0,0,0,0,0);
    Pid pid = new Pid(pidStatus);

    public static FilterStatus filterStatus = new FilterStatus(0,0,0,0,0);
    Filter filter = new Filter(filterStatus);

    public void setVel(double velTar){
        pid.setPos(velocity);
        pid.setTarget(velTar);
        pid.update();
        double u = pid.getU();
        setVoltage(u);
    }
    public void setPower(double power){
        if(isInit) {
            dev.setPower(power * dir);
        }
    }
    public void setVoltage(double voltage){
        if(isInit){
            dev.setPower(dir*((12/battery.getVoltage())*voltage)/12);
        }
    }
    private void updatePos(){
      if(isInit) {
          position =  dir * dev.getCurrentPosition();
      }
    }
    private void updateVel(){
        filter.setPos(position);
        filter.update();
        velocity = filter.getVelocity();
    }

    public double getVelocity() {
        return velocity;
    }

    public double getPosition() {
        return position;
    }
}
