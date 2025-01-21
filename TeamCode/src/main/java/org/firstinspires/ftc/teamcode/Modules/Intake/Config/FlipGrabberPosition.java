package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config
public enum FlipGrabberPosition {
    UP, DOWN, CLEAR, IN;
    public static double up   = 0.15;
    public static double down = 0.065;
    public static double clear = 0.25   ;
    public static double in = 0.1;


    public double get() {
        switch (this) {
            default:
            case UP:
                return up;
            case DOWN:
                return down;
            case CLEAR:
                return clear;
            case IN:
                return in;
        }
    }
}
