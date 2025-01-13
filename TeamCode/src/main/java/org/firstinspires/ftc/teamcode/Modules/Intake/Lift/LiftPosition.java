package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;


import com.acmerobotics.dashboard.config.Config;
@Config
public enum LiftPosition {
    DOWN,UP, WALL_EAT,LOW_AXIS, HIGHEST_AXIS,SCORE_AXIS, HIGHEST_BASKET, LOWEST_BASKET, IN_ROBOT;

    public static double down = 0;
    public static double up = 3100;

    public static double waitEat = 550;
    public static double lowAxisGet = 1500;
    public static double highestAxis = 1900;
    public static double scoreAxis  = 1600;

    public static double highestBasket = 2500;
    public static double lowestBasket = 1000;


    public static double inPos = 100;

    public double get() {
        switch (this) {
            default:
            case DOWN:
                return down;
            case HIGHEST_AXIS:
                return highestAxis;
            case LOW_AXIS:
                return lowAxisGet;
            case HIGHEST_BASKET:
                return highestBasket;
            case LOWEST_BASKET:
                return lowestBasket;
            case IN_ROBOT:
                return inPos;
            case UP:
                return up;
            case WALL_EAT:
                return waitEat;
            case SCORE_AXIS:
                return scoreAxis;
        }
    }


}
