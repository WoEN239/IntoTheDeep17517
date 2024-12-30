package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;


import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

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
        kY = -(x2 - x1);
        c =  -(kY*y1 + kX*x1) ;

        double h = Math.atan2(x2 - x1, y2 - y1);
        start = new Position(x1,y1,h);
        end   = new Position(x2,y2,h);
        lineAngle = toDegrees(h);

        unitVector = new Position(sin(h), cos(h), toDegrees(h));

        length = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        return this;
    }

    public Position findProjection(Position p){
        double kX1 = -kY;
        double kY1 = kX;
        double c1 =  -(kX1*p.x + kY1*p.y);

        double x = 0;
        double y = 0;

        x =  (kY1*c-c1*kY) / (kX1*kY - kY1*kX);
        y =  (c1*kX-kX1*c) / (kX1*kY - kY1*kX);


        if(Double.isNaN(x) || Double.isNaN(y)){
            return new Position();
        }

        FieldView.packet.fieldOverlay().strokeLine(p.x,p.y, x,y);

        return new Position(x,y,0);
    }

    @NonNull
    @Override
    public String toString(){
        return "from " + start.toString() + " to " + end.toString();
    }
}
