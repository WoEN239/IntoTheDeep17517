package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.ScorerGripPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer.Transfer;
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
              ()->robot.intake.scoreAxis(),
              ()->robot.intake.setTargeted(false)
      ),
      new Task(
              TaskDelay::isDone,
              new Runnable[]{
                  ()->robot.intake.setTargeted(false)
              },
              () -> robot.intake.setTargeted(true),
              ()->TaskDelay.setDelay(2),
              () -> robot.driveTrain.setManualPositionTarget(PositionPool.fChamber)
      )
    );

    public WayPoint goToHumanElements = new WayPoint(
            PositionPool.humanElement,
            new Task(
                    ()->true,
                    ()->{
                        robot.intake.setTargeted(false);
                        robot.intake.autoEat();
                        Transfer.eatPos = 0.32;
                    }
            ),
            new Task(
                    "target",
                    ()->true,
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.humanElement)
            )
    ).toSpline(-Math.PI*3.0/8.0,Math.PI/2.0);


    public WayPoint firstHumanElementEat = new WayPoint(
            PositionPool.humanElement,
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                        ()->robot.intake.setTargeted(false)
                    },
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.humanElement),
                    ()->TaskDelay.setDelay(2)
            )
    );

    public WayPoint bringFirstHumanElement = new WayPoint(
          new Position().copyFrom(PositionPool.humanElement).positionPlus(10,0,0),
          new Task(
                  ()->true
          ),
          new Task(
                  ()->true,
                  new Runnable[]{
                          ()->robot.intake.setTargeted(false),
                          ()->robot.intake.autoEat(),
                          ()->Transfer.eatPos = 0.37
                  },
                  ()->robot.intake.setTargeted(true),
                  ()->robot.driveTrain.setManualPositionTarget(
                          new Position().copyFrom(PositionPool.humanElement).positionPlus(10,0,0)
                  )
          )
    );

    public WayPoint secondHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(10,0,0),
            new Task(
                    TaskDelay::isDone,
                    ()->TaskDelay.setDelay(0.5),
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(10,0,0)
                            )
            )
    );

    public WayPoint bringSecondHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(20,0,0),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat(),
                    },
                    ()->TaskDelay.setDelay(0.6),
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(10,0,0)
                    )
            )
    );



    public WayPoint thirdHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,0,32),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,0,32)
                    )
            )
    );

    public WayPoint bringThirdHumanElement = new WayPoint(
            PositionPool.humanScore,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false),
                            ()->robot.intake.wallEat(),
                    },
                    ()->TaskDelay.setDelay(0.6),
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.humanScore)
            )
    );

    public WayPoint goToWallFromHuman = new WayPoint(
            PositionPool.wall,
            new Task(
                    TaskDelay::isDone,
                    ()-> TaskDelay.setDelay(1.3)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->TaskDelay.setDelay(0.5),
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.wall)
            )
    );


    public WayPoint goToChamber = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false)
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(1),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.5);

    public WayPoint goToWall = new WayPoint(
            PositionPool.wall,
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close),
                    ()->TaskDelay.setDelay(0.3),
                    ()->robot.driveTrain.setManualPositionTarget(PositionPool.wall)
            )
    ).toSpline(-Math.PI*3.0/8.0,-Math.PI*0.5);


    public WayPoint goToChamber2 = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false)
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(1),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.5);

    public WayPoint goToWall2 = new WayPoint(
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
    ).toSpline(-Math.PI*3.0/8.0,-Math.PI*0.5);

    public WayPoint goToChamber3 = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false)
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(1),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.5);

    public WayPoint goToWall3 = new WayPoint(
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
    ).toSpline(-Math.PI*3.0/8.0,-Math.PI*0.5);

    public WayPoint goToChamber4 = new WayPoint(
            PositionPool.chamber,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false)
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(1),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.5);

    public WayPoint goToWall4 = new WayPoint(
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
    ).toSpline(-Math.PI*3.0/8.0,-Math.PI*0.5);

    public WayPoint goToChamber5 = new WayPoint(
            PositionPool.chamber.positionPlus(5,0,0),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false)
            ),
            new Task(
                    TaskDelay::isDone,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(false)
                    },
                    () -> robot.intake.setTargeted(true),
                    ()->TaskDelay.setDelay(1),
                    () -> robot.driveTrain.setManualPositionTarget(PositionPool.chamber)
            )
    ).toSpline(Math.PI*0.75,Math.PI*0.5);

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
    ).toSpline(-Math.PI*3.0/8.0,-Math.PI*0.5);


}
