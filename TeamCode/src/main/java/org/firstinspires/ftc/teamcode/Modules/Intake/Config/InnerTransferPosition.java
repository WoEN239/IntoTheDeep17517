package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum InnerTransferPosition {
    IN_ROBOT, OUT_ROBOT, CENTER;
    public static double inRobot = 0;
    public static double outRobot = 0.25;
    public static double center   = 0.125;

    public double get(){
        switch (this){
            default:
            case IN_ROBOT:
                return inRobot;
            case OUT_ROBOT:
                return outRobot;
            case CENTER:
                return center;
        }
    }
}
