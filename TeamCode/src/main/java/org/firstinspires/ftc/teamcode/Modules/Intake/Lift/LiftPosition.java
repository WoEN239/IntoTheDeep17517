package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;


import com.acmerobotics.dashboard.config.Config;

@Config
public enum LiftPosition {
    UP, DOWN, LOW_AXIS_GET, HIGHEST_AXIS, HIGHEST_BASKET, LOWEST_BASKET;

    public static double down = 0;
    public static double up = 0;
    public static double lowAxisGet = 0;
    public static double highestAxis = 0;
    public static double highestBasket = 0;
    public static double lowestBasket = 0;

    public double get(){
        switch (this){
            default:
            case UP:
                return up;
            case DOWN:
                return down;
            case HIGHEST_AXIS:
                return highestAxis;
            case LOW_AXIS_GET:
                return lowAxisGet;
            case HIGHEST_BASKET:
                return highestBasket;
            case LOWEST_BASKET:
                return lowestBasket;
        }
    }


}
