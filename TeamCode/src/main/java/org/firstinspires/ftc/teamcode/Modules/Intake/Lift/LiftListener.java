package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

public class LiftListener{

    private LiftDevicesValueMap liftDevicesValueMap = new LiftDevicesValueMap();
    public void setDevicesValueMap(LiftDevicesValueMap lD){
        liftDevicesValueMap.copyFrom(lD);
    }

    private static double liftPosition = 0;
    private static double liftStaticErrLeft = 0;
    private static double liftStaticErrRight = 0;
    private static double errSync = 0;

    public double getPosition() {
        return liftPosition;
    }
    public double getErrSync(){return errSync;}

    public void computePosition() {
        boolean isDownLeft  = liftDevicesValueMap.leftDownButton;
        boolean isDownRight = liftDevicesValueMap.rightDownButton;

        if(isDownLeft)
            liftStaticErrLeft = liftDevicesValueMap.leftMotorPos   - LiftPosition.down;
        if(isDownRight)
            liftStaticErrRight = liftDevicesValueMap.rightMotorPos - LiftPosition.down;

        liftPosition = (( liftDevicesValueMap.rightMotorPos - liftStaticErrRight) + (liftDevicesValueMap.leftMotorPos - liftStaticErrLeft)) / 2.0;
        errSync = (liftDevicesValueMap.leftMotorPos - liftStaticErrLeft) - (liftDevicesValueMap.rightMotorPos - liftStaticErrRight);
    }

}