package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Math.Button;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class LiftListener implements Listener {

    Robot robot;

    DigitalChannel buttonUp;
    DigitalChannel buttonDown;
    static Button upBorderButt = new Button();
    static Button downBorderButt = new Button();
    Motor liftLeftMotor;
    Motor liftRightMotor;

    private static double liftPosition = 0;
    private static double liftStaticErrLeft = 0;
    private static double liftStaticErrRight = 0;
    private static double encoderPosition = 0;

    public double errSync = 0;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        buttonUp = Sensors.upButton;
        buttonDown = Sensors.downButton;

        liftLeftMotor = LiftHangingMotors.liftLeftMotor;
        liftRightMotor = LiftHangingMotors.liftRightMotor;
    }
    private void updateDevice(){
        liftRightMotor.update();
        liftLeftMotor.update();
    }

    public double getPosition() {
        return liftPosition;
    }

    private void updatePosition() {
        updateDevice();
        boolean isUp   = buttonUp.getState();
        boolean isDown = buttonDown.getState();

        if (isUp) {
            liftStaticErrRight = -liftRightMotor.getPosition() - LiftPosition.up;
            liftStaticErrLeft  = liftLeftMotor.getPosition()   - LiftPosition .up;
        }
        if(isDown) {
            liftStaticErrRight = -liftRightMotor.getPosition() - LiftPosition.down;
            liftStaticErrLeft  = liftLeftMotor.getPosition() - LiftPosition.down;
        }

        LiftListener.liftPosition = (( -liftRightMotor.getPosition() - liftStaticErrRight) + ( liftLeftMotor.getPosition() - liftStaticErrLeft)) / 2.0;
        errSync = ( liftLeftMotor.getPosition() - liftStaticErrLeft) - ( -liftRightMotor.getPosition() - liftStaticErrRight);
    }


    @Override
    public void read() {
        updatePosition();
    }

}