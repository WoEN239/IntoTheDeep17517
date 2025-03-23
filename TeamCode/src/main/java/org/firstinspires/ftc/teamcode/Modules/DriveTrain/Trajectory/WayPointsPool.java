package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.ScorerGripPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.TaskDelay;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.Task;

public class WayPointsPool {
    public  Robot robot;

    public WayPointsPool(Robot robot){
        this.robot = robot;
    }

    public WayPoint lineScore = new WayPoint(
      PositionPool.fChamber,
      new Task(
              ()->true,
              ()-> LiftPosition.score = 840,
              ()->TrajectoryFollower.endDetectAngle = 5,
              ()->robot.intake.scoreAxis(),
              ()->robot.intake.setTargeted(false)
      ),
      new Task(
              TaskDelay::isDone,
              new Runnable[]{
                  ()->robot.intake.setTargeted(false),
                  ()->robot.intake.autoEat()
              },
              ()-> robot.intake.setTargeted(true),
              ()-> TaskDelay.setDelay(0.1),
              () -> robot.driveTrain.setManualPositionTarget(PositionPool.fChamber.positionPlus(0,-35,0))
      )
    );

    public WayPoint goToHumanElements = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,0,-5),
            new Task(
                    ()->true,
                    ()-> LiftPosition.score = 820,
                    ()->{
                        robot.intake.setTargeted(false);
                        Transfer.eatPos = 0.23;
                        TrajectoryFollower.localRadiusAngle = 100;
                    }
            ),
            new Task(
                    ()->true,
                    new Runnable[]{
                            ()->TrajectoryFollower.localRadiusAngle = 20,
                            ()->TrajectoryFollower.localRadius = 20,

                            ()->TrajectoryFollower.endDetectX = 3,
                            ()->TrajectoryFollower.endDetectY = 10,
                            ()->TrajectoryFollower.endDetectAngle = 2
                    },
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,0,-5)

                            )
            )
    ).toSpline(-Math.PI*3.0/8.0,Math.PI/2.0);


    public WayPoint firstHumanElementEat = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,0,-5),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                        ()->robot.intake.setTargeted(false),
                        ()->robot.intake.autoEat(),
                        ()-> Transfer.eatPos = 0.21

                    },
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,0,-5)
                    ),
                    ()->TaskDelay.setDelay(1.9)
            )
    );


    public WayPoint secondHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(23,0,0),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->Transfer.eatPos = 0.25,
                            ()->robot.intake.autoEat()
                    },
                    ()->TaskDelay.setDelay(1.9),
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(23,0,0)
                    )
            )
    );

    public WayPoint thirdHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(32,0,22),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat(),
                            ()->{
                                TrajectoryFollower.endDetectAngle = 10;
                                TrajectoryFollower.endDetectX = 10;
                                TrajectoryFollower.endDetectY = 10;
                                TrajectoryFollower.localRadiusAngle = 90;
                                TrajectoryFollower.localRadius = 70;
                            }
                    },
                    ()->TaskDelay.setDelay(1.9),
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(32,0,22)
                    )
            )
    );

    public WayPoint rotate = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(-10,-23,180),
            new Task(
                    ()->true,
                    ()->TrajectoryFollower.endDetectAngle = 20,
                    ()->TrajectoryFollower.endDetectX = 20,
                    ()->TrajectoryFollower.endDetectY = 20

            ),
            new Task(
                    ()->true,
                    ()->TrajectoryFollower.endDetectAngle = 10,
                    ()->TrajectoryFollower.endDetectX = 20,
                    ()->TrajectoryFollower.endDetectY = 20,
                    ()->robot.driveTrain.setManualPositionTarget(new Position().copyFrom(PositionPool.humanElement).positionPlus(-10,-23,180))
            )
    );

    public WayPoint goToWallFromHuman = new WayPoint(
             new Position().copyFrom( PositionPool.wall ).positionPlus(0,-10,0),
            new Task(
                    ()->true
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->TaskDelay.setDelay(0.1),
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom( PositionPool.wall ).positionPlus(0,-10,0)
                    )
            )
    );


    public WayPoint goToChamber = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->TrajectoryFollower.localRadius = 100,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat()
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(0.01),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.3);

    public WayPoint goToWall = new WayPoint(
            PositionPool.wall,
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->TaskDelay.setDelay(0.1),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.wall)
            )
    ).toSpline(-Math.PI*0.5,-Math.PI*0.3);


    public WayPoint goToChamber2 = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat()
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(0.01),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.3);

    public WayPoint goToWall2 = new WayPoint(
            PositionPool.wall,
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->TaskDelay.setDelay(0.1),
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.wall)
            )
    ).toSpline(-Math.PI*0.5,-Math.PI*0.3);

    public WayPoint goToChamber3 = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat()
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(0.01),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.3);

    public WayPoint goToWall3 = new WayPoint(
            PositionPool.wall,
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->TaskDelay.setDelay(0.1),
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.wall)
            )
    ).toSpline(-Math.PI*0.5,-Math.PI*0.3);

    public WayPoint goToChamber4 = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat()
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(0.01),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.3);

    public WayPoint goToWall4 = new WayPoint(
            PositionPool.wall.positionPlus(5,0,0),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->TaskDelay.setDelay(0.1),
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.wall.positionPlus(0,0,0))
            )
    ).toSpline(-Math.PI*0.5,-Math.PI*0.3);

    public WayPoint goToChamber5 = new WayPoint(
            PositionPool.chamber.positionPlus(0,0,0),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat()
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(0.01),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.3);

    public WayPoint goToWall5 = new WayPoint(
            PositionPool.wall,
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->TaskDelay.setDelay(0.3),
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.wall)
            )
    ).toSpline(-Math.PI*4.0/8.0,-Math.PI*0.3);


}
