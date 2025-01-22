package org.firstinspires.ftc.teamcode.Modules.Intake.Config;

/**
 * Writing by @MrFrosty1234
 */

public enum TransferPosition {

    EAT, NORMAL;

    public static double eat = 0.35;
    public static double normal = 0.0;


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
