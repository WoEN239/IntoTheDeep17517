package org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Eater;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.EaterPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Diff.Diff;

/*
  Writing by EgorKhvostikov
*/

public class Eater {
    private Diff eater;

    double yaw  = 0;
    double pitch = 0;

    public void init(){

        eater = IntakeDevices.eater;
    }
    public void horizon(){
        yaw   = EaterPosition  .horizonYaw;
        pitch = EaterPosition  .horizonPitch;
        eater.setTarget(yaw, pitch);
    }

    public void down(){
        pitch = EaterPosition.downPitch;
        eater.setTarget(yaw,pitch);
    }

    public void up(){
        yaw   = EaterPosition  .upYaw  ;
        pitch = EaterPosition.upPitch;
        eater.setTarget(yaw, pitch);
    }

    public void rotate(double y){
        yaw += y;
        eater.setTarget(yaw, pitch);
    }

}
