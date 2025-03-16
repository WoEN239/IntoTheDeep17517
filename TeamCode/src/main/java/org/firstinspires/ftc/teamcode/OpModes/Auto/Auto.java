package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrain;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.FlipPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.ScorerGripPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Writing by EgorKhvostikov
 */

@Autonomous
public class Auto extends BaseMode {

    public void initServo(){
        IntakeDevices.flipRight.setPosition(FlipPosition.up);
        IntakeDevices.transferRight.setPosition(TransferPosition.in);

        IntakeDevices.scorerGrip.setPosition(ScorerGripPosition.close);
    }

    @Override
    public void callRun() {
        robot.driveTrain.setState(DriveTrain.RobotState.TRAVELING);
        robot.driveTrain.addWayPoints(
                wayPointsPool.lineScore,
                wayPointsPool.goToHumanElements,

                wayPointsPool.firstHumanElementEat,
                //        wayPointsPool.bringFirstHumanElement,

                wayPointsPool.secondHumanElement,
//                wayPointsPool.bringSecondHumanElement,

                wayPointsPool.thirdHumanElement,

                wayPointsPool.rotate,

                wayPointsPool.goToWallFromHuman,

                wayPointsPool.goToChamber,
                wayPointsPool.goToWall,

                wayPointsPool.goToChamber2,
                wayPointsPool.goToWall2,

                wayPointsPool.goToChamber3,
                wayPointsPool.goToWall3,

                wayPointsPool.goToChamber4,
                wayPointsPool.goToWall4,

                wayPointsPool.goToChamber5,
                wayPointsPool.goToWall5,


                new WayPoint(new Position())

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