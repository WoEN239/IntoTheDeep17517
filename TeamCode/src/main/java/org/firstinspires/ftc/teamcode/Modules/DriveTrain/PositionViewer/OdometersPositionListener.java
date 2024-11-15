package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
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

    private void calcLocalPosition() {
        double x = (rightOdometer.getPosition() + leftOdometer.getPosition()) / 2.0;
        double h = (rightOdometer.getPosition() - leftOdometer.getPosition()) / 2.0;
        double y = (yOdometer.getPosition());


        deltaPos = new Position(x, y, h);
        deltaPos.positionMinus(odometersPositions);

        odometersPositions.x = x;
        odometersPositions.y = y;
        odometersPositions.h = h;
    }

    public void update() {
        deviceUpdate();
        calcLocalPosition();
    }


}
