package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
@Config
public class VelocityPidController{

    private final Position target = new Position();
    public void setTarget(Position p){this.target.copyFrom(p);}

    private final Position velocity = new Position();
    public void setVelocity(Position p){this.velocity.copyFrom(p);}

    private final Position pidResult = new Position();
    public Position getPidResult(){return pidResult;}


    public static PidStatus pidStatusY = new PidStatus(0.25, 2, 0, 0,1.25,0,0, 4, 0);
    Pid pidY = new Pid(pidStatusY);

    public static PidStatus pidStatusX = new PidStatus(0.15, 3, 0, 0,1.25,0,0, 4, 0);
    Pid pidX = new Pid(pidStatusX);

    public static PidStatus pidStatusH = new PidStatus(0.03, 0.025, 0.0025, 0,0.05,0,0, 1, 0);
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