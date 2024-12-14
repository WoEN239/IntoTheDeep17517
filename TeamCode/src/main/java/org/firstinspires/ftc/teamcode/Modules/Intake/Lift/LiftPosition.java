package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;


import com.acmerobotics.dashboard.config.Config;

@Config
public enum LiftPosition {
    DOWN, LOW_AXIS, HIGHEST_AXIS, HIGHEST_BASKET, LOWEST_BASKET, IN_POSITION;

    public static double down = 0;
    public static double lowAxisGet = 1500;
    public static double highestAxis = 2000;
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
            case IN_POSITION:
                return inPos;
        }
    }


}
