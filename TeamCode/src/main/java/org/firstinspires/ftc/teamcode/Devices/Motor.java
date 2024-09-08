package org.firstinspires.ftc.teamcode.Devices;



import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;

public class Motor{
    private int id;
    private String name;
    private DcMotorEx dev;
    private int dir = 1;
    private ElapsedTime timer;
    public Motor(String name, HardwareMap map, ElapsedTime timer) {
        this.dev = map.get(DcMotorEx.class,name);
        this.id = dev.getPortNumber();
        this.name = dev.getDeviceName();
        this.timer = timer;
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


    double timeOld = timer.seconds();
    double velTrueK0 = 0;
    double velTrueK = 0;
    public static double K = 1;
    double getVel(){
        double tNow = timer.seconds();
        double dt = tNow- timeOld;
        double aK = (velTrueK-velTrueK0)/dt;

        double velSensorK1 = dev.getVelocity();

        double velTrueK1 = K*velSensorK1 + (1-K)*(velTrueK + aK*dt);
        timeOld = tNow;
        velTrueK0 = velTrueK;
        velTrueK = velTrueK1;

        return velTrueK1;
    }
    public void updatePid(PidStatus status){
        pidStatus.copyFrom(status);
    }
}
