package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
public class VelocityPidController{

    private final Position target = new Position();
    public void setTarget(Position p){this.target.copyFrom(p);}

    private final Position velocity = new Position();
    public void setVelocity(Position p){this.velocity.copyFrom(p);}

    private final Position pidResult = new Position();
    public Position getPidResult(){return pidResult;}


    public static PidStatus pidStatusY = new PidStatus(0, 0, 0, 0,0,0,0, 0, 0);
    Pid pidY = new Pid(pidStatusY);

    public static PidStatus pidStatusX = new PidStatus(0.4, 0.5, 0.0025, 0,0.65,0,0, 3, 0);
    Pid pidX = new Pid(pidStatusX);

    public static PidStatus pidStatusH = new PidStatus(0.002, 0.01, 0, 0,0.065,0,0, 5, 0);
    Pid pidH = new Pid(pidStatusH);

    public void computePidResult() {
        Position pidResult = new Position();

        pidX.setPos(velocity.x);
        pidX.setTarget(target.x);
        pidX.update();
        pidResult.x = pidX.getU();

        pidY.setPos(velocity.y);
        pidY.setTarget(target.y);
        pidY.update();
        pidResult.y = pidY.getU();

        pidH.setPos(velocity.h);
        pidH.setTarget(target.h);
        pidH.update();
        pidResult.h = pidH.getU();

        this.pidResult.copyFrom(pidResult);
    }
}