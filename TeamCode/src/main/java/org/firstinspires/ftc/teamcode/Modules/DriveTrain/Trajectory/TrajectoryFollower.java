package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

@Config
public abstract class TrajectoryFollower <T extends TrajectorySegment> {
    public static double localRadius        = 60;
    public static double endDetect          = 5 ;

    public static double endDetectAngle    =  3 ;
    public static double localRadiusAngle   = 50 ;

    public boolean isEndNear = false;
    public double targetAngle = 0;

    public abstract void setTrajectorySegment(T trajectorySegment);

    public abstract Position getVirtualTarget(Position p);
}
