package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class PositionLocalViewer {
    public void init(Robot robot){
        rightOdometer = robot.devicePool.driveTrainMotors.rightBackDrive;
        leftOdometer = robot.devicePool.driveTrainMotors.rightBackDrive;
        yOdometer = robot.devicePool.driveTrainMotors.rightBackDrive;
    }
    private final Position positionLocal = new Position(0,0,0);
    private Motor rightOdometer;
    private Motor leftOdometer;
    private Motor yOdometer;
    public Position getPositionLocal() {
        return positionLocal;
    }
    public Position deltaPosition;
    private void calcLocalPosition(){
        double x = yOdometer.dev.getCurrentPosition();
        double y = (rightOdometer.dev.getCurrentPosition() + leftOdometer.dev.getCurrentPosition()) /2.0;
        double h = (rightOdometer.dev.getCurrentPosition() - leftOdometer.dev.getCurrentPosition()) /2.0;
        deltaPosition = new Position(x,y,h);
        deltaPosition.minus(positionLocal);
        positionLocal.y = y;
        positionLocal.x = x;
        positionLocal.h = h;
    }


    public void update(){
        calcLocalPosition();
    }
}
