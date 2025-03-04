package org.firstinspires.ftc.teamcode.OpModes.Auto;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;
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
      PositionPool.chamber,
      new Task(
              ()->true,
              ()->robot.intake.scoreAxis(),
              ()->robot.intake.setTargeted(false)
      ),
      new Task(
              ()->TaskDelay.isDone(),
              new Runnable[]{
                  ()->robot.intake.setTargeted(false)
              },
              () -> robot.intake.setTargeted(true),
              ()->TaskDelay.setDelay(1),
              () -> robot.driveTrain.setManualPosition(PositionPool.chamber)
      )
    );

    public WayPoint goToHumanElements = new WayPoint(
            PositionPool.humanElement,
            new Task(
                    ()->true,
                    ()->{
                        robot.intake.setTargeted(false);
                        robot.intake.autoEat();
                        Transfer.eatPos = 0.27;
                    }
            ),
            new Task(
                    "target",
                    ()->true,
                    ()->robot.driveTrain.setManualPosition(PositionPool.humanElement)
            )
    ).toSpline(-Math.PI*3.0/8.0,Math.PI/2.0);


    public WayPoint firstHumanElement = new WayPoint(
            PositionPool.humanElement,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(true)
            ),
            new Task(
                    TaskDelay::isDone,
                    ()->robot.intake.setTargeted(false),
                    ()->robot.driveTrain.setManualPosition(PositionPool.humanElement),
                    ()->TaskDelay.setDelay(0.5)
            )
    );

    public WayPoint scoreFirstHumanElement = new WayPoint(
      PositionPool.humanScore,
          new Task(
                  ()->true,
                  new Runnable[]{
                          ()->robot.intake.setTargeted(false),
                          ()->robot.intake.autoEat(),
                          ()->robot.intake.rotateEater(15),
                          ()->Transfer.eatPos = 0.28
                  },
                  ()->robot.intake.setTargeted(true),
                  ()->robot.driveTrain.setManualPosition(PositionPool.humanScore)
          )
    );

    public WayPoint secondHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,5,22),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false),
                    ()->robot.driveTrain.setManualPosition(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,5,22)
                    )
            )
    );

    public WayPoint scoreSecondHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(0,5,22),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(true)
            ),
            new Task(
                    ()->true,
                    new Runnable[]{
                            ()->robot.intake.autoEat(),
                            ()->Transfer.eatPos = 0.36,
                            ()->robot.intake.rotateEater(15),
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->robot.intake.setTargeted(false),
                    ()->robot.driveTrain.setManualPosition(PositionPool.humanScore)
            )
    );



    public WayPoint thirdHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(5,5,37),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false),
                    ()->robot.driveTrain.setManualPosition(
                            new Position().copyFrom(PositionPool.humanElement).positionPlus(5,5,37)
                    )
            )
    );

    public WayPoint scoreThirdHumanElement = new WayPoint(
            new Position().copyFrom(PositionPool.humanElement).positionPlus(5,5,37),
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(true)
            ),
            new Task(
                    ()->true,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->robot.intake.setTargeted(false),
                    ()->robot.driveTrain.setManualPosition(PositionPool.humanScore)
            )
    );

    public WayPoint goToWall = new WayPoint(
            PositionPool.wall,
            new Task(
                    ()->true,
                    ()->robot.intake.setTargeted(false),
                    ()->robot.intake.wallEat()
            ),
            new Task(
                    ()->true,
                    new Runnable[]{
                            ()->robot.intake.setTargeted(true)
                    },
                    ()->robot.driveTrain.setManualPosition(PositionPool.wall)

            )
    );

}
