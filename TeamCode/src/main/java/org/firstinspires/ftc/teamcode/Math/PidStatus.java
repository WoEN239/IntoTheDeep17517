package org.firstinspires.ftc.teamcode.Math;

/**
 * Writing by EgorKhvostikov
 */

public class PidStatus {
    public double kp;
    public double ki;
    public double kd;
    public double kf0;
    public double kf1;
    public double kf2;
    public double kf3;

    public double maxI;
    public double zeroBorder;
    public boolean isTelemetry = false;

    public void setTelemetry(boolean telemetry) {
        isTelemetry = telemetry;
    }

    public PidStatus(double kp, double ki, double kd, double kf0,double kf1,double kf2,double kf3, double maxI, double zeroBorder) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.kf0 = kf0;
        this.kf1 = kf1;
        this.kf2 = kf2;
        this.kf3 = kf3;
        this.maxI = maxI;
        this.zeroBorder = zeroBorder;
    }

    public void copyFrom(PidStatus status) {
        this.kp = status.kp;
        this.ki = status.ki;
        this.kd = status.kd;
        this.kf0 = status.kf0;
        this.kf1 = status.kf1;
        this.kf2 = status.kf2;
        this.kf3 = status.kf3;
        this.maxI = status.maxI;
        this.zeroBorder = status.zeroBorder;
    }
}
