package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegmentFollower;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.FlipGrabberPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.GripPositions;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeManager;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.TaskDelay;

/**
 * Writing by EgorKhvostikov and @MrFrosty1234
 */

@Autonomous
public class Auto extends BaseMode {

    public void initServo(){
        IntakeDevices.flipServoRight.setPosition(FlipGrabberPosition.clear );
        IntakeDevices.gripServo.setPosition(GripPositions.close);
    }

    @Override
    public void callRun() {
        robot.driveTrain.setState(DriveTrainManager.RobotState.TRAVELING);
        IntakeManager.setState(IntakeManager.IntakeState.SAMPLE_IN_GRIP);

        LineSegmentFollower.localRadius = 15;
        TransferPosition.eat = 0.7;
        robot.intake.update();
        robot.driveTrain.addWayPoints(
                /*lift up*/
                new WayPoint(
                        PositionPool.start,
                        new PurePursuitTask(
                                "first up lift",
                                ()->robot.intake.isLiftDone() && robot.intake.getLiftPos()>100,
                                ()->robot.driveTrain.setManualPosition(PositionPool.start),
                                ()->
                                {
                                    robot.intake.setTargeted(false);
                                    robot.intake.scoreBasket();
                                }
                        )
                ),
                //move to basket
                new WayPoint(
                        PositionPool.basketScoring,
                        new PurePursuitTask(
                                "move to basket",
                                ()->true,
                                ()->robot.driveTrain.setManualPosition(PositionPool.basketScoring)
                        )
                ),

                //score 1
                new WayPoint(
                        PositionPool.firstElement,
                        new PurePursuitTask(
                                "score",
                                ()->robot.intake.isDone(),
                                ()->robot.intake.setTargeted(true),
                                ()->robot.driveTrain.setManualPosition(PositionPool.firstElement)
                        ),

                        //brush start
                        new PurePursuitTask(
                                "task for start brush",

                                ()->TaskDelay.isDone(),

                                ()-> TaskDelay.setDelay(2),
                                ()->robot.driveTrain.setManualPosition(PositionPool.firstElement),
                                ()->robot.intake.brushEat()
                        )
                ),
                // move for eat
                new WayPoint(
                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(2,-35,0)),
                        new PurePursuitTask(
                                "Move forward to eat",
                                ()->true
                        ),
                        new PurePursuitTask(
                                " wait sample",
                                ()->robot.intake.isDone(),
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(2,-35,0)))
                        )
                ),

                // up lift
                new WayPoint(
                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(0,0,45)),
                        new PurePursuitTask(
                                "up lift",
                                ()->robot.intake.isLiftDone() && robot.intake.getLiftPos()>1000,
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(0,0,45))
                                ),
                                ()->
                                {
                                    robot.intake.setTargeted(false);
                                    robot.intake.scoreBasket();
                                }
                        )
                        ,new PurePursuitTask(
                        "",
                        ()->true,
                        ()->robot.driveTrain.setManualPosition(
                                new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(0,0,45))
                        )
                )
                ),

                //move to basket
                new WayPoint(
                        new Position().copyFrom(PositionPool.basketScoring).positionPlus(new Position(5,0,0)),
                        new PurePursuitTask(
                                "",
                                ()->true,
                                ()->robot.intake.setTargeted(true),
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.basketScoring).positionPlus(new Position(5,0,0))
                                )
                        )
                ),

                //score 2
                new WayPoint(
                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(25,0,0)),
                        new PurePursuitTask(
                                "score two",
                                ()->robot.intake.isDone(),
                                ()->robot.intake.setTargeted(true),
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(25,0,0))
                                )
                        ),

                        //brush start
                        new PurePursuitTask(
                                "task for start brush",

                                ()->TaskDelay.isDone(),

                                ()-> TaskDelay.setDelay(2),
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(30,0,0))
                                ),
                                ()->robot.intake.brushEat()
                        )
                ),




                // move for eat
                new WayPoint(
                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(32,-35,0)),
                        new PurePursuitTask(
                                "Move forward to eat",
                                ()->true
                        ),
                        new PurePursuitTask(
                                " wait sample",
                                ()->robot.intake.isDone(),
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(32,-35,0)))
                        )
                ),

                // up lift
                new WayPoint(
                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(0,0,45)),
                        new PurePursuitTask(
                                "up lift",
                                ()->robot.intake.isLiftDone() && robot.intake.getLiftPos()>1000,
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(0,0,45))
                                ),
                                ()->
                                {
                                    robot.intake.setTargeted(false);
                                    robot.intake.scoreBasket();
                                }
                        )
                        ,new PurePursuitTask(
                        "",
                        ()->true,
                        ()->robot.driveTrain.setManualPosition(
                                new Position().copyFrom(PositionPool.firstElement).positionPlus(new Position(0,0,45))
                        )
                )
                ),


                //move to basket
                new WayPoint(
                        new Position().copyFrom(PositionPool.basketScoring).positionPlus(new Position(9,0,0)),
                        new PurePursuitTask(
                                "",
                                ()->true,
                                ()->robot.intake.setTargeted(false),
                                ()->robot.driveTrain.setManualPosition(
                                    new Position().copyFrom(PositionPool.basketScoring).positionPlus(new Position(9,0,0))
                                )
                        )
                ),


                //score 3
                new WayPoint(
                        new Position().copyFrom(PositionPool.park).positionPlus(new Position(0,0,0)),
                        new PurePursuitTask(
                                "score",
                                ()->robot.intake.isDone(),
                                ()->robot.intake.setTargeted(true),
                                ()-> LineSegmentFollower.localRadius = 50,
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.park).positionPlus(new Position(0,0,0))
                                )
                        ),

                        //brush start
                        new PurePursuitTask(
                                "task for start brush",
                                ()->TaskDelay.isDone(),

                                ()-> TaskDelay.setDelay(2),
                                ()->robot.driveTrain.setManualPosition(
                                        new Position().copyFrom(PositionPool.park).positionPlus(new Position(0,0,0)))

                        )
                )


        );

        isNeedToCall = false;
    }
    @Override
    public void loopRun(){
        Robot.telemetryPacket .put( "robot pos", robot.driveTrain.getPosition().toString());
        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
