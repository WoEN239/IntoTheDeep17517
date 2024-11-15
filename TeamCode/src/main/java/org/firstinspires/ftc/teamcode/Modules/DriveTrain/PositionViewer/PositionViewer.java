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

    private Position fix = new Position(0,0,0);
    private void calcGlobalPosition() {
        double angleErrPerSec        = 0;
        double angleErrorSensitivity = 0;

        Position dp = new Position();
        dp.copyFrom(odometers.deltaPos);
        dp.rotateVector(robot.positionViewer.getPositionRealGlobal().h);
        positionGlobal.positionPlus(dp);

        fix.x = 0;
        fix.y = dp.h*angleErrPerSec;
        if (gyro.isNewValue()){
            if(abs(positionGlobal.h - gyro.getAngle()*RobotConstant.TIK_PER_ANGLE)> angleErrorSensitivity){
                fix.h = positionGlobal.h-gyro.getAngle()*RobotConstant.TIK_PER_ANGLE;
            }
        }
        positionGlobal.positionMinus(fix);

        positionRealGlobal.copyFrom(positionGlobal);
        positionRealGlobal.linearMultiply(RobotConstant.ENC_TIK_PER_SM);
        positionRealGlobal.angleMultiply(1.0/RobotConstant.TIK_PER_ANGLE);
    }

    @Override
    public void read() {
        odometers.update();
        calcGlobalPosition();
    }

}
