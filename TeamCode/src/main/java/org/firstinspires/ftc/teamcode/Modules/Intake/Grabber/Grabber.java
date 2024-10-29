package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.GrabberAndTransferServo;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by @MrFrosty1234
 */

public class Grabber implements Controller {
    Robot robot;

    private Servo grabberServo;
    private Servo flipServo;
    private Servo rotateServo;
    private Servo transferServo;


    private GrabberPosition grabberTarget = GrabberPosition.OPEN;
    private FlipGrabberPosition grabberUpPosition = FlipGrabberPosition.FINISH;
    private TransferPosition transferPosition = TransferPosition.NORMAL;
    private RotateServoPosition rotateServoPosition = RotateServoPosition.NORMAL;

    public GrabberPosition getGrabberTarget() {
        return grabberTarget;
    }

    public FlipGrabberPosition getFlipServoPos() {
        return grabberUpPosition;
    }

    public TransferPosition getTransferPosition() {
        return transferPosition;
    }

    public RotateServoPosition getRotateServoPosition(){return  rotateServoPosition;}

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

    public void normalRotatePos(){rotateServoPosition = RotateServoPosition.NORMAL;}

    public void perpendicularRotatePos(){rotateServoPosition = RotateServoPosition.PERPENDICULAR;}


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        flipServo = GrabberAndTransferServo.flipServo;
        grabberServo = GrabberAndTransferServo.grabberServo;
        transferServo = GrabberAndTransferServo.transferServo;
        rotateServo = GrabberAndTransferServo.rotateServo;
    }

    public void update() {
        grabberServo.setPosition(grabberTarget.get());
        flipServo.setPosition(grabberUpPosition.get());
        transferServo.setPosition(transferPosition.get());
    }


}
