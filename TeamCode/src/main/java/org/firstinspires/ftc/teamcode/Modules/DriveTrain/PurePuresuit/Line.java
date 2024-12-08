package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePuresuit;


import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import org.firstinspires.ftc.teamcode.Math.Position;

public class Line {
    Position start = new Position();
    Position end = new Position();
    Position singleVector = new Position();

    public double errMax = 0;

    public void makeWithTwoPoint(double x1, double y1, double x2, double y2){
        double h = Math.atan2(x2-x1,y2-y1);
        start = new Position(x1,y1,h);
        end   = new Position(x2,y2,h);

        //x^2+y^2 = 1
        singleVector = new Position(sin(toRadians(h)), cos(toRadians(h)), h);

    }
    public Position findProection(Position p){
        p.vectorMinus(start);
        p.rotateVector(start.h);
        p.y = 0;
        p.rotateVector(-start.h);
        p.vectorPlus(start);
        return p;
    }

}
