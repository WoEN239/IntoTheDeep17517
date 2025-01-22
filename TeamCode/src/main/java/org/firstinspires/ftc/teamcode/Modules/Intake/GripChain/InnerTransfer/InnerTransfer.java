package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.InnerTransferPosition;

public class InnerTransfer {
    private Servo innerTransferServo;
    public void init(){
        innerTransferServo = IntakeDevices.innerTransfer;
    }

    public void in(){
        innerTransferServo.setPosition(InnerTransferPosition.IN_ROBOT.get());
    }
    public void out(){
        innerTransferServo.setPosition(InnerTransferPosition.OUT_ROBOT.get());}
    public void centre(){
        innerTransferServo.setPosition(InnerTransferPosition.CENTER.get());
    }

}
