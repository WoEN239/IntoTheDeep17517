package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;

@Config

public enum TransferPosition {

    GRAB, NORMAL;

    public static double grab = 1;
    private static double normal =0 ;

    public double get(){
        switch (this){
            default:
            case GRAB:
                return grab;
            case NORMAL:
                return normal;
        }
    }

}
