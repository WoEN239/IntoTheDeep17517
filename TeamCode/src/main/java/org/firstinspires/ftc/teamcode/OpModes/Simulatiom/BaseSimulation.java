package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

public abstract class BaseSimulation extends LinearOpMode {
    {
        Robot.isDebug = true;
    }
    Robot robot;
    void initOpMode(){
        robot = new Robot(this);
        robot.initSimulation();
        DriveTrainSimulation.position = robot.positionListener.getPositionGlobal();
        FieldView.updateField(DriveTrainSimulation.position);
    }
    boolean firstInit = true;
    @Override
    public void runOpMode(){
        if(firstInit) {
            initOpMode();
            firstInit = false;
        }
        waitForStart();
        while (opModeIsActive()){
            doing();
            robot.updatePPTasks();
            robot.updateSimulation();
            DriveTrainSimulation.updatePosition();
            FieldView.updateField(DriveTrainSimulation.position);
        }
    }
    public abstract void doing();
}
