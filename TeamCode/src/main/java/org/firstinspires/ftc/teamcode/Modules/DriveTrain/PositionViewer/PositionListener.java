package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionsPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Robot.Team;

/*
 Writing by EgorKhvostikov
*/

public class PositionListener implements Listener {
    private final LocalPositionListener localViewer = new LocalPositionListener();

    @Override
    public void init(Robot robot) {
        localViewer.init(robot);
        this.robot = robot;
        this.gyro = robot.imu;
        if (Robot.myTeam == Team.RED) {
            positionTikGlobal = new Position().copyFrom(PositionsPool.redStart).
                    linearMultiply(1 / RobotConstant.SM_PER_ODOMETER_TIK);
            positionGlobal = new Position().copyFrom(PositionsPool.redStart);
        }
        else {
            positionTikGlobal = new Position().copyFrom(PositionsPool.blueStart).
                    linearMultiply(1 / RobotConstant.SM_PER_ODOMETER_TIK);
            positionGlobal = new Position().copyFrom(PositionsPool.blueStart);
        }
    }
    Gyro gyro;
    Robot robot;
    private Position positionTikGlobal;
    private Position positionGlobal;

    public Position getPositionGlobal(){
        if(Robot.isDebug){
            return DriveTrainSimulation.position;
        }
        return positionGlobal;
    }
    public Position getPositionTikGlobal() {return positionTikGlobal;}
    public LocalPositionListener getLocalViewer() {return localViewer;}

    private void calcGlobalPosition() {
        Position dp = new Position();
        dp.copyFrom(localViewer.deltaPos);
        dp.rotateVector(localViewer.getLocalPositions().h);

        positionTikGlobal.vectorPlus(dp);
        positionTikGlobal.h = localViewer.getLocalPositions().h;
        
        positionGlobal.copyFrom(positionTikGlobal);
        positionGlobal.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

    @Override
    public void read() {
        localViewer.update();
        calcGlobalPosition();
    }

}
