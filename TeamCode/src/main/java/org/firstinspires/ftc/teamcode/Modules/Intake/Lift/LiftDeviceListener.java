package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Math.Button;

public class LiftDeviceListener {
    private DigitalChannel leftButtonDown;
    private DigitalChannel rightButtonDown;
    private DigitalChannel leftButtonUp;
    private DigitalChannel rightButtonUp;
    private Motor leftMotor;
    private Motor rightMotor;
    Button upBorderButt = new Button();

    private final LiftDevices valuesMap = new LiftDevices();
    private LiftDevices getValuesMap(){
        return valuesMap;
    }

    public void init(){
        leftMotor = LiftHangingMotors.liftLeftMotor;
        rightMotor = LiftHangingMotors.liftRightMotor;
        leftButtonDown = Sensors.downLeftButton;
        leftButtonUp = Sensors.upLeftButton;
        rightButtonUp = Sensors.upRightButton;
        rightButtonDown = Sensors.downRightButton;
    }
    private void updateDevices(){
       upBorderButt.update(leftButtonUp.getState());
       upBorderButt.update(leftButtonDown.getState());
       upBorderButt.update(rightButtonDown.getState());
       upBorderButt.update(rightButtonUp.getState());
       leftMotor.update();
       rightMotor.update();
    }
    public void updateValuesMap(){
        updateDevices();
        valuesMap.leftUpButton = leftButtonUp.getState();
        valuesMap.leftDownButton = leftButtonDown.getState();
        valuesMap.rightUpButton = rightButtonUp.getState();
        valuesMap.rightDownButton = rightButtonDown.getState();
        valuesMap.leftMotorPos = leftMotor.getPosition();
        valuesMap.rightMotorPos = rightMotor.getPosition();
    }
}
