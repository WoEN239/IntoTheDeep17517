package org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner;

import com.acmerobotics.dashboard.config.Config;

@Config
public class RobotConstant {
    public static double maxAccel = 70;
    public static double minAccel = -70;

    public static double maxLinSpeed = 150;
    public static double maxAngSpeed = 4;

    public static double trackWidth = 0;
    public static double wheelDiameter = 0;

    public static double xMultiplier = 0;

    public static double kPForward = 5;
    public static double kPSide = 5;
    public static double kPTurn = 10;

    public static double TICKS_PER_REV = 480;
    public static double MAX_RPM = 480;

    public static double WHEEL_RADIUS = 2;
    public static double GEAR_RATIO = 1;
    public static double TRACK_WIDTH = 1;

}