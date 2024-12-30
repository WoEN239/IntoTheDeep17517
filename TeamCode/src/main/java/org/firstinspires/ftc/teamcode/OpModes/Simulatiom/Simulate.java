package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionsPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.IntakeSimulation;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;
@Config
@TeleOp
public class Simulate extends BaseSimulation {
    int i = 0;
    boolean isFirst = true;
    boolean isEnd = false;
    ElapsedTime timer = new ElapsedTime();
    @Override
    public void doing() {
        DriveTrainSimulation.updatePosition();
        if(isFirst) {
            timer.reset();
            robot.purePursuit.addWayPoints(
                    new WayPoint(new Position().copyFrom(PositionsPool.redCenterScoring),
                            new PurePursuitTask(
                                    "Up Lift",
                                    IntakeSimulation::isDone,
                                    () -> IntakeSimulation.setDelay(3)
                            ),
                            new PurePursuitTask(
                                    "Scoring",
                                    IntakeSimulation::isDone,
                                    () -> IntakeSimulation.setDelay(5)
                            )
                    ),
                    new WayPoint(new Position().copyFrom(PositionsPool.redBasketScoring),
                            new PurePursuitTask(
                                    "Up two Lift",
                                    IntakeSimulation::isDone,
                                    () -> IntakeSimulation.setDelay(3)
                            )
                            ,new PurePursuitTask(
                                    "Scoring into basket",
                                    IntakeSimulation::isDone,
                                    ()->IntakeSimulation.setDelay(5)
                            )
                    ),
                    new WayPoint(new Position().copyFrom(PositionsPool.redCenterEat))

            );
            isFirst = false;
        }
        FieldView.position = DriveTrainSimulation.position;
        FieldView.circle = robot.positionController.getGlobalTarget();
        FieldView.updateField();
    }
}
