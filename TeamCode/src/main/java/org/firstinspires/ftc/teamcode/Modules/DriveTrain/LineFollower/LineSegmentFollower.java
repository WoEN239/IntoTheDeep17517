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
        Robot.telemetryPacket.put("angle p I",p.h);

        LineTrajectorySegment unUnitTargetVector = new LineTrajectorySegment().makeWithTwoPoint(projection,targetLineSegment.end);
        Position unitTargetVector = unUnitTargetVector.unitVector;

        Position linearU =  projection.vectorPlus(new Position().copyFrom(unitTargetVector).linearMultiply(localRadius));

        Position linearError = new Position().copyFrom(p).vectorMinus(targetLineSegment.end);
        boolean linearEndNear = false;

        if( abs(linearError.x) < endDetect && abs(linearError.y) < endDetect){
            linearEndNear = true;
            linearU.copyFrom(targetLineSegment.end);
        }

        double angleU =  p.h + localRadiusAngle * Math.signum(targetAngle - p.h);
        boolean angleEndNear = false;

        if( abs(Position.normalizeAngle(targetAngle - p.h)) < endDetectAngle ) {
            angleEndNear = true;
            angleU = targetAngle;
        }

        Robot.telemetryPacket.put("angle end",angleEndNear);
        Robot.telemetryPacket.put("angle target",targetAngle);
        Robot.telemetryPacket.put("angle p U",p.h);


        isEndNear = angleEndNear&&linearEndNear;
        return new Position(
                linearU.x,linearU.y,angleU
        );
    }
}
