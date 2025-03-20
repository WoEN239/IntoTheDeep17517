package org.firstinspires.ftc.teamcode.Modules.DriveTrain.LineFollower;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.TrajectoryFollower;

/*
  Writing by EgorKhvostikov
*/

@Config
public class LineSegmentFollower extends TrajectoryFollower<LineTrajectorySegment> {
    public LineTrajectorySegment targetLineSegment;

    public LineSegmentFollower(LineTrajectorySegment targetLineSegment) {
        this.targetLineSegment = targetLineSegment;
    }

    @Override
    public void setTrajectorySegment(LineTrajectorySegment trajectorySegment) {
        targetLineSegment = trajectorySegment;
    }

    @Override
    public Position getVirtualTarget(Position p){
        Position projection = targetLineSegment.findProjection(p);

        LineTrajectorySegment unUnitTargetVector = new LineTrajectorySegment().makeFromTwoPoint(projection,targetLineSegment.end);
        Position unitTargetVector = unUnitTargetVector.unitVector;

        Position linearU =  projection.vectorPlus(new Position().copyFrom(unitTargetVector).linearMultiply(localRadius));

        double xError = new Position().copyFrom(p).vectorMinus(targetLineSegment.end).x;
        double yError = new Position().copyFrom(p).vectorMinus(targetLineSegment.end).y;
        boolean linearEndNearX = false;
        boolean linearEndNearY = false;

        if( abs(xError) < endDetectX){
            linearEndNearX = true;
            linearU.x= targetLineSegment.end.x;
        }
        if( abs(yError) < endDetectY){
            linearEndNearY = true;
            linearU.y= targetLineSegment.end.y;
        }

        double angleU =  p.h + localRadiusAngle * Math.signum(targetAngle - p.h);
        boolean angleEndNear = false;

        if( abs(Position.normalizeAngle(targetAngle - p.h)) < endDetectAngle ) {
            angleEndNear = true;
            angleU = targetAngle;
        }

        isEndNear = angleEndNear&&linearEndNearX && linearEndNearY;
        return new Position(
                linearU.x,linearU.y,angleU
        );
    }
}
