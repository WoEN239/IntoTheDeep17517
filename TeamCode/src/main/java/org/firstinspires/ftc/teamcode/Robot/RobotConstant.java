package org.firstinspires.ftc.teamcode.Robot;

import static java.lang.Math.PI;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by EgorKhvostikov
 */

@Config
public class RobotConstant {
    public static double maxAccel = 200;

    public static double maxLinSpeed = 75;

    public static double Y_MULTIPLIER = 1.25;


    public static double MAX_MOTOR_TICKS_VEL = 2400;

    public static double ENCODER_CONSTANT = 480;
    public static double TRANSMISSION = 21d/27;
    public static double LENGTH_OF_WHEEL = 9.6d*PI;

    public static double TIK_PER_ANGLE = 97.09166;//TODO
    public static double ENC_TIK_PER_SM = (TRANSMISSION * ENCODER_CONSTANT)/ LENGTH_OF_WHEEL;

    public static double ODOMETER_CONSTANT = 8192;
    public static double LENGTH_OF_ODOMETER = 4.8d*PI;
    public static double SM_PER_ODOMETER_TIK = LENGTH_OF_ODOMETER / ODOMETER_CONSTANT;




}
