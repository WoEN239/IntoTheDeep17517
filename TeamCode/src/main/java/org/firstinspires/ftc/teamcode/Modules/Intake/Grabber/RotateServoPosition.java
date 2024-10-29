package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum RotateServoPosition {
    NORMAL, PERPENDICULAR;

    public static double normal = 0.5;
    public static double perpendicular = 0.15;

    public double get() {
        switch (this) {
            default:
            case NORMAL:
                return normal;
            case PERPENDICULAR:
                return perpendicular;
        }
    }

}
