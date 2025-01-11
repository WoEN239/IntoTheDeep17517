package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

public enum FlipGrabberPositionRight {
    SPREADOUT, UNSPREADOUT;
    public static double unSperadOut = 0.93;
    public static double spreadOut = 0.9;

    public double get() {
        switch (this) {
            default:
            case SPREADOUT:
                return spreadOut;
            case UNSPREADOUT:
                return unSperadOut;
        }
    }
}
