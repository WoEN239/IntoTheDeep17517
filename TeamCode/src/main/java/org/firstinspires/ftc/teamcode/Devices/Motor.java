package org.firstinspires.ftc.teamcode.Devices;



import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;

public class Motor{
    private int id;
    private String name;
    private DcMotorEx dev;
    private int dir = 1;
    public Motor(String name, HardwareMap map) {
        this.dev = map.get(DcMotorEx.class,name);
        this.id = dev.getPortNumber();
        this.name = dev.getDeviceName();
    }

    public void setDir(int i){
        if (i == 1 || i ==-1) {
            dir = i;
        }
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    PidStatus pidStatus = new PidStatus(0,0,0,0,0,0);
    Pid pid = new Pid(pidStatus);
    public void setVel(double vel){
        double u = pid.calc(vel,getPos());
        setPower(u);
    }
    void setPower(double power){
        dev.setPower(power*dir);
    }
    double getPos(){
        return dev.getCurrentPosition();
    }
    double getVel(){
        return dev.getVelocity();//TODO filter
    }
    public void updatePid(PidStatus status){
        pidStatus.copyFrom(status);
    }
}
