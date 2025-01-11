package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.Devices.IntakeServo;
import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.Intake.SampleSensor.ColorDetective;
import org.firstinspires.ftc.teamcode.Modules.Intake.SampleSensor.ColorSensorListener;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;

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


    private GrabberPosition grabberTarget = GrabberPosition.OPEN;
    private FlipGrabberPosition flipGrabberPositon = FlipGrabberPosition.UNSPREADOUT;
    private TransferPosition transferPosition = TransferPosition.NORMAL;
    private AfterTransferGrabber afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
    private OutServoPosition outServoPosition = OutServoPosition.IN_ROBOT;
    private TwistServo twistServoPosition = TwistServo.EAT;
    private PowerBrush brushPower = PowerBrush.STOP;

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
        flipServoRight.setPosition(flipGrabberPositon.get());
        flipServoLeft.setPosition(flipGrabberPositon.get());
        transferServoLeft.setPosition(transferPosition.get());
        transferServoRight.setPosition(transferPosition.get());
        afterTransferServo.setPosition(afterTransferGrabberPosition.get());
        outRobotServo.setPosition(outServoPosition.get());
        twistServo.setPosition(twistServoPosition.get());
        brushVolt();
    }

    public void brushVolt(){
        if(brushMotor.dev.getCurrent(CurrentUnit.AMPS) > volt){
            brushPower = PowerBrush.REVERSE;
        }
        brushMotor.setVoltage(brushPower.get());
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
