package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config

public enum FlipGrabberPosition {
    UP, DOWN, MOVE;
    public static double down = 0.15;
    public static double up = 0.53;
    public static double move = 0.33;

    public double get() {
        switch (this) {
            default:
            case UP:
                return down;
            case DOWN:
                return up;
            case MOVE:
                return move;
        }
    }
}
