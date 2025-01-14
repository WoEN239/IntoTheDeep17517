package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Position;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum TransferPositionRight {
    EAT, NORMAL;

    public static double eat = 0.35;
    public static double normal = 0.1;

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
