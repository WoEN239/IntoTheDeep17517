package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant;
import org.firstinspires.ftc.teamcode.Robot;

/*
 Writing by EgorKhvostikov
*/

public class OdometersPositionListener {
    Gyro gyro;
    public void init(Robot robot) {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer ;
        yOdometer    = DriveTrainMotors.yOdometer    ;
        gyro = robot.imu;
    }
    private void deviceUpdate(){
        DriveTrainMotors.rightOdometer.update();
        DriveTrainMotors.leftOdometer .update();
        DriveTrainMotors.yOdometer    .update();
    }
    private final Position odometersPositions = new Position(0, 0, 0);

    private Motor rightOdometer;
    private Motor leftOdometer ;
    private Motor yOdometer    ;

    public Position getOdometersPositions() {
        return odometersPositions;
    }
    public Position deltaPos = new Position(0,0,0);

    double angleFix = 0;
    private void calcLocalPosition() {
        double k = 122;
        double x = (rightOdometer.getPosition() + leftOdometer.getPosition()) / 2.0;
        double hClean = (rightOdometer.getPosition() - leftOdometer.getPosition()) / 2.0 / RobotConstant.TIK_PER_ANGLE;
        double y = (yOdometer.getPosition()) - hClean*k;

        hClean = Position.normalizeAngle(hClean);
        if (gyro.isNewValue()){
            if(abs(gyro.getAngle()-hClean)> 2){
                angleFix = hClean-gyro.getAngle();
            }
        }

        double h = hClean - angleFix;
        h = Position.normalizeAngle(h);

        deltaPos = new Position(x, y, h);
        deltaPos.vectorMinus(odometersPositions);

        odometersPositions.x = x;
        odometersPositions.y = y;
        odometersPositions.h = h;
    }

    public void update() {
        deviceUpdate();
        calcLocalPosition();
    }


}
