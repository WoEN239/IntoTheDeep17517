package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.GrabberAndTransferServo;
import org.firstinspires.ftc.teamcode.Modules.Controller;
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
    private FlipGrabberPosition grabberUpPosition = FlipGrabberPosition.DOWN;
    private TransferPosition transferPosition = TransferPosition.NORMAL;
    private double rotateServoPosition = RotateServoPosition.NORMAL.get();

    public GrabberPosition getGrabberTarget() {
        return grabberTarget;
    }

    public FlipGrabberPosition getFlipServoPos() {
        return grabberUpPosition;
    }

    public TransferPosition getTransferPosition() {
        return transferPosition;
    }

    public double getRotateServoPosition(){return  rotateServoPosition;}


    public void closeSimpleGrabber() {
        grabberTarget = GrabberPosition.CLOSE;
    }

    public void openSampleGrabber() {
        grabberTarget = GrabberPosition.OPEN;
    }


    public void transferToEat() {
        transferPosition = TransferPosition.EAT;
    }

    public void transferToNormal() {
        transferPosition = TransferPosition.NORMAL;
    }


    public void upFlipServo() { grabberUpPosition = FlipGrabberPosition.DOWN;}

    public void downFlipServo() {
        grabberUpPosition = FlipGrabberPosition.UP;
    }

    public void targetingFLipServo(){grabberUpPosition = FlipGrabberPosition.MOVE;}


    public void normalRotateServo() {rotateServoPosition = RotateServoPosition.NORMAL.get();}

    public void rotatedRotateServo(){rotateServoPosition = RotateServoPosition.ROTATED.get();}

    public void setRotateServoPosition(double pos ){rotateServoPosition = pos;}

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        flipServo = GrabberAndTransferServo.flipServo;
        grabberServo = GrabberAndTransferServo.grabberServo;
        transferServo = GrabberAndTransferServo.transferServo;
        rotateServo = GrabberAndTransferServo.rotateServo;
    }

    public void update() {
        grabberServo .setPosition(grabberTarget    .get()  );
        flipServo    .setPosition(grabberUpPosition.get()  );
        transferServo.setPosition(transferPosition .get()  );
        rotateServo  .setPosition(rotateServoPosition      );
    }

}
