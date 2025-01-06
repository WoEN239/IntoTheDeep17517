package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Math.Button;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class LiftListener{

    LiftDevices liftDevices = new LiftDevices();

    private void setLiftDevices(LiftDevices lD){
        liftDevices.copyFrom(lD);
    }



    private double liftPosition = 0;
    private double liftStaticErrLeft = 0;
    private double liftStaticErrRight = 0;

    public double errSync = 0;


    public double getPosition() {
        return liftPosition;
    }
    public double getErrSync(){return errSync;}

    public void computePosition() {
        boolean isDownLeft  = liftDevices.leftDownButton;
        boolean isDownRight = liftDevices.rightDownButton;
        boolean isUpRight = liftDevices.rightUpButton;
        boolean isUpLeft = liftDevices.leftUpButton;
        if (isDownLeft)
            liftStaticErrLeft = liftDevices.leftMotorPos - LiftPosition.down;
        if(isDownRight)
            liftStaticErrRight = liftDevices.rightMotorPos - LiftPosition.down;
        if(isUpRight)
            liftStaticErrRight = LiftPosition.highestBasket - liftDevices.rightMotorPos;
        if(isUpLeft)
            liftStaticErrLeft = LiftPosition.highestBasket - liftDevices.leftMotorPos;
        liftPosition = ((liftDevices.rightMotorPos - liftStaticErrRight) + (liftDevices.leftMotorPos - liftStaticErrLeft)) / 2.0;
        errSync = (liftDevices.leftMotorPos - liftStaticErrLeft) - (liftDevices.rightMotorPos - liftStaticErrRight);
    }

}