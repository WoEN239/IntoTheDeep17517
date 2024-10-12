package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */


public class PositionController implements IModule {
    Robot robot;
    private VelocityController controller;
    private Position position = new Position();
    private Position target = new Position();
    public static boolean isUpdate = false;
    @Override
    public void init(Robot robot){
        this.robot = robot;
        controller = robot.velocityController;
    }
    private void updatePosition(){
        position = robot.positionViewer.getLocalViewer().getPositionLocal();
    }
    public void move(Position target){
        this.target = target;
        isUpdate = true;
        update();
    }

    public static PidStatus pidStatusY = new PidStatus(0,0,0,0,0,0);
    Pid pidY = new Pid(pidStatusY);
    
    public static PidStatus pidStatusX = new PidStatus(0,0,0,0,0,0);
    Pid pidX = new Pid(pidStatusX);
    
    public static PidStatus pidStatusH = new PidStatus(0,0,0,0,0,0);
    Pid pidH = new Pid(pidStatusH);

    public void update(){
        Position pidResult = new Position();
        
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
        if(isUpdate) {
            VelocityController.isUpdate = true;
            controller.move(pidResult);
        }
    }
}
