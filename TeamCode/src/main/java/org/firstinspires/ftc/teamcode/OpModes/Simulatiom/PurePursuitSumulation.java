package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.IntakeSimulation;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;
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
                        new PurePursuitTask(
                                "edf",
                                ()->true,
                                ()->robot.driveTrain.setManualPodition(new Position(100,0,0))
                        ))
                ,
                new WayPoint(new Position(100,50,10),
                        new PurePursuitTask(
                                "edf",
                                ()->true,
                                ()->robot.driveTrain.setManualPodition(new Position(100,50,0))
                        ))
        );


        isNeedToCall = false;

    }
    @Override
    public void loopRun(){
        FieldView.position = DriveTrainSimulation.position;
        FieldView.circle = robot.driveTrain.getPidTarget();
        FieldView.updateField();
    }
}
