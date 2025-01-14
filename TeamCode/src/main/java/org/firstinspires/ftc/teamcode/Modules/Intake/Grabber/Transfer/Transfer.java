package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Transfer;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Transfer.Position.AfterTransferGrabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Transfer.Position.OutServoPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Transfer.Position.TwistServo;
import org.firstinspires.ftc.teamcode.Robot.Robot;
@Config
/*
 * Writing by @MrFrosty1234
 */
public class Transfer {
        Robot robot;
        private Servo afterTransferServo;
        private Servo outRobotServo;
        private Servo twistServo;


        public AfterTransferGrabber afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
        public OutServoPosition outServoPosition = OutServoPosition.IN_ROBOT;
        public TwistServo twistServoPosition = TwistServo.EAT;

        public void inOutServo() {
            outServoPosition = OutServoPosition.IN_ROBOT;
        }

        public void outOutServo() {
            outServoPosition = OutServoPosition.OUT_ROBOT;
        }

        public void closeAfterTransferServo() {
            afterTransferGrabberPosition = AfterTransferGrabber.CLOSE;
        }

        public void openAfterTransferServo() {
            afterTransferGrabberPosition = AfterTransferGrabber.OPEN;
        }

        public void toEatTwistServo() {
            twistServoPosition = TwistServo.EAT;
        }

        public void toEatFromWallTwistServo() {
            twistServoPosition = TwistServo.EAT_FROM_WALL;
        }

        public void toDropTwistServo() {
            twistServoPosition = TwistServo.DROP;
        }


        public void init(Robot robot) {
            this.robot = robot;
            outRobotServo = IntakeDevices.outRobotServo;
            afterTransferServo = IntakeDevices.afterTransferServo;
            twistServo = IntakeDevices.twistServo;
        }

        public void update() {
            afterTransferServo.setPosition(afterTransferGrabberPosition.get());
            outRobotServo.setPosition(outServoPosition.get());
            twistServo.setPosition(twistServoPosition.get());
        }
    }
