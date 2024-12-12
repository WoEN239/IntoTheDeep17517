package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
 Writing by EgorKhvostikov
*/

public class PositionViewer implements Listener {
    private final LocalPositionListener localViewer = new LocalPositionListener();
    @Override
    public void init(Robot robot) {
        localViewer.init(robot);
        this.robot = robot;
        this.gyro = robot.imu;
    }
    Gyro gyro;
    Robot robot;
    private final Position positionGlobal = new Position(0, 0, 0);
    private final Position positionRealGlobal = new Position(0, 0, 0);

    public Position getPositionRealGlobal(){return positionRealGlobal;}
    public Position getPositionGlobal() {
        return positionGlobal;
    }

    public LocalPositionListener getLocalViewer() {
        return localViewer;
    }

    private void calcGlobalPosition() {
        Position dp = new Position();
        dp.copyFrom(localViewer.deltaPos);
        dp.rotateVector(localViewer.getLocalPositions().h);

        positionGlobal.vectorPlus(dp);
        positionGlobal.h = localViewer.getLocalPositions().h;
        
        positionRealGlobal.copyFrom(positionGlobal);
        positionRealGlobal.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

    @Override
    public void read() {
        localViewer.update();
        calcGlobalPosition();
    }

}
