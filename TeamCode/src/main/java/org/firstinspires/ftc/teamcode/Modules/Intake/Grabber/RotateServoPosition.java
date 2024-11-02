package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum RotateServoPosition {
    NORMAL, ROTATED;

    public static double normal = 0.21;
    public static double rotated = 0.59;

    public double get() {
        switch (this) {
            default:
            case NORMAL:
                return normal;
            case ROTATED:
                return rotated;
        }
    }

}
