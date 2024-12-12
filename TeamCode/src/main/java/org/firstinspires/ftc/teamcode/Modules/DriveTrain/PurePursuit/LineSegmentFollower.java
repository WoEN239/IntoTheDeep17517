package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
public class LineSegmentFollower {
    public static LineSegment targetLineSegment = new LineSegment();
    public static double localRadius = 1.5;
    public static double endDetect = 1.5;

    public static boolean isEndNear = false;
    public static Position getVirtualTarget(Position pos){
        Position t = targetLineSegment.findProjection(pos);
        t.vectorPlus(new Position().copyFrom(targetLineSegment.singleVector).linearMultiply(localRadius));
        Position error = new Position().copyFrom(pos).vectorMinus(targetLineSegment.end);
        if( abs(error.x) < endDetect && abs(error.y) < endDetect  ) {
            isEndNear = false;
            return targetLineSegment.end;
        }else {
            isEndNear = true ;
            return t;
        }
    }
}
