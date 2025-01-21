package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

public enum TwistPosition {
    IN, OUT;
    public static double in = 0.9;
    public static double out = 0.1;
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

