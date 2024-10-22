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
    private Servo flipServo;
    private Servo transferServoSecond;
    private Servo transferServoFirst;


    private GrabberPosition grabberTarget = GrabberPosition.OPEN;
    private FlipGrabberPosition grabberUpPosition = FlipGrabberPosition.FINISH;
    private TransferPosition transferPosition = TransferPosition.NORMAL;

    public GrabberPosition getGrabberTarget() {
        return grabberTarget;
    }

    public FlipGrabberPosition getFlipServoPos() {
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

    public void closeFlipServo() {
        grabberUpPosition = FlipGrabberPosition.FINISH;
    }

    public void openFlipServo() {
        grabberUpPosition = FlipGrabberPosition.START;
    }


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        flipServo = robot.devicePool.grabber.flipServo;
        grabberServo = robot.devicePool.grabber.grabberServo;
        transferServoFirst = robot.devicePool.grabber.transferServo;
        transferServoSecond = robot.devicePool.grabber.transferServoSecond;
    }

    public void update() {
        grabberServo.setPosition(grabberTarget.get());
        flipServo.setPosition(grabberUpPosition.get());
        transferServoFirst.setPosition(transferPosition.get());
    }


}
