package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;

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
    private Motor brushMotor;


    private GrabberPosition grabberTarget = GrabberPosition.CLOSE;
    private FlipGrabberPosition flipGrabberPositon = FlipGrabberPosition.UNSPREADOUT;
    private TransferPosition transferPosition = TransferPosition.NORMAL;
    private AfterTransferGrabber afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
    private OutServoPosition outServoPosition = OutServoPosition.IN_ROBOT;

    private BrushMotorPos brushTarget = BrushMotorPos.STOP;

    public GrabberPosition getGrabberTarget() {
        return grabberTarget;
    }

    public FlipGrabberPosition getFlipServoPos() {return flipGrabberPositon;}

    public TransferPosition getTransferPosition() {
        return transferPosition;
    }



    public void closeSampleGrabber() {
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

    public void forwardBrush(){
        brushTarget = BrushMotorPos.FORWARD;
    }
    public void reverseBrush(){
        brushTarget = BrushMotorPos.REVERSE;
    }
    public void stopBrush(){
        brushTarget = BrushMotorPos.STOP;
    }




    @Override
    public void init() {
        flipServoRight = IntakeDevices.flipServoRight;
        grabberServo = IntakeDevices.grabberServo;
        transferServoLeft = IntakeDevices.transferServoLeft;
        flipServoLeft = IntakeDevices.flipServoLeft;
        outRobotServo = IntakeDevices.outRobotServo;
        afterTransferServo = IntakeDevices.afterTransferServo;
        transferServoRight = IntakeDevices.transferServoRight;
        brushMotor = IntakeDevices.brushMotor;
    }

    public void update() {
        grabberServo .setPosition(grabberTarget    .get()  );
        flipServoRight.setPosition(flipGrabberPositon.get());
        flipServoLeft.setPosition(flipGrabberPositon.get());
        transferServoLeft.setPosition(transferPosition.get());
        transferServoRight.setPosition(transferPosition.get());
        afterTransferServo.setPosition(afterTransferGrabberPosition.get());
        outRobotServo.setPosition(outServoPosition.get());
        brushMotor.setPower(brushMotor.getPosition());
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
