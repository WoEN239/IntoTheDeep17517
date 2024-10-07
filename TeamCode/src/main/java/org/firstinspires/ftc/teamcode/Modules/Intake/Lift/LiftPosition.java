package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;


import com.acmerobotics.dashboard.config.Config;

@Config
public enum LiftPosition {
    UP, DOWN, LOW_AXIS_GET, HIGHEST_AXIS;

    public static int down = 0;
    public static int up = 0;
    public static int lowAxisGet = 0;
    public static int highestAxis = 0;

    public int get(){
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
        }
    }


}
