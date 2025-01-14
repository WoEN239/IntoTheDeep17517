package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config
public enum TransferPosition {

    GRAB, NORMAL;

    public static double grab = 0.2;
    public static double normal = 0.55;

    public double get() {
        switch (this) {
            default:
            case GRAB:
                return grab;
            case NORMAL:
                return normal;
        }
    }

}
