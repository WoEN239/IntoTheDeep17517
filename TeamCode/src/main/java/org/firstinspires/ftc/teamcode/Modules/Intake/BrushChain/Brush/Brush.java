package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Brush;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.BrushMotorPowers;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.FlipGrabberPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.BackWallPosition;

/*
  Writing by EgorKhvostikov
*/
public class Brush {
    private Servo flipServoLeft;
    private Motor brushMotor;
    private Servo flipServoRight;
    private Servo backWallServo;

    public void init(){
        flipServoLeft = IntakeDevices.flipServoLeft;
        brushMotor    = IntakeDevices.brushMotor;
        flipServoRight = IntakeDevices.flipServoRight;
        backWallServo  = IntakeDevices.backWallServo;
    }

    public void up(){
        flipServoLeft.setPosition(1-FlipGrabberPosition.up);
        flipServoRight.setPosition(FlipGrabberPosition.up);
    }

    public void in(){
        flipServoLeft.setPosition(1-FlipGrabberPosition.in);
        flipServoRight.setPosition(FlipGrabberPosition.in);
    }

    public void down(){
        flipServoLeft.setPosition(1-FlipGrabberPosition.down);
        flipServoRight.setPosition(FlipGrabberPosition.down);
    }

    public void clear(){
        flipServoLeft.setPosition(1-FlipGrabberPosition.clear);
        flipServoRight.setPosition(FlipGrabberPosition.clear);
    }


    public void openWall(){
        backWallServo.setPosition (BackWallPosition.open);
    }

    public void closeWall(){
        backWallServo.setPosition(BackWallPosition.close);
    }

    public void on() {
        brushMotor.setVoltage(BrushMotorPowers.forward);
    }

    public void revers() {
        brushMotor.setVoltage(BrushMotorPowers.reverse);
    }

    public void off() {
        brushMotor.setVoltage(BrushMotorPowers.stop);
    }
}
