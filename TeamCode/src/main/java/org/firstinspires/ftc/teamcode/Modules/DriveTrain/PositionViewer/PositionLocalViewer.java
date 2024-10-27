package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.ANGLE_PER_TIK;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class PositionLocalViewer {
    Gyro gyro;
    public void init(Robot robot) {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer;
        yOdometer    = DriveTrainMotors.yOdometer;;
        gyro = robot.imu;
    }

    private final Position positionLocal = new Position(0, 0, 0);
    private Motor rightOdometer;
    private Motor leftOdometer;
    private Motor yOdometer;

    public Position getPositionLocal() {
        return positionLocal;
    }

    public Position deltaPositionLocal;

    private double staticAngleError = 0;
    private double angleErrorSensitivity = 10;
    private double yErrPerAngle = 1;//TODO
    private void calcLocalPosition() {


        double x = (rightOdometer.getPosition() + leftOdometer.getPosition()) / 2.0;

        double hClean = ANGLE_PER_TIK * ((rightOdometer.getPosition() - leftOdometer.getPosition()) / 2.0);
        double hGyro = gyro.getAngle();
        if (gyro.isNewValue()){
            if(abs(hGyro-hClean)> angleErrorSensitivity){
                staticAngleError = hClean-hGyro;
            }
        }
        double h = hClean - staticAngleError;

        double yFix = h*yErrPerAngle;
        double y = yOdometer.getPosition() - yFix;

        deltaPositionLocal = new Position(x, y, h);
        deltaPositionLocal.minus(positionLocal);

        positionLocal.y = y;
        positionLocal.x = x;
        positionLocal.h = h;
    }

    public void update() {
        calcLocalPosition();
    }

}
