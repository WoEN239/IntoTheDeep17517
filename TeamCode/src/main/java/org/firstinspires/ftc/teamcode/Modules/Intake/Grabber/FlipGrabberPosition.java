package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config

public enum FlipGrabberPosition {
    START, FINISH;
    public static double start = 0.15;
    public static double finish = 0.53;

    public double get() {
        switch (this) {
            default:
            case START:
                return start;
            case FINISH:
                return finish;
        }
    }
}
