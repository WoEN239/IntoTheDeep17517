package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.MAX_MOTOR_TICKS_VEL;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.yMultiplier;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
  Writing by EgorKhvostikov
*/

public class MotorsVoltageController {
    private Motor rightForwardDrive;
    private Motor rightBackDrive;
    private Motor leftForwardDrive;
    private Motor leftBackDrive;

    public void updateData() {
        rightBackDrive.update();
        rightForwardDrive.update();
        leftBackDrive.update();
        leftForwardDrive.update();

    }

    public void init(Robot robot) {
        rightBackDrive    = DriveTrainMotors.rightBackDrive;
        rightForwardDrive = DriveTrainMotors.rightForwardDrive;
        leftBackDrive     = DriveTrainMotors.leftBackDrive;
        leftForwardDrive  = DriveTrainMotors.leftForwardDrive;
    }

    public void setVoltage(Position t){
        Position target = new Position().copyFrom(t);

        target.linearMultiply(RobotConstant.ENC_TIK_PER_SM);
        target.angleMultiply(RobotConstant.TIK_PER_ANGLE);

        double rightBackVel    = target.x + target.y*yMultiplier - target.h;
        double rightForwardVel = target.x - target.y*yMultiplier - target.h;
        double leftBackVel     = target.x - target.y*yMultiplier + target.h;
        double leftForwardVel  = target.x + target.y*yMultiplier + target.h;

        double maxTargetVel = max(
                max(abs(rightBackVel), abs(rightForwardVel)),
                max(abs(leftBackVel) , abs(leftForwardVel)));


        if (maxTargetVel > MAX_MOTOR_TICKS_VEL) {
            double k = MAX_MOTOR_TICKS_VEL / maxTargetVel;
            rightBackVel *= k;
            rightForwardVel *= k;
            leftBackVel *= k;
            leftForwardVel *= k;
        }

        rightBackDrive   .setVel(rightBackVel);
        rightForwardDrive.setVel(rightForwardVel);
        leftBackDrive    .setVel(leftBackVel);
        leftForwardDrive .setVel(leftForwardVel);
    }
}
