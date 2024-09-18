package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
@Config
public class TeleOp extends BaseOpMode{
    public static boolean isSin = false ;
    public static double u = 0;
    public void doing(){
        Position target = new Position(rightStickY,rightStickX,leftStickX);
        if(isSin){
            robot.devicePool.leftBackDrive.setPower(Math.sin(robot.timer.seconds()*u));
        }else {
            robot.devicePool.leftBackDrive.setPower(u);
        }

        this.telemetry.addData("vel",robot.devicePool.leftBackDrive.getVel());
        FtcDashboard.getInstance().getTelemetry().addData("vel",robot.devicePool.leftBackDrive.getVel());
        FtcDashboard.getInstance().getTelemetry().addData("u",Math.sin(robot.timer.seconds()*u));
        FtcDashboard.getInstance().getTelemetry().addData("velFrom getVelocity()",robot.devicePool.leftBackDrive.dev.getVelocity());
        FtcDashboard.getInstance().getTelemetry().update();

    }
}
