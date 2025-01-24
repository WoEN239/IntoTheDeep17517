package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;

public class LiftVoltageController {
    private Motor left = new Motor();
    private Motor right = new Motor();

    public void updateData() {
        right.update();
        left.update();
    }
    public void init(){
        left = LiftHangingMotors.liftLeftMotor;
        right = LiftHangingMotors.liftRightMotor;
    }
    public void setVoltage(double s, double m){
        right.setVoltage(m+s);
        left.setVoltage (m-s);
    }

}
