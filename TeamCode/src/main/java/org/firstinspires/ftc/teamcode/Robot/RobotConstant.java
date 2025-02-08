package org.firstinspires.ftc.teamcode.Robot;

import static java.lang.Math.PI;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by EgorKhvostikov
 */

@Config
public class RobotConstant {
    public static double maxAccel = 200;
    public static double minAccel = -70;

    public static double maxLinSpeed = 75;
    public static double maxAngSpeed = 4;

    public static double trackWidth = 0;
    public static double wheelDiameter = 0;

    public static double yMultiplier = 1.25;

    public static double kPForward = 5;
    public static double kPSide = 5;
    public static double kPTurn = 10;

    public static double TICKS_PER_REV = 480;
    public static double MAX_RPM = 300;
    public static double MAX_MOTOR_TICKS_VEL = 2400;

    public static double WHEEL_RADIUS = 2;
    public static double GEAR_RATIO = 1;
    public static double TRACK_WIDTH = 1;

    public static double ENCODER_CONSTANT = 480;
    public static double TRANSMISSION = 21d/27;
    public static double LENGTH_OF_WHEEL = 9.6d*PI;

    public static double TIK_PER_ANGLE = 97.09166;//TODO
    public static double ENC_TIK_PER_SM = (TRANSMISSION * ENCODER_CONSTANT)/ LENGTH_OF_WHEEL;

    public static double ODOMETER_CONSTANT = 8192;
    public static double LENGTH_OF_ODOMETER = 4.8d*PI;
    public static double SM_PER_ODOMETER_TIK = LENGTH_OF_ODOMETER / ODOMETER_CONSTANT;




}
