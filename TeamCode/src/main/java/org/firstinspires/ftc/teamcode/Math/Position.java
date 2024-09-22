package org.firstinspires.ftc.teamcode.Math;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Position {
    public double x;
    public double y;
    public double h;

    public Position(double x, double y, double h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }
    public Position(){
        this.x = 0;
        this.y = 0;
        this.h = 0;
    }
    public void rotateIt(double degreesAngle){
        double angle1 = Math.toRadians(degreesAngle);
        double x1 = x * cos(angle1) - y * sin(angle1);
        double y1 = x * sin(angle1) + y * cos(angle1);
        x = x1;
        y = y1;
    }
    public void  minus(Position pos){
        x -= pos.x;
        y -= pos.y;
    }
    public void plus(Position pos){
        x += pos.x;
        y += pos.y;
    }

}
