package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.Task;

@Config
@TeleOp
public class PurePursuitSumulation extends BaseSimulation {
    int i = 0;
    ElapsedTime timer = new ElapsedTime();
    public static Position targetMan = new Position();

    @Override
    public void callRun() {
        timer.reset();
        robot.driveTrain.addWayPoints(new WayPoint(new Position(100,0,0),
                        new Task(
                                "edf",
                                ()->true,
                                ()->robot.driveTrain.setManualPosition(new Position(100,0,0))
                        ))
                ,
                new WayPoint(new Position(100,50,10),
                        new Task(
                                "edf",
                                ()->true,
                                ()->robot.driveTrain.setManualPosition(new Position(100,50,0))
                        ))
        );


        isNeedToCall = false;

    }
    @Override
    public void loopRun(){
    }
}
