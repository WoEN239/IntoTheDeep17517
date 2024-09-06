package org.firstinspires.ftc.teamcode.Math;

import static java.lang.Math.*;

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


    private double tLast = System.nanoTime();
    private double errLast = 0;
    private double P;
    private double I;
    private double D;
    private double F;

    public double calc(double target, double pos){
        double err = target - pos;
        double dErr = err - errLast;
        errLast = err;

        double tNow = System.nanoTime();
        double dt = tNow - tLast;
        tLast = tNow;

        P  = kp*err;
        I += ki*err*dt;
        D  = kd*dErr/dt;
        F  = kf*target;
        if(abs(I)>maxI){
            I = maxI*signum(I);
        }
        double u = P+I+D+F;

        if(abs(u)<zeroBorder){
            u = 0;
        }

        return u;
    }
}
