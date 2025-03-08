package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;

@Config

public class PidSimulation extends BaseSimulation{
    public static Position start = new Position();
    public static Position end   = new Position();
    public static Position target = new Position();
    private Position position = new Position();

    @Override
    public void callRun() {

        isNeedToCall = false;
    }

    @Override
    public void loopRun(){
      position = DriveTrainSimulation.position;

      robot.driveTrain.setState(DriveTrainManager.RobotState.POINT);
      robot.driveTrain.setManualPosition(target);

    }

}
