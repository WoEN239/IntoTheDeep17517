package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

@Config
public class PositionController implements Controller {
    Robot robot;
    private VelocityController controller;
    private Position position = new Position();
    private Position target = new Position();
    public static boolean isUpdate = false;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        controller = robot.velocityController;
    }

    private void updatePosition() {
        position = robot.positionViewer.getPositionRealGlobal();
    }

    public void move(Position t) {
        Position target = new Position().copyFrom(t);
        target.vectorMinus(robot.positionViewer.getPositionRealGlobal());
        target.vectorPlus (robot.positionViewer.getLocalViewer().getRealLocalPositions());

        this.target = target;
        isUpdate = true;
    }

    public static PidStatus pidStatusY = new PidStatus(3.5, 7.50, 0.25, 0,0,0,0, 10, 0);
    Pid pidY = new Pid(pidStatusY);

    public static PidStatus pidStatusX = new PidStatus(3.5, 7.50, 0.25, 0,0,0,0, 10, 0);
    Pid pidX = new Pid(pidStatusX);

    public static PidStatus pidStatusH = new PidStatus(4, 15, 0.2, 0,0,0,0, 15, 0);
    Pid pidH = new Pid(pidStatusH);

    public void update() {
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
        if (isUpdate) {
            VelocityController.isUpdate = true;
            controller.moveGlobal(pidResult);
        }
    }
    public boolean isAtTarget(){
        Position err = new Position().copyFrom(target);
        err.positionMinus(position);
        return abs(err.x)<3 && abs(err.y) < 5 && abs(err.h) < 5;
    }
}
