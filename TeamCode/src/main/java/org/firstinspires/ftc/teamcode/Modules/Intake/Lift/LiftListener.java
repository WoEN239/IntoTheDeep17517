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

    DigitalChannel leftButtonDown;
    DigitalChannel rightButtonDown;
    Button upBorderButt = new Button();
    Motor liftLeftMotor;
    Motor liftRightMotor;

    private double liftPosition = 0;
    private double liftStaticErr = 0;
    private double encoderPosition = 0;

    public double errSync = 0;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        leftButtonDown = Sensors.downLeftButton;
        rightButtonDown = Sensors.downRightButton;
        liftLeftMotor = LiftHangingMotors.liftLeftMotor;
        liftRightMotor = LiftHangingMotors.liftRightMotor;
    }

    public double getPosition() {
        return liftPosition;
    }

    public double getRawPosition() {
        return encoderPosition;
    }

    public double position(){
        return (liftRightMotor.getPosition() + liftLeftMotor.getPosition()) / 2;
    }

    private void updatePosition() {
        encoderPosition = position();
        boolean isDown = upBorderButt.update(leftButtonDown.getState() && rightButtonDown.getState());
        if (isDown) {
            liftStaticErr = encoderPosition - LiftPosition.down;
        }
        liftPosition = encoderPosition - liftStaticErr;
        errSync = liftLeftMotor.getPosition() - liftRightMotor.getPosition();
    }

    @Override
    public void read() {
        updatePosition();
    }

}
