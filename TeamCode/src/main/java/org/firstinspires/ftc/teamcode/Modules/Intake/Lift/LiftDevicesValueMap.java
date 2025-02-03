package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

public class LiftDevicesValueMap {
    public boolean leftDownButton;
    public boolean rightDownButton;
    public double leftMotorPos;
    public double rightMotorPos;

    public void copyFrom(LiftDevicesValueMap lD){
        this.leftDownButton = lD.leftDownButton;
        this.rightDownButton = lD.rightDownButton;
        this.leftMotorPos = lD.leftMotorPos;
        this.rightMotorPos = lD.rightMotorPos;
    }
}
