package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

@Config
@TeleOp
public class TestFilter extends BaseOpMode {
    public static boolean isSin = false ;
    public static double u = 0;
    public void doing(){
        if(isSin){
            robot.devicePool.driveTrainMotors.leftBackDrive.setPower(Math.sin(robot.timer.seconds())*u);
        }else {
            robot.devicePool.driveTrainMotors.leftBackDrive.setPower(u);
        }

        FtcDashboard.getInstance().getTelemetry().addData("Rev Vel",robot.devicePool.driveTrainMotors.leftBackDrive.getPosition());
        FtcDashboard.getInstance().getTelemetry().addData("u",u);
        FtcDashboard.getInstance().getTelemetry().update();

    }
}
