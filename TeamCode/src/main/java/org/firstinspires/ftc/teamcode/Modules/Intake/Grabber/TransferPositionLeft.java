package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

/**
 * Writing by @MrFrosty1234
 */

@Config
public enum TransferPositionLeft {

    EAT, NORMAL;

    public static double eat = 0.7;
    public static double normal = 0.3;

    public double get() {
        switch (this) {
            default:
            case EAT:
                return eat;
            case NORMAL:
                return normal;
        }
    }

}
