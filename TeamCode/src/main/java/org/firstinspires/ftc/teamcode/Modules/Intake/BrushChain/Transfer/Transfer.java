package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;

/*
  Writing by EgorKhvostikov
*/
public class Transfer {
    private Servo transferServoLeft ;
    private Servo transferServoRight;
    public static double eatPos;

    public void init(){
        transferServoRight = IntakeDevices.transferServoRight;
        transferServoLeft = IntakeDevices.transferServoLeft;
    }
    public void normal(){
        transferServoRight.setPosition(TransferPosition.normal);
        transferServoLeft .setPosition(1.0-TransferPosition.normal);
    }

    public void eat(){
        double t = Range.clip(eatPos,TransferPosition.normal,TransferPosition.eat);
        transferServoRight.setPosition(t);
        transferServoLeft .setPosition(1-t);
    }
}
