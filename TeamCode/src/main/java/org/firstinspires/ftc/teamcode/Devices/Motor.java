package org.firstinspires.ftc.teamcode.Devices;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;

import java.util.Arrays;


@Config
public class Motor{
    public final DcMotorEx dev;
    private int dir = 1;
    public Motor(String name, HardwareMap map) {
        this.dev = map.get(DcMotorEx.class,name);

    }

    public void setDir(int i){
        if (i == 1 || i ==-1) {
            dir = i;
        }
    }
    PidStatus pidStatus = new PidStatus(0,0,0,0,0,0);
    Pid pid = new Pid(pidStatus);
    public void setVel(double vel){
        double u = pid.calc(vel,getPos());
        setPower(u);
    }
    public void setPower(double power){
        dev.setPower(power*dir);
    }
    double getPos(){
        return dir*dev.getCurrentPosition();
    }
    public void updatePid(PidStatus status){
        pidStatus.copyFrom(status);
    }
    /////////////////////////////////

    public static int K  = 5;
    public static double senseUp = 50;
    public static double bigK    = 0.3;
    public static double smallK  = 0.9;
    private double posOld = 0;
    private double tOld   = 0;
    private final double [] lastReads = new double[2*K+1];
    ElapsedTime timer = new ElapsedTime();

    public double getVel(){
        updateVel();
        filter();
        return vel*dir;
    }

    public void updateVel(){
        double tNow = timer.seconds();
        double dt = tNow-tOld;
        tOld = tNow;

        double posNow = getPos();
        double dp = posNow - posOld;
        posOld = posNow;

        for(int i =0; i<(2*K); i++){
            lastReads[i] = lastReads[i++];
        }

        lastReads[2*K] = dp/dt;
    }
    private double median(){
        double [] sortReads = Arrays.stream(lastReads).sorted().toArray();
        return sortReads[K+1];
    }
    double vel = 0;
    private void filter(){
        double k = 0;
        double vNow = median();
        if(abs(vNow - vel)>senseUp){
            k = bigK;
        }else{
            k = smallK;
        }
        vel = vel + (vNow - vel)*k;
    }



}
