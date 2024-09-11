package org.firstinspires.ftc.teamcode.Devices;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
@Config
public class Motor{
    public final DcMotorEx dev;
    private int dir = 1;
    private ElapsedTime timer = new ElapsedTime();
    public Motor(String name, HardwareMap map, ElapsedTime timer) {
        this.dev = map.get(DcMotorEx.class,name);
        for (int i = 0; i < K; i++) {
            lastVels[i] = 0;
        }
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
        return dev.getCurrentPosition();
    }


    double timeOld = timer.seconds();
    double velTrueK0 = 0;
    double velTrueK = 0;
    public static int K = 1;
    double [] lastVels  = new double[K];
    public double getVel(){
        double velSensorK1 = dev.getVelocity();
        double sum = 0;

        for (int i = 0; i < lastVels.length-1; i++) {
            lastVels[i] = lastVels[i+1];
        }
        lastVels[K-1] = velSensorK1;
        for (double i: lastVels
             ) {
            sum+=i;
        }
        return sum/K;
    }
    public void updatePid(PidStatus status){
        pidStatus.copyFrom(status);
    }
}
