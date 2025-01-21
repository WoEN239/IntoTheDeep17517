package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Math.BorderButton;

public class LiftDeviceListener {
    private DigitalChannel leftDownButton;
    private final BorderButton leftDownBorderButt = new BorderButton();

    private DigitalChannel rightDownButton;
    private final BorderButton rightDownBorderButt = new BorderButton();

    private DigitalChannel leftUpButton;
    private final BorderButton leftUpBorderButt    = new BorderButton();

    private DigitalChannel rightUpButton;
    private final BorderButton rightUpBorderButt   = new BorderButton();

    private Motor leftMotor;
    private Motor rightMotor;


    private final LiftDevicesValueMap valuesMap = new LiftDevicesValueMap();
    public  LiftDevicesValueMap getValuesMap(){return valuesMap;}

    public void init(){
        leftMotor = LiftHangingMotors.liftLeftMotor;
        rightMotor = LiftHangingMotors.liftRightMotor;
        leftDownButton = Sensors.downLeftButton;
        leftUpButton = Sensors.upLeftButton;
        rightUpButton = Sensors.upRightButton;
        rightDownButton = Sensors.downRightButton;
    }
    private void updateDevices(){
       leftMotor.update();
       rightMotor.update();
    }
    public void updateValuesMap(){
        updateDevices();
        valuesMap.leftUpButton    = leftUpBorderButt.get(leftUpButton   .getState());
        valuesMap.leftDownButton  = leftDownBorderButt.get(leftDownButton .getState());
        valuesMap.rightUpButton   = rightUpBorderButt.get(rightUpButton  .getState());
        valuesMap.rightDownButton = rightDownBorderButt.get(rightDownButton.getState());

        valuesMap.leftMotorPos = leftMotor.getPosition();
        valuesMap.rightMotorPos = rightMotor.getPosition();
    }
}
