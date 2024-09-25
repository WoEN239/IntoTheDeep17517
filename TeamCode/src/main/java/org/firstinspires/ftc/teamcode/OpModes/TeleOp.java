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
            robot.devicePool.leftBackDrive.setVel(Math.sin(robot.timer.seconds())*u);
        }else {
            robot.devicePool.leftBackDrive.setVel(u);
        }


        FtcDashboard.getInstance().getTelemetry().addData("vel",robot.devicePool.leftBackDrive.getVelocity());
        FtcDashboard.getInstance().getTelemetry().addData("u",u);
        FtcDashboard.getInstance().getTelemetry().update();

    }
}
