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
    public static double localRadius       = 100;
    public static double endDetectAngle    =  5 ;

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

        Position target =  projection.vectorPlus(new Position().copyFrom(unitTargetVector).linearMultiply(localRadius));
        target.h = targetAngle;

        Position error = new Position().copyFrom(p).positionMinus(targetLineSegment.end);

        if( abs(error.x) < endDetect && abs(error.y) < endDetect  && abs(targetAngle - p.h) < endDetectAngle) {
            isEndNear = true;
            targetLineSegment.end.h = targetAngle;
            target.h = p.h;

            return targetLineSegment.end;
        }else {
            isEndNear = false;
            target.h = p.h + localRadiusAngle*Math.signum(targetAngle - p.h);
            return target;
        }
    }
}
