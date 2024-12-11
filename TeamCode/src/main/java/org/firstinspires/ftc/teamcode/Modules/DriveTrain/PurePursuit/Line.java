package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;


import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
public class Line {
    Position start = new Position();
    Position end = new Position();
    Position singleVector = new Position();
    public double k = 0;
    public double b = 0;
    public double errMax = 0;

    public Line makeWithTwoPoint(Position start, Position end) {
        return makeWithTwoPoint(start.x, start.y, end.x, end.y);
    }
    public Line makeWithTwoPoint(double x1, double y1, double x2, double y2){
        double h = Math.atan2(x2-x1,y2-y1);
        start = new Position(x1,y1,h);
        end   = new Position(x2,y2,h);

        k = (y2-y1)/(x2-x1);
        b = (y2-k*x2);

        //x^2+y^2 = 1
        singleVector = new Position(sin(toRadians(h)), cos(toRadians(h)), h);
        return this;
    }

//    public Position findProection(Position p){
//        p.vectorMinus(start);
//        double a = toRadians(start.h);
//        double x = p.x * sin(a)*sin(a) - p.y*sin(2*a)/2.0;
//        double y = p.y * cos(a)*cos(a) - p.x*sin(2*a)/2.0;
//        p.x = x;
//        p.y = y;
//        p.vectorPlus(start);
//        return p;
//  }

    public Position findProection(Position p){
        double k1 = - ( (end.x - start.x)/
                        (end.y - start.y) );
        double b1 = ( p.y + p.x * (end.x - start.x)/
                                  (end.y - start.y) );

        double x = (b - b1)/
                   (k1 - b);
        double y = k1*x + b1;
        return new Position(x,y,90-start.h);
    }


}
