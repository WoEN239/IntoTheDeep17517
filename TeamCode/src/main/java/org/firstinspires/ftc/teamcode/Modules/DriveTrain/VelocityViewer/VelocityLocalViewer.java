package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.TIK_PER_ANGLE;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class VelocityLocalViewer {
    public void init(Robot robot) {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer;
        yOdometer    = DriveTrainMotors.yOdometer;;
    }

    private final Position velocityLocal = new Position(0, 0, 0);

    public Position getVelocityLocal() {
        return velocityLocal;
    }

    private Motor rightOdometer;
    private Motor leftOdometer;
    private Motor yOdometer;
    public Position deltaVel;

    private void calcLocalVelocity() {
        double x = (rightOdometer.getVelocity() + leftOdometer.getVelocity()) / 2.0;
        double y = yOdometer.getVelocity();
        double h = TIK_PER_ANGLE *((rightOdometer.getVelocity() + leftOdometer.getVelocity()) / 2.0);
        deltaVel = new Position(x, y, h);
        deltaVel.minus(velocityLocal);
        velocityLocal.x = x;
        velocityLocal.y = y;
        velocityLocal.h = h;
    }

    public void update() {
        calcLocalVelocity();
    }
}
