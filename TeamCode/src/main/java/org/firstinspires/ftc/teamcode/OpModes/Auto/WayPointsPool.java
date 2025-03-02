package org.firstinspires.ftc.teamcode.OpModes.Auto;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.PurePursuitTask;

public class WayPointsPool {
    public static Robot robot;
    static  {
        robot = Robot.getInstance();
    }
    public static WayPoint lineScore = new WayPoint(
      PositionPool.chamber,
      new PurePursuitTask(
              ()->true,
              ()->robot.intake.scoreAxis(),
              ()->robot.intake.setTargeted(false)
      ),
      new PurePursuitTask(
              () -> robot.intake.isDone(),
              new Runnable[]{
                      ()->robot.intake.setTargeted(false)
              },
              () -> robot.intake.setTargeted(true),
              () -> robot.driveTrain.setManualPosition(PositionPool.chamber)
      )
    );

    public static WayPoint goToHumanElements = new WayPoint(
            PositionPool.humanElement,
            new PurePursuitTask(
                    ()->true,
                    ()->{
                        robot.intake.setTargeted(false);
                        robot.intake.centerEat();
                        Transfer.eatPos = 0.32;
                    }
            ),
            new PurePursuitTask(
                    "target",
                    ()->true,
                    ()->robot.driveTrain.setManualPosition(PositionPool.humanElement)
            )
    ).toSpline(-Math.PI/2.0,Math.PI/2.0);

    public static WayPoint firstHumanElement = new WayPoint(
            PositionPool.humanElement,
            new PurePursuitTask(
                    ()->robot.intake.setTargeted(true),
                    ()->robot.driveTrain.setManualPosition(PositionPool.humanElement)
            )
    );

}
