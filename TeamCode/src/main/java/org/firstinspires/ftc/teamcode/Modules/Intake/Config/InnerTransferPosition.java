package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

public enum InnerTransferPosition {
    IN_ROBOT, OUT_ROBOR;
    public static double inRobot = 0;
    public static double outRobot = 0;

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
