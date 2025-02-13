package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.CompositePositionPath;
import com.acmerobotics.roadrunner.PositionPathSeqBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionPool;
import org.firstinspires.ftc.teamcode.Robot.Robot;

import java.util.List;

@TeleOp
public class PedroPedroTest extends BaseMode{

    public void callRun(){
        robot.driveTrain.setState(DriveTrainManager.RobotState.PEDRO_PEDRO);
        List<CompositePositionPath<Arclength>> path =   new PositionPathSeqBuilder(
                PositionPool.start.toRRPosition().position,0,1e-6)
                .splineTo(new Vector2d(100,0),Math.PI)
                .build();
        robot.driveTrain.addTrajectory(path);

        robot.fieldView.path = path.get(0);
        isNeedToCall = false;
    }
    public void loopRun(){
        robot.fieldView.position = robot.driveTrain.getPosition();
        //robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
