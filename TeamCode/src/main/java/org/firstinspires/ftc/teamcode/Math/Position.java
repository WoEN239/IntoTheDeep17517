package org.firstinspires.ftc.teamcode.Math;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.signum;
import static java.lang.Math.sin;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.PoseVelocity2dDual;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Vector2d;

/**
 * Writing by EgorKhvostikov
 */

public class Position {
    public double x;
    public double y;
    public double h;

    public Position(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
        this.h = 0;
    }

    public void rotateIt(double angle1) {
        double x1 = x * cos(angle1) - y * sin(angle1);
        double y1 = x * sin(angle1) + y * cos(angle1);
        x = x1;
        y = y1;
    }

    public void minus(Position pos) {
        x -= pos.x;
        y -= pos.y;
        h -= pos.h;
        h = normalizeAngle(h);
    }

    public void plus(Position pos) {
        x += pos.x;
        y += pos.y;
        h += pos.h;
        h = normalizeAngle(h);
    }

    public static double normalizeAngle(double error){
        while (abs(error)>180) error-=360*signum(error);
        return error;
    }
    public void copyFrom(Position p){
        this.x = p.x;
        this.h = p.h;
        this.y = p.y;

    }
    public Pose2d toRRPose() {
        return new Pose2d(x, y, h);
    }

    public PoseVelocity2d toRRVelocity() {
        return new PoseVelocity2d(new Vector2d(x, y), h);
    }

    public static Position fromRRVelocity(PoseVelocity2dDual<Time> p) {
        return new Position(p.linearVel.x.value(), p.linearVel.y.value(), p.angVel.value());
    }
    public Position linearMultiply(double k) {
        this.x = x*k;
        this.y = y*k;
        return this;
    }
}
