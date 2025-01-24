package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.GripPositions;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.IntakeSimulation;
import org.firstinspires.ftc.teamcode.Robot.Team;

/**
 * Writing by EgorKhvostikov and @MrFrosty1234
 */

@Autonomous
public class Auto extends BaseMode {

    public void initServo(){
        IntakeDevices.gripServo.setPosition(GripPositions.close);

    }

    @Override
    public void callRun() {
        robot.driveTrain.setState(DriveTrainManager.RobotState.TRAVELING);
        Robot.myTeam = Team.RED;
        robot.intake.update();
        robot.driveTrain.addWayPoints(
                new WayPoint(
                new Position().copyFrom(PositionPool.blueCenterScoring),

                new PurePursuitTask(
                        "liftUp",
                        ()->true,
                        ()->robot.intake.setTargeted(false),
                        ()->robot.intake.scoreAxis()
                ),

                new PurePursuitTask(
                        "score",
                        IntakeSimulation::isDone,
                        ()->IntakeSimulation.setDelay(3),
                        ()->robot.intake.setTargeted(true),
                        ()->robot.driveTrain.setManualPosition(new Position().copyFrom(PositionPool.blueCenterScoring))
                )),

                new WayPoint(
                        new Position().copyFrom(PositionPool.blueHuman),
                        new PurePursuitTask(
                                "target",
                                IntakeSimulation::isDone,
                                ()->IntakeSimulation.setDelay(1),
                                ()->robot.driveTrain.setManualPosition(new Position()
                                        .copyFrom(PositionPool.blueHuman)
                                        .positionPlus(new Position(0,0,180))
                                ),
                                ()-> robot.intake.setTargeted(false),
                                ()-> robot.intake.wallEat()
                )),

                new WayPoint(new Position()
                        .copyFrom(PositionPool.blueHuman)
                        .positionPlus(new Position(5,0,0)),
                new PurePursuitTask("eat",
                    IntakeSimulation::isDone,
                    ()->IntakeSimulation.setDelay(2.1),
                    ()->robot.intake.setTargeted(true))
                )
        );
                isNeedToCall = false;
    }
    @Override
    public void loopRun(){
        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle = robot.driveTrain.getPidTarget();
    }
}
