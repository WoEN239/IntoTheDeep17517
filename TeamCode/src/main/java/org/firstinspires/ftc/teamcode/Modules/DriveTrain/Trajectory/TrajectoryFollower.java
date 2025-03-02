package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

@Config
public abstract class TrajectoryFollower <T extends TrajectorySegment> {
    private TrajectorySegment trajectorySegment;
    public static double localRadius = 50;
    public static double endDetect = 10;

    public abstract void setTrajectorySegment(T trajectorySegment);

    public abstract Position getVirtualTarget(Position p);
}
