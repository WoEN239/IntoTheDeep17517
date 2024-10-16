package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;


import org.firstinspires.ftc.teamcode.Math.Filter;
import org.firstinspires.ftc.teamcode.Math.FilterStatus;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Robot;

public class Motor{
    public DcMotorEx dev;
    private double velocity = 0;
    private double position = 0;

    private int dir = 1;
    private VoltageSensor battery;

    private String name;

    public void init(String name, HardwareMap map) {
        this.battery = map.voltageSensor.get("Control Hub");
        this.dev = map.get(DcMotorEx.class,name);
        this.name = name;
        filter.setName(name);
        pid.setName(name);
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

    public PidStatus pidStatus = new PidStatus(0,0,0,0,0,0);
    Pid pid = new Pid(pidStatus);

    public FilterStatus filterStatus = new FilterStatus(6,150,30,1,0.1,0.3);
    Filter filter = new Filter().init(filterStatus);

    public void setVel(double velTar){
        pid.setPos(velocity);
        pid.setTarget(velTar);
        pid.update();
        double u = pid.getU();
        setVoltage(u);
    }
    public void setPower(double power){
            dev.setPower(power * dir);

    }
    public void setVoltage(double voltage){
        double u = ((voltage/12.0)*(12.0/battery.getVoltage()));
        Robot.telemetry.addData(name + "voltage",u);
        setPower( u );
    }
    private void updatePos(){
          position =  dir * dev.getCurrentPosition();
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
