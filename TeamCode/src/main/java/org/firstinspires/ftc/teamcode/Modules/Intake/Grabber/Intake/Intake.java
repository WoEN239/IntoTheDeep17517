package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeServo;
import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Position.FlipGrabberPositionLeft;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Position.FlipGrabberPositionRight;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Position.GrabberPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Position.PowerBrush;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Position.TransferPositionLeft;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Position.TransferPositionRight;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Config

public class Intake {
    /**
     * Writing by @MrFrosty1234
     */
    Robot robot;
    private Servo grabberServo;
    private Servo flipServoRight;
    private Servo flipServoLeft;
    private Servo transferServoLeft;
    private Servo transferServoRight;
    private Motor brushMotor;
    public static double volt = 3.3;


    public GrabberPosition grabberTarget = GrabberPosition.OPEN;
    public FlipGrabberPositionRight flipGrabberPositonRight = FlipGrabberPositionRight.UNSPREADOUT;
    public FlipGrabberPositionLeft flipGrabberPositonLeft = FlipGrabberPositionLeft.UNSPREADOUT;
    public TransferPositionLeft transferPositionLeft = TransferPositionLeft.NORMAL;
    public TransferPositionRight transferPositionRight = TransferPositionRight.NORMAL;
    public PowerBrush brushPower = PowerBrush.STOP;

    public void closeSampleGrabber() {
        grabberTarget = GrabberPosition.CLOSE;
    }


    public void openSampleGrabber() {
        grabberTarget = GrabberPosition.OPEN;
    }


    public void upFlipServo() {
        flipGrabberPositonRight = FlipGrabberPositionRight.UNSPREADOUT;
        flipGrabberPositonLeft = FlipGrabberPositionLeft.UNSPREADOUT;
    }

    public void downFlipServo() {
        flipGrabberPositonRight = FlipGrabberPositionRight.SPREADOUT;
        flipGrabberPositonLeft = FlipGrabberPositionLeft.SPREADOUT;
    }

    public void normalTrasnfer() {
        transferPositionLeft = TransferPositionLeft.NORMAL;
        transferPositionRight = TransferPositionRight.NORMAL;
    }

    public void eatTransfer() {
        transferPositionRight = TransferPositionRight.EAT;
        transferPositionLeft = TransferPositionLeft.EAT;
    }

    public void forwrdBrush() {
        brushPower = PowerBrush.FORWARD;
    }

    public void stopBrush() {
        brushPower = PowerBrush.STOP;
    }

    public void reverseBrush() {
        brushPower = PowerBrush.REVERSE;
    }

    public static double fixTransfer = 0.05;

    public void init(Robot robot) {
        this.robot = robot;
        flipServoRight = IntakeServo.flipServoRight;
        grabberServo = IntakeServo.grabberServo;
        transferServoLeft = IntakeServo.transferServoLeft;
        transferServoRight = IntakeServo.transferServoRight;
        flipServoLeft = IntakeServo.flipServoLeft;
        brushMotor = LiftHangingMotors.brushMotor;
    }

    public void update() {
        grabberServo.setPosition(grabberTarget.get());
        flipServoRight.setPosition(flipGrabberPositonRight.get());
        flipServoLeft.setPosition(flipGrabberPositonLeft.get());

        transferServoLeft.setPosition(transferPositionLeft.get());
        transferServoRight.setPosition(transferPositionRight.get());
        brushVolt();
    }

    public void brushVolt() {
        brushMotor.setVoltage(brushPower.get());
    }
}

