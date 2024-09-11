package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Config
public class TeleOp extends BaseOpMode{

    public static double u;
    public void doing(){
        Position target = new Position(rightStickY,rightStickX,leftStickX);
        robot.devicePool.leftBackDrive.setPower(u);

        this.telemetry.addData("vel",robot.devicePool.leftBackDrive.getVel());
        FtcDashboard.getInstance().getTelemetry().addData("vel",robot.devicePool.leftBackDrive.getVel());
        FtcDashboard.getInstance().getTelemetry().update();

    }
}
