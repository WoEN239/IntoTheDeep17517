package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@TeleOp
public class TestFilter extends BaseMode {
    public static boolean isSin = false;
    public static double u = 0;
    public static double a = 0;

    public void loopRun() {
        if (isSin) {
            DriveTrainMotors.leftBackDrive.setPower(Math.sin(robot.timer.seconds() / Math.PI * u) * a);
        } else {
            DriveTrainMotors.leftBackDrive.setPower(u);
        }
        FtcDashboard.getInstance().getTelemetry().addData("Rev Vel", DriveTrainMotors.leftBackDrive.dev.getVelocity());
        FtcDashboard.getInstance().getTelemetry().addData("u", u);
        FtcDashboard.getInstance().getTelemetry().update();

    }
}
