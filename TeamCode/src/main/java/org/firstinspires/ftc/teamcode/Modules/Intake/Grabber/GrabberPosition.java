package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config

public enum GrabberPosition {
    OPEN, CLOSE;
    public static double open = 1;
    public static double close = 0;

    public double get() {
        switch (this) {
            default:
            case OPEN:
                return open;
            case CLOSE:
                return close;
        }
    }
}
