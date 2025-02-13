package org.firstinspires.ftc.teamcode.Modules.DriveTrain.SplineFollower;

import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.CompositePositionPath;



import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.TrajectorySegment;

public class SplineTrajectorySegment extends TrajectorySegment {
    public CompositePositionPath<Arclength> spline;

    public SplineTrajectorySegment(CompositePositionPath<Arclength> spline) {
        this.spline = spline;
    }
}
