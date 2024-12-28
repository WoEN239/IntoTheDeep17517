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

/*
  Writing by EgorKhvostikov
*/

/*                          x
                            ^
                            |
                            |
                            |
                            |
                            |
                            |
----------------------------|---------------------------->y
                            |
                            |
                            |
                            |
                            |
*/
public class Position {
    public double x;
    public double y;
    public double h;

    public void setH(double h) {
        this.h = h;
    }

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

    public void rotateVector(double angle) {
        double angle1 = Math.toRadians(angle);
        double y1 = y * cos(angle1) - x * sin(angle1);
        x = y * sin(angle1) + x * cos(angle1);
        y = y1;
    }

    public Position vectorMinus(Position pos) {
        x -= pos.x;
        y -= pos.y;
        return this;
    }

    public void positionMinus(Position pos){
        x -= pos.x;
        y -= pos.y;
        h -= pos.h;
    }

    public Position vectorPlus(Position pos) {
        x += pos.x;
        y += pos.y;
        return this;
    }

    public static double normalizeAngle(double error){
        while (abs(error)>180) error-=360*signum(error);
        return error;
    }

    public Position copyFrom(Position p){
        this.x = p.x;
        this.h = p.h;
        this.y = p.y;
        return this;
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

    public void angleMultiply(double k){
        this.h = h*k;
    }

    public Position linearMultiply(double k) {
        this.x = x*k;
        this.y = y*k;
        return this;
    }
    public static double length(Position s, Position e){
        return Math.sqrt((s.x-e.x)*(s.x-e.x) - (s.y-e.y)*(s.y-e.y));
    }

}
