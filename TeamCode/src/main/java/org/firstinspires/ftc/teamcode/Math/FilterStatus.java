package org.firstinspires.ftc.teamcode.Math;

public class FilterStatus {
    public  int    medianSize  = 3     ;
    public  double senseUp     = 50    ;
    public  double senseDown   = 10    ;
    public  double bigK        = 0.4   ;
    public  double smallK      = 0.0005;
    public  boolean isTelemetry = false;

    public void setTelemetry(boolean telemetry) {
        isTelemetry = telemetry;
    }

    public FilterStatus(int medianSize, double senseUp, double senseDown, double bigK, double smallK) {
        this.medianSize = medianSize;
        this.senseUp = senseUp;
        this.senseDown = senseDown;
        this.bigK = bigK;
        this.smallK = smallK;
    }
}