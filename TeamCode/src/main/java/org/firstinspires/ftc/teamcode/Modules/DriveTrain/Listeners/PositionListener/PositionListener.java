package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener;

import org.firstinspires.ftc.teamcode.Math.Position;
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
    }

    private final Position deltaPos = new Position();
    public void setDeltaPos(Position p){deltaPos.copyFrom(p);}

    public void computePosition() {
        Position dp = new Position();
        dp.copyFrom(deltaPos);
        dp.rotateVector(deltaPos.h);

        positionTik.vectorPlus(dp);
        positionTik.h = deltaPos.h;
        
        position.copyFrom(positionTik);
        position.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

}
