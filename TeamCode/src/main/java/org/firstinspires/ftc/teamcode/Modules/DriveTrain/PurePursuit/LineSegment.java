package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;


import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import org.firstinspires.ftc.teamcode.Math.Position;

import java.util.Objects;

/*
  Writing by EgorKhvostikov
*/
public class LineSegment {
    //start.h,end.h - angle of line
    public Position start = new Position();
    public Position end = new Position();

    Position singleVector = new Position();
    Position normalVector = new Position();

    public double k = 0;
    public double b = 0;

    public LineSegment makeWithTwoPoint(Position start, Position end) {
        return makeWithTwoPoint(start.x, start.y, end.x, end.y);
    }
    public LineSegment makeWithTwoPoint(double x1, double y1, double x2, double y2){
        double h = Math.atan2(x2-x1,y2-y1);
        start = new Position(x1,y1,h);
        end   = new Position(x2,y2,h);

        k = (x2-x1)/(y2-y1);
        b = (x2-k*y2);

        //x^2+y^2 = 1
        singleVector = new Position(sin(toRadians(h)), cos(toRadians(h)), h);
        return this;
    }

    public Position findProjection(Position p){
        double k1 = - k;
        double b1 = p.x - p.y*k1;

        double y = (b - b1)/(k1 - k);
        double x = k1*y + b1;

        return new Position(x,y,90-start.h);
    }
}
