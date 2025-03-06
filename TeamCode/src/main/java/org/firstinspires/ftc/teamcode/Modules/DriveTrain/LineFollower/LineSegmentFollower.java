package org.firstinspires.ftc.teamcode.Modules.DriveTrain.LineFollower;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.TrajectoryFollower;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
  Writing by EgorKhvostikov
*/

@Config
public class LineSegmentFollower extends TrajectoryFollower<LineTrajectorySegment> {
    public LineTrajectorySegment targetLineSegment;

    public double targetAngle = 0;
    public boolean isEndNear = false;


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

        LineTrajectorySegment unUnitTargetVector = new LineTrajectorySegment().makeWithTwoPoint(projection,targetLineSegment.end);
        Position unitTargetVector = unUnitTargetVector.unitVector;

        Position linearTarget =  projection.vectorPlus(new Position().copyFrom(unitTargetVector).linearMultiply(localRadius));

        Position linearError = new Position().copyFrom(p).vectorMinus(targetLineSegment.end);
        boolean linearEndNear = false;

        if( abs(linearError.x) < endDetect && abs(linearError.y) < endDetect){
            linearEndNear = true;
            linearTarget.copyFrom(targetLineSegment.end);
        }

        double angleTarget =  p.h + localRadiusAngle * Math.signum(targetAngle - p.h);
        boolean angleEndNear = false;

        if( abs(targetAngle - p.h) < endDetectAngle ) {
            angleEndNear = true;
            angleTarget = targetAngle;
        }

        isEndNear = angleEndNear&&linearEndNear;
        return new Position(
                linearTarget.x,linearTarget.y,angleTarget
        );
    }
}
