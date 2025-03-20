package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum LiftPosition {
    DOWN, SCORE_AXIS, HIGHEST_AXIS, HIGHEST_BASKET, SWIPE, IN_POSITION, WALL_EAT, LAUNCH;


    public static double down = 0;
    public static double eatWait = 450;
    public static double highestAxis = 1900;
    public static double highestBasket = 2500;
    public static double swipe = 700;

    public static double score = 850;
    public static double inPos = 450;
    public static double launch = 1500;

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
            case SWIPE:
                return swipe;
            case IN_POSITION:
                return inPos;
            case SCORE_AXIS:
                return score;
            case LAUNCH:
                return launch;
        }
    }


}
