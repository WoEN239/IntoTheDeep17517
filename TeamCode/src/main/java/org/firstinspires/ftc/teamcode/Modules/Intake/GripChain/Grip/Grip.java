package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.Grip;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.GripPositions;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TwistPosition;

/*
  Writing by EgorKhvostikov
*/
public class Grip {
    Servo gripServo;
    Servo twistServo;
    public void init(){
        twistServo = IntakeDevices.twistServo;
        gripServo = IntakeDevices.gripServo;
    }
    public void open(){
        gripServo.setPosition(GripPositions.open);
    }
    public void close(){gripServo.setPosition(GripPositions.open);}

    public void in(){twistServo.setPosition(TwistPosition.in);}
    public void out(){twistServo.setPosition(TwistPosition.in);}
}
