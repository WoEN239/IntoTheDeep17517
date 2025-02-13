package org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.FlipPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;

/*
  Writing by EgorKhvostikov
*/
public class Transfer {
    private Servo transferServoLeft ;
    private Servo transferServoRight;

    private Servo flipLeft ;
    private Servo flipRight;


    public static double eatPos = 1;

    public void init(){
        transferServoRight = IntakeDevices.transferRight;
        transferServoLeft  = IntakeDevices.transferLeft ;

        flipRight = IntakeDevices.flipRight;
        flipLeft  = IntakeDevices.flipLeft ;
    }

    public void up(){
        flipLeft.setPosition(FlipPosition.up);
        flipRight.setPosition(FlipPosition.up);
    }

    public void down(){
        flipLeft .setPosition(FlipPosition.down);
        flipRight.setPosition(FlipPosition.down);
    }

    public void in(){
        transferServoRight.setPosition(TransferPosition.in);
        transferServoLeft .setPosition(TransferPosition.in);
    }
    public void normal(){
        transferServoRight.setPosition(TransferPosition.normal);
        transferServoLeft .setPosition(TransferPosition.normal);
    }

    public void eat(){
        double t = Range.clip(eatPos,TransferPosition.normal,TransferPosition.eat);
        transferServoRight.setPosition(t);
        transferServoLeft .setPosition(t);
    }
}
