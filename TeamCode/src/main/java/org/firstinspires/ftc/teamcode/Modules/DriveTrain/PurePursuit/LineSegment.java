package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;


import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
public class LineSegment {
    //start.h,end.h - angle of line
    public Position start = new Position();
    public Position end = new Position();
    public double lineAngle = 0;

    Position unitVector = new Position();
    double length = 0;

    public double kX = 0;
    public double kY = 0;
    public double c = 0;

    public LineSegment makeWithTwoPoint(Position start, Position end) {
        return makeWithTwoPoint(start.x, start.y, end.x, end.y);
    }
    public LineSegment makeWithTwoPoint(double x1, double y1, double x2, double y2){
        kX = y2 - y1;
        kY = x1 - x2;
        c = -( kY*y1 + kX*x1 );

        double h = Math.atan2(x2 - x1, y2 - y1);
        start = new Position(x1,y1,h);
        end   = new Position(x2,y2,h);
        lineAngle = toDegrees(h);

        unitVector = new Position(sin(h), cos(h), toDegrees(h));

        length = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return this;
    }

    public Position findProjection(Position p){
        double kX1 = kY;
        double kY1 =-kX;
        double c1 =-(kX1*p.x + kY1*p.y);

        double x = 0;
        double y = 0;
        if(kY==0){
            x = -c/kX;
            y =  c1/kX;
        }else if(kX == 0){
            x = - c1/kY;
            y = - c/ kY;
        }else {
            x = - (kY*c1+c)/(kX* (1+ kY*kY));
            y = (kY*x+c1)/kX;
        }

        return new Position(x,y,0);
    }
//    public double findVectorProjectionDirection(double pos1,){
//
//    }
}
