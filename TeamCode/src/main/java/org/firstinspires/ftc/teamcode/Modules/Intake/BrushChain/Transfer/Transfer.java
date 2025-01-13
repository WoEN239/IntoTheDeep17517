package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;

public class Transfer {
    private Servo transferServoLeft ;
    private Servo transferServoRight;
    private double eatPos;

    public void init(){
        transferServoRight = IntakeDevices.transferServoRight;
        transferServoLeft = IntakeDevices.transferServoLeft;
    }
    public void normal(){
        transferServoRight.setPosition(TransferPosition.normal);
        transferServoLeft .setPosition(TransferPosition.normal);
    }
    public void eat(){
        double t = Range.clip(eatPos,TransferPosition.normal,TransferPosition.EAT.get());
        transferServoRight.setPosition(t);
        transferServoLeft .setPosition(t);
    }
}
