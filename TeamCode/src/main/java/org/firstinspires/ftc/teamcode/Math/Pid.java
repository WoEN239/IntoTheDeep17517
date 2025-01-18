package org.firstinspires.ftc.teamcode.Math;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class Pid {
    PidStatus status;

    public Pid(PidStatus status) {
        this.status = status;
    }

    public String name;

    public void setName(String n) {
        name = n;
    }

    private double tLast = (double) System.nanoTime() / (double) ElapsedTime.SECOND_IN_NANO;
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

    public void update() {
        calc();
        if (status.isTelemetry) {
            Robot.telemetryPacket.put(name + " P", P);
            Robot.telemetryPacket.put(name + " I", I);
            Robot.telemetryPacket.put(name + " D", D);
            Robot.telemetryPacket.put(name + " F", F);
            Robot.telemetryPacket.put(name + " Target", target);
            Robot.telemetryPacket.put(name + " position", pos);
            Robot.telemetryPacket.put(name + " err", err);
            Robot.telemetryPacket.put(name + " pidU", u);
        }
    }

    double err = 0;

    private void calc() {
        err = target - pos;
        double dErr = err - errLast;
        errLast = err;

        double tNow = (double) System.nanoTime() / (double) ElapsedTime.SECOND_IN_NANO;
        double dt = tNow - tLast;
        tLast = tNow;

        P = status.kp * err;
        I += status.ki * err * dt;
        D = status.kd * dErr / dt;
        F = status.kf0+
            status.kf1*target+
            status.kf2*target*target+
            status.kf3*target*target*target
        ;

        if (abs(I) > status.maxI) {
            I = status.maxI * signum(I);
        }
        double u = P + I + D + F;

        if (abs(u) < status.zeroBorder) {
            u = 0;
        }
        this.u = u;
    }

}
