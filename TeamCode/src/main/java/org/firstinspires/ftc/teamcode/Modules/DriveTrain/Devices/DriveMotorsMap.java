package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices;

import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class DriveMotorsMap {
    Robot robot;
    public Motor rightForwardDrive;
    public Motor rightBackDrive;
    public Motor leftForwardDrive;
    public Motor leftBackDrive;

    public void update() {
        rightBackDrive.update();
        rightForwardDrive.update();
        leftBackDrive.update();
        leftForwardDrive.update();

    }

    public void init(Robot robot) {
        this.robot = robot;
        rightBackDrive    = DriveTrainMotors.rightBackDrive;
        rightForwardDrive = DriveTrainMotors.rightForwardDrive;
        leftBackDrive     = DriveTrainMotors.leftBackDrive;
        leftForwardDrive  = DriveTrainMotors.leftForwardDrive;
    }
}
