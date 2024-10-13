package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

@Config
@TeleOp
public class TestFilter extends BaseOpMode {
    public static boolean isSin = false ;
    public static double u = 0;
    public static double a = 0;
    public void doing(){
        if(isSin){
            DevicePool.leftBackDrive.setPower(Math.sin(robot.timer.seconds()/Math.PI*u)*a);
        }else {
            DevicePool.leftBackDrive.setPower(u);
        }
        FtcDashboard.getInstance().getTelemetry().addData("Rev Vel", DevicePool.leftBackDrive.dev.getVelocity());
        FtcDashboard.getInstance().getTelemetry().addData("u",u);
        FtcDashboard.getInstance().getTelemetry().update();

    }
}
