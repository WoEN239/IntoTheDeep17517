package org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner;

import static java.lang.Math.PI;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by EgorKhvostikov
 */

@Config
public class RobotConstant {
    public static double maxAccel = 70;
    public static double minAccel = -70;

    public static double maxLinSpeed = 150;
    public static double maxAngSpeed = 4;

    public static double trackWidth = 0;
    public static double wheelDiameter = 0;

    public static double yMultiplier = 1.25;

    public static double kPForward = 5;
    public static double kPSide = 5;
    public static double kPTurn = 10;

    public static double TICKS_PER_REV = 480;
    public static double MAX_RPM = 300;

    public static double WHEEL_RADIUS = 2;
    public static double GEAR_RATIO = 1;
    public static double TRACK_WIDTH = 1;

    public static double encoderConstant = 480;
    public static double transmission    = 1.26d;
    public static double lightOfWheel    = 9.6d*PI;

    public static double TIK_PER_ANGLE = 97.09166;//TODO
    public static double ENC_TIK_PER_SM = (transmission*encoderConstant)/lightOfWheel;

    public static double odometerConstant = 8192;
    public static double lightOfOdometer = 4.8d*PI;
    public static double SM_PER_ODOMETER_TIK = lightOfOdometer/odometerConstant;




}
