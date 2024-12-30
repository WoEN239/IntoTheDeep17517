package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;


import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.DriveMotorsMap;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;

/**
 * Writing by EgorKhvostikov
 */
public class VelocityController implements Controller {
    Robot robot;

    private final Position target = new Position(0, 0, 0);
    private Position position = new Position(0, 0, 0);

    DriveMotorsMap motorsMap = new DriveMotorsMap();
    public static boolean isUpdate = false;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        motorsMap.init(robot);

    }

    public void moveLocal(Position target){
        this.target.x = target.x;
        this.target.y = target.y;
        this.target.h = target.h;

        isUpdate = true;
    }

    public void moveGlobal(Position t) {
        Position target = new Position().copyFrom(t);
        this.target.x = target.x;
        this.target.y = target.y;
        this.target.h = target.h;

        isUpdate = true;
    }

    private void updatePosition() {
        position = robot.velocityViewer.getLocalVelocityListener().getVelocityLocalReal();
    }

    public static PidStatus pidStatusY = new PidStatus(0, 0, 0, 0,0,0,0, 0, 0);
    Pid pidY = new Pid(pidStatusY);

    public static PidStatus pidStatusX = new PidStatus(0.4, 0.5, 0.0025, 0,0.65,0,0, 3, 0);
    Pid pidX = new Pid(pidStatusX);

    public static PidStatus pidStatusH = new PidStatus(0.002, 0.01, 0, 0,0.065,0,0, 5, 0);
    Pid pidH = new Pid(pidStatusH);

    @Override
    public void update() {
        motorsMap.update();
        Position pidResult = new Position();
        updatePosition();

        pidX.setPos(position.x);
        pidX.setTarget(target.x);
        pidX.update();
        pidResult.x = pidX.getU();

        pidY.setPos(position.y);
        pidY.setTarget(target.y);
        pidY.update();
        pidResult.y = pidY.getU();

        pidH.setPos(position.h);
        pidH.setTarget(target.h);
        pidH.update();
        pidResult.h = pidH.getU();

        if(isUpdate){
            motorsMap.move(pidResult);
        }
    }
}