package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.ExponentFilter;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;

/*
 Writing by EgorKhvostikov
*/
public class LocalPositionListener {
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
    private final Position realLocalPositions = new Position(0, 0, 0);

    private Motor rightOdometer;
    private Motor leftOdometer ;
    private Motor yOdometer    ;

    public Position getLocalPositions() {
        return odometersPositions;
    }

    public Position getRealLocalPositions() {
        if(Robot.isDebug){
            return DriveTrainSimulation.localPosition;
        }
        return realLocalPositions;
    }

    public Position deltaPos = new Position(0,0,0);

    ExponentFilter filter = new ExponentFilter();
    public static double k = 15.25;

    double s1Old = 0;
    double xHOld = 0;
    private void calcLocalPosition() {
        double x = (rightOdometer.getPosition() + leftOdometer.getPosition()) / 2.0;
        double hClean = ((-rightOdometer.getPosition() + leftOdometer.getPosition()) / 2.0) / RobotConstant.TIK_PER_ANGLE;
        double yFix = (k*Math.toRadians(hClean))/RobotConstant.SM_PER_ODOMETER_TIK;
        double y    = (yOdometer.getPosition());
        y+= yFix;

        hClean = Position.normalizeAngle(hClean);

        double d1 = hClean          - s1Old;
        double d2 = gyro.getAngle() - xHOld;
        d2 = Position.normalizeAngle(d2);
        filter.update(d1,d2);

        s1Old = hClean;
        xHOld = filter.getX();

        Robot.telemetry.addData("hGyro", gyro.getAngle());
        Robot.telemetry.addData("hClean",hClean         );
        Robot.telemetry.addData("filter",filter.getX()  );

        double h = filter.getX();
        h = Position.normalizeAngle(h);

        deltaPos = new Position(x, y, h);
        deltaPos.vectorMinus(odometersPositions);

        odometersPositions.x = x;
        odometersPositions.y = y;
        odometersPositions.h = h;

        realLocalPositions.copyFrom(odometersPositions);
        realLocalPositions.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

    public void update() {
        deviceUpdate();
        calcLocalPosition();
    }


}
