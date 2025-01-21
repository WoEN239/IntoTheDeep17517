package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

public enum BrushMotorPowers {
    STOP, FORWARD, REVERSE;
    public static double forward = -12;
    public static double reverse = 12;
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
