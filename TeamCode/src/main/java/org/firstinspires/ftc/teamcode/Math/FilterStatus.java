package org.firstinspires.ftc.teamcode.Math;

/**
 * Writing by EgorKhvostikov
 */

public class FilterStatus {
    public int medianSize;
    public double senseUp;
    public double senseDown;
    public double bigK;
    public double smallK;
    public double centreK;
    public boolean isTelemetry = false;

    public void setTelemetry(boolean telemetry) {
        isTelemetry = telemetry;
    }

    public FilterStatus(int medianSize, double senseUp, double senseDown, double bigK, double smallK, double centreK) {
        this.medianSize = medianSize;
        this.senseUp = senseUp;
        this.senseDown = senseDown;
        this.bigK = bigK;
        this.smallK = smallK;
        this.centreK = centreK;
    }

}
