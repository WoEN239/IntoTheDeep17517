package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum OutServoPosition {
    IN_ROBOT, OUT_ROBOR;
    public static double inRobot = 0.325;
    public static double outRobot = 0.7;

    public double get(){
        switch (this){
            default:
            case IN_ROBOT:
                return inRobot;
            case OUT_ROBOR:
                return outRobot;
        }
    }
}
