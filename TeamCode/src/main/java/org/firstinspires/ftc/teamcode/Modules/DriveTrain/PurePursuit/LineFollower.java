package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
public class LineFollower {
    public static Line targetLine = new Line();
    public static double localRadius = 1.5;
    public static double endDetect = 1.5;

    public static boolean isEndNear = false;
    public static Position getVirtualTarget(Position pos){
        Position t = targetLine.findProjection(pos);
        t.vectorPlus(new Position().copyFrom(targetLine.singleVector).linearMultiply(localRadius));
        Position error = new Position().copyFrom(pos).vectorMinus(targetLine.end);
        if( abs(error.x) < endDetect && abs(error.y) < endDetect  ) {
            isEndNear = false;
            return targetLine.end;
        }else {
            isEndNear = true ;
            return t;
        }
    }
}
