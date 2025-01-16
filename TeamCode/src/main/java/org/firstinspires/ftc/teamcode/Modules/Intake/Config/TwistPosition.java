package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

public enum TwistPosition {
    IN, OUT;
    public static double in = 1;
    public static double out = 0;
    public double get() {
        switch (this) {
            default:
            case IN:
                return in;
            case OUT:
                return out;
        }
    }
}

