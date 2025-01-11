package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

public enum TwistServo {
    EAT, DROP, EAT_FROM_WALL;
    public static double eat = 0.375;
    public static double eatFromWall = 1;
    public static double drop = 0.9;

    public double get() {
        switch (this) {
            default:
            case EAT:
                return eat;
            case EAT_FROM_WALL:
                return eatFromWall;
            case DROP:
                return drop;
        }
    }
}
