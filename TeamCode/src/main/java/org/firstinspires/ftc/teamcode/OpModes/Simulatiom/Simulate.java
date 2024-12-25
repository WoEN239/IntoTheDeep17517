package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import android.content.Context;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;
@Config
@TeleOp
public class Simulate extends BaseSimulation {
    public static Position target = new Position();

    int i = 0;
    @Override
    public void doing() {
        //Robot.telemetry.addData("i",++i);
        Robot.telemetry.addData("xPos",DriveTrainSimulation.position.x);
        Robot.telemetry.addData("yPos",DriveTrainSimulation.position.y);
        Robot.telemetry.addData("hPos",DriveTrainSimulation.position.h);

        FieldView.target = robot.positionController.getGlobalTarget();
        FieldView.updateField(DriveTrainSimulation.position);

        robot.positionController.move( target );
        DriveTrainSimulation.updatePosition();

    }
}
