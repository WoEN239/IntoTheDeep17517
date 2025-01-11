package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

public enum TransferPositionRight {
    EAT, NORMAL;

    public static double eat = 0;
    public static double normal = 0;

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
