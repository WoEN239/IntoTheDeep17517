package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config

public enum GrabberPosition {
    FORWARD, REAVERSE, STOP;
    public static double forward = 1;
    public static double reverse = 0;

    public static double stop = 0.5;

    public double get() {
        switch (this) {
            default:
            case FORWARD:
                return forward;
            case REAVERSE:
                return reverse;
            case STOP:
                return stop;
        }
    }
}
