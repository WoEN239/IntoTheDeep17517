package org.firstinspires.ftc.teamcode.Math;

public class PidStatus {
    public double kp;
    public double ki;
    public double kd;
    public double kf;
    public double maxI;
    public double zeroBorder;

    public PidStatus(double kp, double ki, double kd, double kf, double maxI, double zeroBorder) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.kf = kf;
        this.maxI = maxI;
        this.zeroBorder = zeroBorder;
    }

    public void update(double kp, double ki, double kd, double kf, double maxI, double zeroBorder) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.kf = kf;
        this.maxI = maxI;
        this.zeroBorder = zeroBorder;
    }
    public void copyFrom(PidStatus status){
        this.kp = status.kp;
        this.ki = status.ki;
        this.kd = status.kd;
        this.kf = status.kf;
        this.maxI = status.maxI;
        this.zeroBorder = status.zeroBorder;
    }
}
