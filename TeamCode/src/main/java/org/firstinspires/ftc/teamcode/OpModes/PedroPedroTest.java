package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.PurePursuitTask;


@TeleOp
public class PedroPedroTest extends BaseMode{

    public void callRun(){

        robot.driveTrain.addWayPoints(
                new WayPoint( new Position(100,-100,0),
                new PurePursuitTask("",()->true)
                ).toSpline(0,- 3.14/2.0),

                new WayPoint(PositionPool.start,
                new PurePursuitTask("",()->true)
                ).toSpline(0,3.14 )
        );

        isNeedToCall = false;
    }
    public void loopRun(){
        robot.fieldView.position = robot.driveTrain.getPosition();
    }
}
