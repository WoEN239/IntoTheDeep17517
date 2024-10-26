package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Math.Button;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

public class LiftListener implements Listener {

    Robot robot;

    DigitalChannel buttonDown;
    Button upBorderButt = new Button();
    Motor liftMotor;

    private double liftPosition = 0;
    private double liftStaticErr = 0;
    private double encoderPosition = 0;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        buttonDown = Sensors.downButton;
        liftMotor  = LiftHangingMotors.liftLeftMotor;
    }

    public double getPosition() {
        return liftPosition;
    }

    public double getRawPosition() {
        return encoderPosition;
    }

    private void updatePosition() {
        encoderPosition = liftMotor.getPosition();
        boolean isDown = upBorderButt.update(buttonDown.getState());
        if (isDown) {
            liftStaticErr = encoderPosition - LiftPosition.down;
        }
        liftPosition = encoderPosition - liftStaticErr;
    }

    @Override
    public void read() {
        updatePosition();
    }

}
