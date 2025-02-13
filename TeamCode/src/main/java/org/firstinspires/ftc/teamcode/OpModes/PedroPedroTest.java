package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.CompositePositionPath;
import com.acmerobotics.roadrunner.PositionPathSeqBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.PositionPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;

import java.util.List;

@TeleOp
public class PedroPedroTest extends BaseMode{

    public void callRun(){
        robot.driveTrain.setState(DriveTrainManager.RobotState.PEDRO_PEDRO);

        robot.driveTrain.addWayPoints(
                new WayPoint( new Position() ).toSpline(0,3.14)
        );

        isNeedToCall = false;
    }
    public void loopRun(){
        robot.fieldView.position = robot.driveTrain.getPosition();
        //robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
