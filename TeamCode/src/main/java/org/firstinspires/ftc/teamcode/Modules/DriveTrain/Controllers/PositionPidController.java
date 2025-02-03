package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Math.Position;

/**
 * Writing by EgorKhvostikov
 */
@Config
public class PositionPidController{

    private final Position globalTarget = new Position();
    public Position getGlobalTarget() {return globalTarget;}
    public void setTarget(Position globalTarget) {this.globalTarget.copyFrom(globalTarget);}

    private final Position globalPosition = new Position();
    public void setGlobalPosition(Position p) {this.globalPosition.copyFrom(p);}

    private final Position localPosition = new Position();
    public void setLocalPosition(Position p) {
        this.localPosition.copyFrom(p);
    }

    private final Position pidResult = new Position();
    public Position getPidResult() {return pidResult;}


    public static PidStatus pidStatusY = new PidStatus(2.35, 5, 0, 0, 0, 0, 0, 3, 0);
    Pid pidY = new Pid(pidStatusY);

    public static PidStatus pidStatusX = new PidStatus(2, 5, 0.2, 0, 0, 0, 0, 5, 0);
    Pid pidX = new Pid(pidStatusX);

    public static PidStatus pidStatusH = new PidStatus(3.5, 5, 0.001, 0, 0, 0, 0, 50, 0);
    Pid pidH = new Pid(pidStatusH);
    {
        pidH.isAngle = true;
    }

    private final Position targetForPid = new Position();
    private void computePidTarget(){
        Position localTarget = new Position().copyFrom(globalTarget);
        localTarget.vectorMinus(globalPosition);

        localTarget.rotateVector(-globalPosition.h);

        localTarget.vectorPlus(localPosition);
        this.targetForPid.copyFrom(localTarget);
    }

    public void computePidResult() {
        computePidTarget();
        pidH.isAngle = true;

        Position pidResult = new Position();

        pidX.setPos(localPosition.x);
        pidX.setTarget(targetForPid.x);
        pidX.update();
        pidResult.x = pidX.getU();

        pidY.setPos(localPosition.y);
        pidY.setTarget(targetForPid.y);
        pidY.update();
        pidResult.y = pidY.getU();

        pidH.setPos (localPosition.h);
        pidH.setTarget(targetForPid.h);
        pidH.update();
        pidResult.h = pidH.getU();

        this.pidResult.copyFrom(pidResult);
    }


}

