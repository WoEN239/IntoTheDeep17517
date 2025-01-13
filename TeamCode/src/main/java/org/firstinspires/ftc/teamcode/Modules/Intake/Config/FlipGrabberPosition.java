package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config
public enum FlipGrabberPosition {
    UP, DOWN;
    public static double unSperadOut = 0.15;
    public static double spreadOut = 0.5;

    public double get() {
        switch (this) {
            default:
            case UP:
                return spreadOut;
            case DOWN:
                return unSperadOut;
        }
    }
}
