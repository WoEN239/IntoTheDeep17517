package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.InnerTransferPosition;

public class InnerTransfer {
    private Servo innerTransferServo;
    public void init(){
        innerTransferServo = IntakeDevices.innerTransfer;
    }
    public void inRobot(){
        innerTransferServo.setPosition(InnerTransferPosition.IN_ROBOT.get());
    }
    public void outRobot(){
        innerTransferServo.setPosition(InnerTransferPosition.OUT_ROBOR.get());
    }

}
