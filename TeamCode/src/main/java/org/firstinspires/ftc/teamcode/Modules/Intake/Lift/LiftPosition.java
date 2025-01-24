package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum LiftPosition {
    DOWN, SCORE_AXIS, HIGHEST_AXIS, HIGHEST_BASKET, LOWEST_BASKET, IN_POSITION, WALL_EAT;


    public static double down = 0;
    public static double eatWait = 610;
    public static double highestAxis = 1900;
    public static double highestBasket = 2500;
    public static double lowestBasket = 2200;

    public static double score = 1300;
    public static double inPos = 500;

    public double get() {
        switch (this) {
            default:
            case DOWN:
                return down;
            case HIGHEST_AXIS:
                return highestAxis;
            case WALL_EAT:
                return eatWait;
            case HIGHEST_BASKET:
                return highestBasket;
            case LOWEST_BASKET:
                return lowestBasket;
            case IN_POSITION:
                return inPos;
            case SCORE_AXIS:
                return score;
        }
    }


}
