package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;


import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.DriveMotorsMap;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;

public class VelocityController implements IModule {
    Robot robot;
    private Position target = new Position(0,0,0);
    DriveMotorsMap motorsMap;
    @Override
    public void init(Robot robot){
        this.robot = robot;
        motorsMap.init(robot);
    }
    public void move(Position target){
        this.target.x = target.x;
        this.target.y = target.y;
        this.target.h = target.h;
    }
    @Override
    public void update(){
        motorsMap.update();
        motorsMap.rightBackDrive   .setVel(target.y+ target.x+ target.h);
        motorsMap.rightForwardDrive.setVel(target.y- target.x+ target.h);
        motorsMap.leftBackDrive    .setVel(target.y- target.x- target.h);
        motorsMap.leftForwardDrive .setVel(target.y+ target.x- target.h);
    }

}