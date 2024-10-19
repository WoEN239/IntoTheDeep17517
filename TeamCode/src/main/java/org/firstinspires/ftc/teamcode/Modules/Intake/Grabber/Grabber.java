package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by @MrFrosty1234
 */

public class Grabber implements Controller {
    Robot robot;

    private Servo grabberServo;
    private Servo grabberServoUp;
    private Servo transferServoSecond;
    private Servo transferServoFirst;


    private GrabberPosition grabberTarget = GrabberPosition.OPEN;
    private GrabberUpPosition grabberUpPosition = GrabberUpPosition.FINISH;
    private TransferPosition transferPosition = TransferPosition.NORMAL;

    public GrabberPosition getGrabberTarget() {
        return grabberTarget;
    }

    public GrabberUpPosition getGrabberUpPosition() {
        return grabberUpPosition;
    }

    public TransferPosition getTransferPosition() {
        return transferPosition;
    }

    public void closeSimpleGrabber() {
        grabberTarget = GrabberPosition.CLOSE;
    }

    public void openSimpleGrabber() {
        grabberTarget = GrabberPosition.OPEN;
    }

    public void transferToGrab() {
        transferPosition = TransferPosition.GRAB;
    }

    public void transferToNormal() {
        transferPosition = TransferPosition.NORMAL;
    }

    public void closeUpGrabber() {
        grabberUpPosition = GrabberUpPosition.FINISH;
    }

    public void openUpGrabber() {
        grabberUpPosition = GrabberUpPosition.START;
    }


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        grabberServoUp = robot.devicePool.grabber.grabberServoUp;
        grabberServo = robot.devicePool.grabber.grabberServo;
        transferServoFirst = robot.devicePool.grabber.transferServoFirst;
        transferServoSecond = robot.devicePool.grabber.transferServoSecond;
    }

    public void update() {
        grabberServo.setPosition(grabberTarget.get());
        grabberServoUp.setPosition(grabberUpPosition.get());
        transferServoFirst.setPosition(transferPosition.get());
    }


}
