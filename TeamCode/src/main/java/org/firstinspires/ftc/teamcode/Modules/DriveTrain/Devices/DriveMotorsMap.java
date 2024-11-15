package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices;

import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.yMultiplier;

import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant;
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
    public void move(Position t){
        Position target = new Position().copyFrom(t);
        target.linearMultiply(RobotConstant.ENC_TIK_PER_SM);
        target.angleMultiply(RobotConstant.TIK_PER_ANGLE);
        rightBackDrive.setVel   (target.x + target.y*yMultiplier - target.h);
        rightForwardDrive.setVel(target.x - target.y*yMultiplier - target.h);
        leftBackDrive.setVel    (target.x - target.y*yMultiplier + target.h);
        leftForwardDrive.setVel (target.x + target.y*yMultiplier + target.h);
    }
}
