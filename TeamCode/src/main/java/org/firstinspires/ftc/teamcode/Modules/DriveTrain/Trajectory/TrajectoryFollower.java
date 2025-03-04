package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

@Config
public abstract class TrajectoryFollower <T extends TrajectorySegment> {
    public static double localRadius        = 30;
    public static double endDetect          = 10 ;

    public static double endDetectAngle    =  10 ;
    public static double localRadiusAngle   = 5 ;

    public abstract void setTrajectorySegment(T trajectorySegment);

    public abstract Position getVirtualTarget(Position p);
}
