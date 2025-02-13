package org.firstinspires.ftc.teamcode.Modules.DriveTrain.SplineFollower;

import static com.acmerobotics.roadrunner.Curves.project;

import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.Vector2dDual;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.TrajectoryFollower;

public class SplineFollower extends TrajectoryFollower <SplineTrajectorySegment>{

    public double targetAngle = 0;
    public SplineTrajectorySegment path;
    private double lastDisplacement = 0;
    public boolean isEndNear = false;

    @Override
    public void setTrajectorySegment(SplineTrajectorySegment p){
       path = p;
    }

    @Override
    public Position getVirtualTarget(Position p){

        double displacement = project(path.spline,p.toRRPosition().position,lastDisplacement);
        lastDisplacement = displacement;

        Vector2dDual<Arclength> target =  path.spline.get(TrajectoryFollower.localRadius + displacement ,1);
        Vector2dDual<Arclength> project = path.spline.get(displacement,1);

        double xT = target.x.get(0);
        double yT = target.y.get(0);

        double xP = project.x.get(0);
        double yP = project.y.get(0);

        isEndNear =  Math.abs(path.spline.length - displacement) < endDetect;

        return new Position(xT,yT, targetAngle);
    }

}
