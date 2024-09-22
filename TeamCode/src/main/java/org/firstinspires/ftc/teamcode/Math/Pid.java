package org.firstinspires.ftc.teamcode.Math;

import static java.lang.Math.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pid {
    PidStatus status;

    public Pid(PidStatus status) {
        this.status = status;
    }

    private double tLast = (double)System.nanoTime()/(double)ElapsedTime.SECOND_IN_NANO;
    private double errLast = 0;
    private double P = 0;
    private double I = 0;
    private double D = 0;
    private double F = 0;
    private double u = 0;
    private double target = 0;
    private double pos = 0;

    public void setPos(double pos) {
        this.pos = pos;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getU() {
        return u;
    }

    public void update(){
        calc();
        if(status.isTelemetry){
            Robot.telemetry.addData("P",P);
            Robot.telemetry.addData("I",I);
            Robot.telemetry.addData("D",D);
            Robot.telemetry.addData("F",F);
            Robot.telemetry.addData("Target",target);
            Robot.telemetry.addData("position",pos);
            Robot.telemetry.addData("pidU",u);
        }
    }
    private void calc(){
        double err = target - pos;
        double dErr = err - errLast;
        errLast = err;


        double tNow = (double)System.nanoTime()/(double)ElapsedTime.SECOND_IN_NANO;
        double dt = tNow - tLast;
        tLast = tNow;

        P  = status.kp*err;
        I += status.ki*err*dt;
        D  = status.kd*dErr/dt;
        F  = status.kf*target;
        if(abs(I)>status.maxI){
            I = status.maxI*signum(I);
        }
        double u = P+I+D+F;

        if(abs(u)<status.zeroBorder){
            u = 0;
        }
        this.u = u;
    }

}
