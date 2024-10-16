package org.firstinspires.ftc.teamcode.Modules.Intake;


import org.firstinspires.ftc.teamcode.Devices.GrabberAndTransferServo;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Robot;

public class FinalStateMachine {

    Robot robot;
    GrabberAndTransferServo grabber;
    LiftController lift;

    public FinalStateMachine(Robot robot){
        this.robot = robot;
    }
}
