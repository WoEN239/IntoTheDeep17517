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
    public static double localRadius = 50;

    public double targetLineAngle = 0;
    public double targetEndAngle = 0;

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
        target.h = targetLineAngle;

        Position error = new Position().copyFrom(p).vectorMinus(targetLineSegment.end);

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
