package org.firstinspires.ftc.teamcode.Modules.DriveTrain;


import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;

public class DriveTrain implements IModule {
    Robot robot;
    private Position velVector = new Position(0,0,0);
    private Position posVector = new Position(0,0,0);
    private Position velTarget = new Position(0,0,0);
    private Position posTarget = new Position(0,0,0);
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
    public DriveTrain(){}
    public void moveVel(Position pos){
        velTarget.x = pos.x;
        velTarget.y = pos.y;
        velTarget.h = pos.h;
    }
    @Override
    public void update(){
        //rightBackDrive   .setVel(velTarget.y+velTarget.x+velTarget.h);
        //rightForwardDrive.setVel(velTarget.y-velTarget.x+velTarget.h);
        leftBackDrive    .setVel(velTarget.y-velTarget.x-velTarget.h);
        //leftForwardDrive .setVel(velTarget.y+velTarget.x-velTarget.h);
    }

}
