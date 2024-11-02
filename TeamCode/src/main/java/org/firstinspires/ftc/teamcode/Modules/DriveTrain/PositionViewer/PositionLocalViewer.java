package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.TIK_PER_ANGLE;
import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.lightOfOdometer;
import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.odometerConstant;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Robot;

/*
 Writing by EgorKhvostikov
*/

public class PositionLocalViewer {
    Gyro gyro;
    public void init(Robot robot) {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer;
        yOdometer    = DriveTrainMotors.yOdometer;
        gyro = robot.imu;
    }
    private void deviceUpdate(){
        DriveTrainMotors.rightOdometer.update();
        DriveTrainMotors.leftOdometer .update();
        DriveTrainMotors.yOdometer    .update();
    }
    private final Position positionLocal = new Position(0, 0, 0);
    private Position positionRealLocal = new Position(0, 0, 0);

    private Motor rightOdometer;
    private Motor leftOdometer;
    private Motor yOdometer;

    public Position getPositionLocal() {
        return positionLocal;
    }
    public Position getPositionRealLocal() {
        return positionRealLocal;
    }
    public Position deltaPositionLocal;

    private double staticAngleError = 0;
    private double angleErrorSensitivity = 7;
    private double yErrPerAngle = 150.29;
    private void calcLocalPosition() {
        double x = (rightOdometer.getPosition() + leftOdometer.getPosition()) / 2.0;
        double hClean =((rightOdometer.getPosition() - leftOdometer.getPosition()) / 2.0)/TIK_PER_ANGLE;
        double yFix = hClean*yErrPerAngle;

        hClean = Position.normalizeAngle(hClean);
        double hGyro = gyro.getAngle();
        if (gyro.isNewValue()){
            if(abs(hGyro-hClean)> angleErrorSensitivity){
                staticAngleError = hClean-hGyro;
            }
        }

        double h = hClean - staticAngleError;
        h = Position.normalizeAngle(h);

        double y = yOdometer.getPosition() + yFix;

        deltaPositionLocal = new Position(x, y, h);
        deltaPositionLocal.minus(positionLocal);

        positionLocal.y = y;
        positionLocal.x = x;
        positionLocal.h = h;

        positionRealLocal = positionLocal;
        positionRealLocal.linearMultiply(lightOfOdometer/odometerConstant);
    }

    public void update() {
        deviceUpdate();
        calcLocalPosition();
    }


}
