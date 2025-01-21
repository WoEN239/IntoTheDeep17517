package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;

/**
 * Writing by @MrFrosty1234
 */

public class LiftController { ;

    private double errSync;
    private double pos;
    private double targetPos;

    private double uMove;
    private double uSync;

    public void setErrSync(double err){
        errSync = err;
    }
    public void setTargetPos(double target){targetPos = target;}
    public void setPos(double posLift){
        pos = posLift;
    }

    public double getUMove(){return uMove;}
    public double getUSync(){
        return uSync;
    }

    public static PidStatus pidStatus = new PidStatus(0.06, 0.001, 0.00001, 0, 0, 0, 0, 2, 0.5);
    Pid pid = new Pid(pidStatus);

    public static PidStatus pidStatusSync = new PidStatus(0.00001, 0, 0, 0, 0, 0, 0, 0, 0);
    Pid pidSync = new Pid(pidStatusSync);

    public void computeVoltage(){
        pidSync.setTarget(0);
        pidSync.setPos(-errSync);
        pidSync.update();
        uSync = pidSync.getU();


        pid.setTarget(targetPos);
        pid.setPos(pos);
        pid.update();
        uMove = pid.getU();
    }
}
