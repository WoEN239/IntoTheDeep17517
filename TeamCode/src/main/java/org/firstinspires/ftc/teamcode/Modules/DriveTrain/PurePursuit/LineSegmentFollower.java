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
    public LineSegment targetLineSegment;
    public static double localRadius = 15;
    public static double endDetect = 10;

    public double targetLineAngle = 0;
    public double targetEndAngle = 0;

    public boolean isEndNear = false;

    public LineSegmentFollower(LineSegment targetLineSegment, double localRadius, double endDetect, double targetLineAngle, double targetEndAngle) {
        this.targetLineSegment = targetLineSegment;
        this.localRadius = localRadius;
        this.endDetect   = endDetect;
        this.targetLineAngle = targetLineAngle;
        this.targetEndAngle = targetEndAngle;
    }

    public LineSegmentFollower(LineSegment targetLineSegment) {
        this.targetLineSegment = targetLineSegment;
    }

    public Position getVirtualTarget(Position pos){
        Position projection = targetLineSegment.findProjection(pos);

        LineSegment unUnitTargetVector = new LineSegment().makeWithTwoPoint(projection,targetLineSegment.end);
        Position unitTargetVector = unUnitTargetVector.unitVector;

        Position target =  projection.vectorPlus(new Position().copyFrom(unitTargetVector).linearMultiply(localRadius));
        target.h = targetLineAngle;

        Position error = new Position().copyFrom(pos).vectorMinus(targetLineSegment.end);

        Robot.telemetryPacket.fieldOverlay().
                strokeLine(targetLineSegment.start.x, targetLineSegment.start.y,
                        targetLineSegment.end.x, targetLineSegment.end.y);


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
