package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.PositionPool;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;

/*
 Writing by EgorKhvostikov
*/

public class PositionListener{
    private final Position positionTik = new Position();
    public Position getPositionTik() {return positionTik;}

    private final Position position = new Position();

    public Position getPosition(){
        if(Robot.isDebug){
            return DriveTrainSimulation.position;
        }
        return position;
    }

    public void init() {
        positionTik.copyFrom(Robot.myTeam.startPos).
                linearMultiply(1 / RobotConstant.SM_PER_ODOMETER_TIK);
        position   .copyFrom(Robot.myTeam.startPos);
        oldH = Robot.myTeam.startPos.h;

    }

    private final Position deltaPos = new Position();
    public void setDeltaPos(Position p){deltaPos.copyFrom(p);}

    private double oldH = 0;
    public void computePosition() {
        Position dp = new Position();
        dp.copyFrom(deltaPos);

        double dH = Math.toRadians( dp.h - oldH);

        Position dpCorrected = new Position().copyFrom(dp);

        dpCorrected.x = dp.x * Math.sin(dH) / dH + dp.y * (cos(dH)-1)/dH;

        dpCorrected.y = dp.x*(1-cos(dH))/dH + dp.y*sin(dH)/dH;

        if(abs(dH)<0.0017){
            dpCorrected.copyFrom(dp);
        }

        dpCorrected.rotateVector(oldH);

        oldH = dp.h;

        positionTik.vectorPlus(dpCorrected);
        positionTik.h = (dpCorrected.h);
        
        position.copyFrom(positionTik);
        position.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

}
