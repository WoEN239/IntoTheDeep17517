package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

public class LiftListener{

    private LiftDevicesValueMap liftDevicesValueMap = new LiftDevicesValueMap();
    public void setDevicesValueMap(LiftDevicesValueMap lD){
        liftDevicesValueMap.copyFrom(lD);
    }

    private double liftPosition = 0;
    private double liftStaticErrLeft = 0;
    private double liftStaticErrRight = 0;
    private double errSync = 0;

    public double getPosition() {
        return liftPosition;
    }
    public double getErrSync(){return errSync;}

    public void computePosition() {
        boolean isDownLeft  = liftDevicesValueMap.leftDownButton;
        boolean isDownRight = liftDevicesValueMap.rightDownButton;
        boolean isUpRight   = liftDevicesValueMap.rightUpButton;
        boolean isUpLeft    = liftDevicesValueMap.leftUpButton;
        if (isDownLeft)
            liftStaticErrLeft = liftDevicesValueMap.leftMotorPos - LiftPosition.down;
        if(isDownRight)
            liftStaticErrRight = liftDevicesValueMap.rightMotorPos - LiftPosition.down;
        if(isUpRight)
            liftStaticErrRight = -LiftPosition.highestBasket + liftDevicesValueMap.rightMotorPos;
        if(isUpLeft)
            liftStaticErrLeft = - LiftPosition.highestBasket + liftDevicesValueMap.leftMotorPos;

        liftPosition = ((liftDevicesValueMap.rightMotorPos - liftStaticErrRight) + (liftDevicesValueMap.leftMotorPos - liftStaticErrLeft)) / 2.0;
        errSync = (liftDevicesValueMap.leftMotorPos - liftStaticErrLeft) - (liftDevicesValueMap.rightMotorPos - liftStaticErrRight);
    }

}