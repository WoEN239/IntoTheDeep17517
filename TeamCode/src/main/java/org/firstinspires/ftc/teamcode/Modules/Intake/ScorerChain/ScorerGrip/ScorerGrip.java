package org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain.ScorerGrip;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.ScorerGripPosition;

public class ScorerGrip {
    private Servo grip;
    public void init(){
        grip = IntakeDevices.scorerGrip;
    }

    public void open(){
        grip.setPosition(ScorerGripPosition.open);
    }

    public void close() {
        grip.setPosition(ScorerGripPosition.close);
    }
    public void regrip() {
        grip.setPosition(ScorerGripPosition.regrip);
    }
}
