package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePuresuit;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Math.Position;

public class PurePursuit {
    public static Line targetLine = new Line();
    public static double localRadius = 1.5;

    public static Position getVirtualTarget(Position pos){
        Position t = targetLine.findProection(pos);
        t.vectorPlus(new Position().copyFrom(targetLine.singleVector).linearMultiply(localRadius));
        Position error = new Position().copyFrom(pos).vectorMinus(targetLine.end);
        if( abs(error.x) < 5 && abs(error.y) < 5  ) {
            return targetLine.end;
        }else {
            return t;
        }
    }
}
