package org.firstinspires.ftc.teamcode.Modules.DriveTrain.SplineFollower;

import static com.acmerobotics.roadrunner.Curves.project;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;

import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.Vector2dDual;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.TrajectoryFollower;

public class PedroPedroFollower extends TrajectoryFollower<SplineTrajectorySegment> {

    private SplineTrajectorySegment path;
    public boolean isEndNear = false;

    public double targetAngle = 0;
    private double lastDisplacement;

    @Override
    public void setTrajectorySegment(SplineTrajectorySegment trajectorySegment) {
        path = trajectorySegment;
    }

    @Override
    public Position getVirtualTarget(Position p) {
        double displacement = project(path.spline,p.toRRPosition().position,lastDisplacement);
        lastDisplacement = displacement;

        Vector2dDual<Arclength> projectRR =  path.spline.get(TrajectoryFollower.localRadius + displacement ,2);
        Position project = new Position(
                projectRR.x.get(0),
                projectRR.y.get(0),
                0
        );

        double h = Math.atan2(projectRR.x.get(1),projectRR.y.get(0));
        Position unitVector = new Position(sin(h), cos(h), toDegrees(h));
        Position linearU = new Position().copyFrom(project).vectorPlus(unitVector.linearMultiply(localRadius));
        boolean linearEndNear =  Math.abs(path.spline.length - displacement) < endDetect;

        double angleU = p.h + localRadiusAngle * Math.signum(targetAngle - p.h);
        boolean angleEndNear = false;
        if(Math.abs(targetAngle - p.h) < endDetectAngle){
            angleEndNear = true;
            angleU = targetAngle;
        }

        isEndNear = linearEndNear && angleEndNear;
        return new Position(
                linearU.x,linearU.y,angleU
        );
    }
}
