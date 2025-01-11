package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeServo;
import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Writing by @MrFrosty1234
 */

@Config
public class Grabber implements Controller {
    Robot robot;
    private Servo grabberServo;
    private Servo flipServoRight;
    private Servo transferServoLeft;
    private Servo transferServoRight;
    private Servo afterTransferServo;
    private Servo outRobotServo;
    private Servo flipServoLeft;
    private Motor brushMotor;
    private Servo twistServo;
    public static double volt = 3.3;


    public GrabberPosition grabberTarget = GrabberPosition.OPEN;
    public FlipGrabberPositionRight flipGrabberPositonRight = FlipGrabberPositionRight.UNSPREADOUT;
    public FlipGrabberPositionLeft flipGrabberPositonLeft = FlipGrabberPositionLeft.UNSPREADOUT;
    public TransferPositionLeft transferPositionLeft = TransferPositionLeft.NORMAL;
    public double transferPositionRight = TransferPositionRight.NORMAL.get();
    public AfterTransferGrabber afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
    public OutServoPosition outServoPosition = OutServoPosition.IN_ROBOT;
    public TwistServo twistServoPosition = TwistServo.EAT;
    public PowerBrush brushPower = PowerBrush.STOP;

    public GrabberPosition getGrabberTarget() {
        return grabberTarget;
    }

    //public FlipGrabberPositionLeft getFlipServoPos() {return flipGrabberPositonRight;}

    public TransferPositionLeft getTransferPosition() {
        return transferPositionLeft;
    }



    public void closeSampleGrabber() {
        grabberTarget = GrabberPosition.CLOSE;
    }


    public void openSampleGrabber() {
        grabberTarget = GrabberPosition.OPEN;
    }


    public void transferToEat() {
        transferPositionLeft = TransferPositionLeft.EAT;
    }

    public void transferToNormal() {
        transferPositionLeft = TransferPositionLeft.NORMAL;
    }


    public void upFlipServo() { flipGrabberPositonRight = FlipGrabberPositionRight.UNSPREADOUT;}

    public void downFlipServo() {
        flipGrabberPositonRight = FlipGrabberPositionRight.SPREADOUT;
    }

    public void inOutServo(){
        outServoPosition = OutServoPosition.IN_ROBOT;
    }

    public void outOutServo(){
        outServoPosition = OutServoPosition.OUT_ROBOT;
    }

    public void closeAfterTransferServo(){
        afterTransferGrabberPosition = AfterTransferGrabber.CLOSE;
    }

    public void openAfterTransferServo(){
        afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
    }

    public void toEatTwistServo(){
        twistServoPosition = TwistServo.EAT;
    }
    public void toEatFromWallTwistServo(){
        twistServoPosition = TwistServo.EAT_FROM_WALL;
    }
    public void toDropTwistServo(){
        twistServoPosition = TwistServo.DROP;
    }

    public void forwrdBrush(){
        brushPower = PowerBrush.FORWARD;
    }
    public void stopBrush(){
        brushPower = PowerBrush.STOP;
    }
    public void reverseBrush(){
        brushPower = PowerBrush.REVERSE;
    }


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        flipServoRight = IntakeServo.flipServoRight;
        grabberServo = IntakeServo.grabberServo;
        transferServoLeft  = IntakeServo.transferServoLeft;
        transferServoRight = IntakeServo.transferServoRight;
        flipServoLeft = IntakeServo.flipServoLeft;
        outRobotServo = IntakeServo.outRobotServo;
        afterTransferServo = IntakeServo.afterTransferServo;
        twistServo = IntakeServo.twistServo;
        brushMotor = LiftHangingMotors.brushMotor;
    }

    public void update() {
        grabberServo .setPosition(grabberTarget    .get()  );
        flipServoRight.setPosition(flipGrabberPositonRight.get());
        //flipServoLeft.setPosition(flipGrabberPositonLeft.get());

        //transferServoLeft.setPosition( transferPositionLeft.get());
        transferServoRight.setPosition(transferPositionRight);

        afterTransferServo.setPosition(afterTransferGrabberPosition.get());

        outRobotServo.setPosition(outServoPosition.get());
        twistServo.setPosition(twistServoPosition.get());
        brushVolt();
    }

    public void brushVolt(){
        brushMotor.setVoltage(brushPower.get());
    }

    public FlipGrabberPositionRight getFlipGrabberPositonRight() {
        return flipGrabberPositonRight;
    }

    public void setFlipGrabberPositonRight(FlipGrabberPositionRight flipGrabberPositonRight) {
        this.flipGrabberPositonRight = flipGrabberPositonRight;
    }

    public OutServoPosition getOutServoPosition() {
        return outServoPosition;
    }

    public void setOutServoPosition(OutServoPosition outServoPosition) {
        this.outServoPosition = outServoPosition;
    }
}
