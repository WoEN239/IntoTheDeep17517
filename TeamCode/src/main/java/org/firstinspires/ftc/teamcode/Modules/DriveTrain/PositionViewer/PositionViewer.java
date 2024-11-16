package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import static java.lang.Math.abs;
import static java.lang.Math.tan;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

/*
 Writing by EgorKhvostikov
*/

public class PositionViewer implements Listener {
    private final OdometersPositionListener odometers = new OdometersPositionListener();
    @Override
    public void init(Robot robot) {
        odometers.init(robot);
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

    public OdometersPositionListener getOdometers() {
        return odometers;
    }

    private void calcGlobalPosition() {

        Position dp = new Position();
        dp.copyFrom(odometers.deltaPos);
        dp.rotateVector(robot.positionViewer.getPositionRealGlobal().h);
        positionGlobal.vectorPlus(dp);

        positionRealGlobal.copyFrom(positionGlobal);
        positionRealGlobal.linearMultiply(RobotConstant.ENC_TIK_PER_SM);
    }

    @Override
    public void read() {
        odometers.update();
        calcGlobalPosition();
    }

}
