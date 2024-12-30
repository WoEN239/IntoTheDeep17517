package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionsPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PurePursuit;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Robot.Team;

public abstract class BaseSimulation extends LinearOpMode {
    {
        Robot.myTeam = Team.RED;
        Robot.isDebug = true;
        PurePursuit.startPosition = PositionsPool.redStart;

    }
    Robot robot;
    void initOpMode(){
        robot = new Robot(this);
        robot.initSimulation();
        DriveTrainSimulation.position.copyFrom(PositionsPool.redStart);

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
        }
    }
    public abstract void doing();
}
