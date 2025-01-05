package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionPool;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Robot.Team;

public abstract class BaseSimulation extends LinearOpMode {
    {
        Robot.myTeam = Team.RED;
        Robot.isDebug = true;

    }
    boolean isNeedToCall = true;
    protected Robot robot;
    void initOpMode(){
        robot = Robot.getInstance();
        robot.init(this);
        DriveTrainSimulation.position.copyFrom(PositionPool.redStart);
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
            if(isNeedToCall){
                callRun();
            }
            loopRun();
            robot.update();
        }
      //  System.exit(0);
    }
    public abstract void loopRun()   ;
    public void callRun(){};
}
