package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.Grip;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.GripPositions;

public class Grip {
    Servo grip;
    public void init(){
        grip = IntakeDevices.gripServo;
    }
    public void open(){
        grip.setPosition(GripPositions.OPEN.get());
    }
    public void close(){
        grip.setPosition(GripPositions.CLOSE.get());
    }
}
