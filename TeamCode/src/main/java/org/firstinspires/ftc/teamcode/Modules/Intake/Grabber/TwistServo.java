package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

public enum TwistServo {
    EAT, DROP, EAT_FROM_WALL;
    public static double eat = 0;
    public static double eatFromWall = 0.1;
    public static double drop = 1;

    public double get(){
        switch (this){
            case EAT:
                return eat;
            case EAT_FROM_WALL:
                return eatFromWall;
            case DROP:
                return drop;
        }
    }
}
