package org.firstinspires.ftc.teamcode.Modules.Intake.Lift.Lift;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;

public class LiftVoltageController {
    private Motor leftLiftMotor = new Motor();
    private Motor rightLiftMotor = new Motor();

    public void updateData() {
        rightLiftMotor.update();
        leftLiftMotor.update();
    }
    public void init(){
        leftLiftMotor = LiftHangingMotors.liftMotor;

    }
    public void setVoltage(double s, double m){
        rightLiftMotor.setVoltage(m+s);
        leftLiftMotor.setVoltage (m-s);
    }

}
