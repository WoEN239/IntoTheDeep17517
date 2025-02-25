package org.firstinspires.ftc.teamcode.OpModes.Auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.LineFollower.LineSegmentFollower;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeManager;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.PurePursuitTask;

@Autonomous
public class ChamberAuto extends BaseMode {

    public void callRun() {
        robot.driveTrain.setState(DriveTrainManager.RobotState.TRAVELING);
        IntakeManager.setState(IntakeManager.IntakeState.SAMPLE_IN_GRIP);

        LineSegmentFollower.localRadius = 15;
        robot.intake.update();

        robot.driveTrain.addWayPoints(
                new WayPoint(
                        PositionPool.start,
                        new PurePursuitTask(
                                "move to basket",
                                () ->true,
                                () ->robot.driveTrain.setManualPosition(PositionPool.chamberHigh)
                        ))
        );
    }


    @Override
    public void loopRun() {

    }
}