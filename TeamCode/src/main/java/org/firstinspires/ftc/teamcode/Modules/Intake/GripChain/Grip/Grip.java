package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.Grip;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.GripPositions;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TwistPosition;

public class Grip {
    Servo gripServo;
    Servo twistServo;
    public void init(){
        twistServo = IntakeDevices.twistServo;
        gripServo = IntakeDevices.gripServo;
    }
    public void open(){
        gripServo.setPosition(GripPositions.OPEN.get());
    }
    public void close(){
        gripServo.setPosition(GripPositions.CLOSE.get());
    }
    public void in(){
        twistServo.setPosition(TwistPosition.IN.get());
    }
    public void out(){
        twistServo.setPosition(TwistPosition.OUT.get());
    }
}
