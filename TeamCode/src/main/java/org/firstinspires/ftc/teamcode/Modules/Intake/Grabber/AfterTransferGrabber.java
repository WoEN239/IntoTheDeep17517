package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;


import com.acmerobotics.dashboard.config.Config;
@Config
public enum AfterTransferGrabber {
    OPEN, CLOSE;

    public static double open = 0.1;
    public static double close = 0;

    public double get(){
        switch (this){
            default:
            case OPEN:
                return open;
            case CLOSE:
                return close;
        }
    }
}
