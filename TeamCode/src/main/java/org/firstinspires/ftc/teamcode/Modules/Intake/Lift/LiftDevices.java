package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Math.Button;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class LiftDevices {
    public boolean leftDownButton;
    public boolean rightDownButton;
    public boolean leftUpButton;
    public boolean rightUpButton;
    public double leftMotorPos;
    public double rightMotorPos;

    public void copyFrom(LiftDevices lD){
        this.leftDownButton = lD.leftDownButton;
        this.leftUpButton = lD.leftUpButton;
        this.rightDownButton = lD.rightDownButton;
        this.rightUpButton = lD.rightUpButton;
        this.leftMotorPos = lD.leftMotorPos;
        this.rightMotorPos = lD.rightMotorPos;
    }
}
