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
    public Motor(String name, HardwareMap map) {
        this.dev = map.get(DcMotorEx.class,name);

    }
    ElapsedTime timer = new ElapsedTime();
    public void setDir(int i){
        if (i == 1 || i ==-1) {
            dir = i;
        }
    }
    PidStatus pidStatus = new PidStatus(0,0,0,0,0,0);
    Pid pid = new Pid(pidStatus);
    double posOld = 0;
    public void setVel(double vel){

        double u = pid.calc(vel,this.getVel());


        setPower(u);
    }
    public void setPower(double power){
        dev.setPower(power*dir);
    }
    double getPos(){
        return dev.getCurrentPosition();
    }
    double velL = 0;
    double tOld = 0;
    public double getVel(){
        double tNow = timer.seconds();
        if(tNow - tOld > 1/1000.0) {
            double posNow = dev.getCurrentPosition();
            double vel = filter((posNow - posOld) / (tNow - tOld));
            tOld = tNow;
            posOld = posNow;
            velL = vel;
        }
        return dir * velL;
    }

    public void updatePid(PidStatus status){
        pidStatus.copyFrom(status);
    }
    static public int K = 100;
    double [] lastVel = new double[K];
    int n = 0;

    private double filter(double i){
        lastVel[n] = i;
        n = (n+1)%K;
        i = 0;
        for(int j = 0; j<K;j++){
            i = i +lastVel[j];
        }
        return i/K;
    }
}
