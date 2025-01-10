package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum PowerBrush {
    FORWARD, REVERSE, STOP;

    public static double forward = 1;
    public static double reverse = -1;
    public static double stop = 0;


    public double get(){
        switch (this){
            case FORWARD:
                return forward;
            case REVERSE:
                return reverse;
            case STOP:
                return stop;
        }
    }
}
