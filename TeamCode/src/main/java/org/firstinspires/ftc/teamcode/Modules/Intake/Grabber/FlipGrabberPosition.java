package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */


public enum FlipGrabberPosition {
    SPREADOUT, UNSPREADOUT;
    public static double unSperadOut = 0.15;
    public static double spreadOut = 0.5;

    public double get() {
        switch (this) {
            default:
            case SPREADOUT:
                return spreadOut;
            case UNSPREADOUT:
                return unSperadOut;
        }
    }
}
