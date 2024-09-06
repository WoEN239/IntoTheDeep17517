package org.firstinspires.ftc.teamcode.Modules;


import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Events.DriveTrainStatus;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot;

public class DriveTrain implements IModule {
    Robot robot;
    public DriveTrainStatus status = new DriveTrainStatus();

    Motor rightForwardDrive;
    Motor rightBackDrive;
    Motor leftForwardDrive;
    Motor leftBackDrive;

    @Override
    public void init(Robot robot){
        this.robot = robot;
        rightBackDrive    = robot.devicePool.rightBackDrive;
        rightForwardDrive = robot.devicePool.rightForwardDrive;
        leftBackDrive     = robot.devicePool.leftBackDrive;
        leftForwardDrive  = robot.devicePool.leftForwardDrive;
    }
    public DriveTrain(){
    }
    public void moveVel(Position pos){
        status.xVelTar = pos.x;
        status.yVelTar = pos.y;
        status.hVelTar = pos.h;

    }
    @Override
    public void update(){
        rightBackDrive   .setVel(status.yVelTar+status.xVelTar+status.hVelTar);
        rightForwardDrive.setVel(status.yVelTar-status.xVelTar+status.hVelTar);
        leftBackDrive    .setVel(status.yVelTar-status.xVelTar-status.hVelTar);
        leftForwardDrive .setVel(status.yVelTar+status.xVelTar-status.hVelTar);
    }

}
