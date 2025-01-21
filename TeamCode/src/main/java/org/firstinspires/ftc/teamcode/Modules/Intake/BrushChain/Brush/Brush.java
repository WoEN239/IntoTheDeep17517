package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Brush;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.BrushMotorPowers;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.FlipGrabberPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.BackWallPosition;

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
        flipServoLeft.setPosition(1- FlipGrabberPosition.UP.get()   );
        flipServoRight.setPosition(FlipGrabberPosition.UP.get());
    }

    public void in(){
        flipServoLeft.setPosition(1- FlipGrabberPosition.IN.get() );
        flipServoRight.setPosition(FlipGrabberPosition.IN.get());
    }

    public void down(){
        flipServoLeft.setPosition(1- FlipGrabberPosition.DOWN.get()   );
        flipServoRight.setPosition(FlipGrabberPosition.DOWN.get());
    }

    public void clear(){
        flipServoLeft.setPosition(1- FlipGrabberPosition.CLEAR.get()   );
        flipServoRight.setPosition(  FlipGrabberPosition.CLEAR.get());
    }


    public void openWall(){
        backWallServo.setPosition (BackWallPosition.OPEN.get());
    }

    public void closeWall(){
        backWallServo.setPosition(BackWallPosition.CLOSE.get());
    }

    public void on() {
        brushMotor.setVoltage(BrushMotorPowers.forward);
    }

    public void off() {
        brushMotor.setVoltage(BrushMotorPowers.stop);
    }
}
