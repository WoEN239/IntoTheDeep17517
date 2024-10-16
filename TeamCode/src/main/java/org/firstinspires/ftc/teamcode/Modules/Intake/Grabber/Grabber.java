package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by @MrFrosty1234
 */

public class Grabber implements Controller {
    Robot robot;

    private final Servo grabberServo;
    private final Servo grabberServoUp;
    private Servo transferServoSecond;
    private final Servo transferServoFirst;


    public Grabber(Robot robot){
        this.robot = robot;
        grabberServoUp = robot.devicePool.grabber.grabberServoUp;
        grabberServo = robot.devicePool.grabber.grabberServo;
        transferServoFirst = robot.devicePool.grabber.transferServoFirst;
        transferServoSecond = robot.devicePool.grabber.transferServoSecond;
    }

    private GrabberPosition grabberTarget = GrabberPosition.OPEN;
    private GrabberUpPosition grabberUpPosition = GrabberUpPosition.FINISH;
    private TransferPosition transferPosition = TransferPosition.NORMAL;

    public  GrabberPosition getGrabberTarget(){return grabberTarget;}

    public GrabberUpPosition getGrabberUpPosition(){return grabberUpPosition;}

    public TransferPosition getTransferPosition(){return transferPosition;}

    public void close(){ grabberTarget = GrabberPosition.CLOSE;}

    public void open(){grabberTarget = GrabberPosition.OPEN;}

    public void transferToGrab(){transferPosition = TransferPosition.GRAB;}

    public void transferToNormal(){transferPosition = TransferPosition.NORMAL;}

    public void closeUpGrabber(){grabberUpPosition = GrabberUpPosition.FINISH;}

    public void openUpGrabber(){grabberUpPosition = GrabberUpPosition.START;}


    @Override
    public void init(Robot robot) {

    }

    public void update(){
        grabberServo.setPosition(grabberTarget.get());
        grabberServoUp.setPosition(grabberUpPosition.get());
        transferServoFirst.setPosition(transferPosition.get());
    }



}
