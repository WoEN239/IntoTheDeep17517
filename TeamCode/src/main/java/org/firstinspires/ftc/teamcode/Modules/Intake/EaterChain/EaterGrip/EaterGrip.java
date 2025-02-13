package org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.EaterGrip;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.EaterGripPosition;

/*
  Writing by EgorKhvostikov
*/
public class EaterGrip {
    private Servo grip;
    public void init(){
        grip = IntakeDevices.eaterGrip;
    }

    public void open(){
        grip.setPosition(EaterGripPosition.open);
    }
    public void close() {
        grip.setPosition(EaterGripPosition.close);
    }
    public void regrip(){
        grip.setPosition(EaterGripPosition.regrip);
    }
}
