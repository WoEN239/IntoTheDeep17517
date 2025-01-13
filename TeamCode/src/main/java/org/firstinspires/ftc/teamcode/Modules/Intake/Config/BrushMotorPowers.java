package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

public enum BrushMotorPowers {
    STOP, FORWARD, REVERSE;
    public static double forward = 1;
    public static double reverse = -1;
    public static double stop = 0;

    public double get(){
        switch (this){
            default:
            case FORWARD:
                return forward;
            case STOP:
                return stop;
            case REVERSE:
                return reverse;
        }
    }
}
