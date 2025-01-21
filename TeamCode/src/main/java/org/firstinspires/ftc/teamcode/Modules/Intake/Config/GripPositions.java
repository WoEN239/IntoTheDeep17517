package org.firstinspires.ftc.teamcode.Modules.Intake.Config;


public enum GripPositions {
    OPEN, CLOSE;

    public static double open = 0.15;
    public static double close = 0.03;

    public double get(){
        switch (this){
            default:
            case OPEN:
                return open;
            case CLOSE:
                return close;
        }
    }
}
