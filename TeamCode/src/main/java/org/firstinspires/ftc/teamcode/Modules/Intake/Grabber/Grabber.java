package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeServo;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Writing by @MrFrosty1234
 */

public class Grabber implements Controller {

    private Servo grabberServo;
    private Servo flipServoRight;
    private Servo transferServoLeft;
    private Servo transferServoRight;
    private Servo afterTransferServo;
    private Servo outRobotServo;
    private Servo flipServoLeft;


    private GrabberPosition grabberTarget = GrabberPosition.STOP;
    private FlipGrabberPosition flipGrabberPositon = FlipGrabberPosition.UNSPREADOUT;
    private TransferPosition transferPosition = TransferPosition.NORMAL;
    private AfterTransferGrabber afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
    private OutServoPosition outServoPosition = OutServoPosition.IN_ROBOT;

    public GrabberPosition getGrabberTarget() {
        return grabberTarget;
    }

    public FlipGrabberPosition getFlipServoPos() {return flipGrabberPositon;}

    public TransferPosition getTransferPosition() {
        return transferPosition;
    }



    public void reverseSampleGrabber() {
        grabberTarget = GrabberPosition.REAVERSE;
    }

    public void stopSampleGrabber(){
        grabberTarget = GrabberPosition.STOP;
    }

    public void forwardSampleGrabber() {
        grabberTarget = GrabberPosition.FORWARD;
    }


    public void transferToEat() {
        transferPosition = TransferPosition.EAT;
    }

    public void transferToNormal() {
        transferPosition = TransferPosition.NORMAL;
    }


    public void upFlipServo() { flipGrabberPositon = FlipGrabberPosition.UNSPREADOUT;}

    public void downFlipServo() {
        flipGrabberPositon = FlipGrabberPosition.SPREADOUT;
    }

    public void inOutServo(){
        outServoPosition = OutServoPosition.IN_ROBOT;
    }

    public void outOutServo(){
        outServoPosition = OutServoPosition.OUT_ROBOR;
    }

    public void closeAfterTransferServo(){
        afterTransferGrabberPosition = AfterTransferGrabber.CLOSE;
    }

    public void openAfterTransferServo(){
        afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
    }




    @Override
    public void init() {
        flipServoRight = IntakeServo.flipServoRight;
        grabberServo = IntakeServo.grabberServo;
        transferServoLeft = IntakeServo.transferServoLeft;
        flipServoLeft = IntakeServo.flipServoLeft;
        outRobotServo = IntakeServo.outRobotServo;
        afterTransferServo = IntakeServo.afterTransferServo;
    }

    public void update() {
        grabberServo .setPosition(grabberTarget    .get()  );
        flipServoRight.setPosition(flipGrabberPositon.get());
        flipServoLeft.setPosition(flipGrabberPositon.get());
        transferServoLeft.setPosition(transferPosition.get());
        transferServoRight.setPosition(transferPosition.get());
        afterTransferServo.setPosition(afterTransferGrabberPosition.get());
        outRobotServo.setPosition(outServoPosition.get());
    }

    public FlipGrabberPosition getFlipGrabberPositon() {
        return flipGrabberPositon;
    }

    public void setFlipGrabberPositon(FlipGrabberPosition flipGrabberPositon) {
        this.flipGrabberPositon = flipGrabberPositon;
    }

    public OutServoPosition getOutServoPosition() {
        return outServoPosition;
    }

    public void setOutServoPosition(OutServoPosition outServoPosition) {
        this.outServoPosition = outServoPosition;
    }
}
