package org.firstinspires.ftc.teamcode.Math;

import static java.lang.Math.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pid {
    private static final Logger log = LoggerFactory.getLogger(Pid.class);
    double kp;
    double kd;
    double ki;
    double kf;
    double maxI;
    double zeroBorder;
    PidStatus status;
    public Pid(double kp, double kd, double ki,double kf, double maxI, double zeroBorder) {
        this.kp = kp;
        this.kd = kd;
        this.ki = ki;
        this.kf = kf;
        this.maxI = maxI;
        this.zeroBorder = zeroBorder;
    }

    public Pid(PidStatus status) {
        this.kp = status.kp;
        this.kd = status.kd;
        this.ki = status.ki;
        this.kf = status.kf;
        this.maxI = status.maxI;
        this.zeroBorder = status.zeroBorder;
        this.status = status;
    }
    public void updateStatus(PidStatus status) {
        this.kp = status.kp;
        this.kd = status.kd;
        this.ki = status.ki;
        this.kf = status.kf;
        this.maxI = status.maxI;
        this.zeroBorder = status.zeroBorder;
    }
    public void updateStatus(double kp, double kd, double ki,double kf) {
        this.kp = kp;
        this.kd = kd;
        this.ki = ki;
        this.kf = kf;
    }


    private double tLast = (double)System.nanoTime()/(double)ElapsedTime.SECOND_IN_NANO;
    private double errLast = 0;
    private double P;
    private double I;
    private double D;
    private double F;

    public double calc(double target, double pos){
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
        FtcDashboard.getInstance().getTelemetry().addData("power",u);
        return u;
    }
}
