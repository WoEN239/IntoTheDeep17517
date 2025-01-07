package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

public class LiftDevicesValueMap {
    public boolean leftDownButton;
    public boolean rightDownButton;
    public boolean leftUpButton;
    public boolean rightUpButton;
    public double leftMotorPos;
    public double rightMotorPos;

    public void copyFrom(LiftDevicesValueMap lD){
        this.leftDownButton = lD.leftDownButton;
        this.leftUpButton = lD.leftUpButton;
        this.rightDownButton = lD.rightDownButton;
        this.rightUpButton = lD.rightUpButton;
        this.leftMotorPos = lD.leftMotorPos;
        this.rightMotorPos = lD.rightMotorPos;
    }
}
