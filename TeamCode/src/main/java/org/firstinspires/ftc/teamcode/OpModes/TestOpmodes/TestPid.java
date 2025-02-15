package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@TeleOp
public class TestPid extends BaseMode {
    public static boolean isSin = false;
    public static double u = 0;
    public static double a = 0;
    public void loopRun() {
        DriveTrainMotors.rightForwardDrive.pidStatusB.setTelemetry(true);
        if (isSin) {
            DriveTrainMotors.rightForwardDrive.setVel(Math.sin(robot.getSeconds() / Math.PI * u) * a);
        } else {
            DriveTrainMotors.rightForwardDrive.setVel(u);
        }
    }
}
