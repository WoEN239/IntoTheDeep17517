package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.InnerTransferPosition;

/*
  Writing by EgorKhvostikov
*/
public class InnerTransfer {
    private Servo innerTransferServo;
    public void init(){
        innerTransferServo = IntakeDevices.innerTransfer;
    }

    public void in(){
        innerTransferServo.setPosition(InnerTransferPosition.inRobot);
    }
    public void out(){
        innerTransferServo.setPosition(InnerTransferPosition.outRobot);}
    public void centre(){
        innerTransferServo.setPosition(InnerTransferPosition.center);
    }

}
