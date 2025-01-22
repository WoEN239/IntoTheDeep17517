package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
  Writing by EgorKhvostikov
*/
@Config
public class LineSegmentFollower {
    public static LineSegment targetLineSegment = new LineSegment();
    public static double localRadius = 55;
    public static double endDetect = 5;
    public static double targetLineAngle = 0;
    public static double targetEndAngle = 0;

    public static boolean isEndNear = false;
    public static Position getVirtualTarget(Position pos){
        Position projection = targetLineSegment.findProjection(pos);

        LineSegment unUnitTargetVector = new LineSegment().makeWithTwoPoint(projection,targetLineSegment.end);
        Position unitTargetVector = unUnitTargetVector.unitVector;

        Position target =  projection.vectorPlus(new Position().copyFrom(unitTargetVector).linearMultiply(localRadius));
        target.h = targetLineAngle;

        Position error = new Position().copyFrom(pos).vectorMinus(targetLineSegment.end);

        if( abs(error.x) < endDetect && abs(error.y) < endDetect  ) {
            isEndNear = true;
            targetLineSegment.end.h = targetEndAngle;
            return targetLineSegment.end;
        }else {
            isEndNear = false ;
            target.h = targetLineAngle;
            return target;
        }
    }
}
