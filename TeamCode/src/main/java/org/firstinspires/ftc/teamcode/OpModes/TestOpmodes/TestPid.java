package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;

@Config
@TeleOp
public class TestPid extends BaseOpMode {
    public static boolean isSin = false;
    public static double u = 0;
    public static double a = 0;

    public void doing() {
        if (isSin) {
            DriveTrainMotors.leftBackDrive.setVel(Math.sin(robot.timer.seconds() / Math.PI * u) * a);
        } else {
            DriveTrainMotors.leftBackDrive.setVel(u);
        }
    }
}
