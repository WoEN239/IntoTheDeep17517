package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant;

/*
 Writing by EgorKhvostikov
*/

public class OdometersVelocityListener {

    public void init() {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer ;
        yOdometer    = DriveTrainMotors.yOdometer    ;
    }

    private final Position odometersVelocities = new Position(0, 0, 0);

    public Position getOdometersVelocities() {return odometersVelocities;}

    private Motor    rightOdometer;
    private Motor    leftOdometer ;
    private Motor    yOdometer    ;
    public  Position deltaVel     ;

    private void deviceUpdate(){
        DriveTrainMotors.rightOdometer.update();
        DriveTrainMotors.leftOdometer .update();
        DriveTrainMotors.yOdometer    .update();
    }

    private void calcLocalVelocity() {
        double k = 122;
        double x = (rightOdometer.getVelocity() + leftOdometer.getVelocity()) / 2.0;
        double h = (rightOdometer.getVelocity() - leftOdometer.getVelocity()) / 2.0 / RobotConstant.TIK_PER_ANGLE;
        double y = (yOdometer.getVelocity())    - h*k;

        deltaVel = new Position(x, y, h);
        deltaVel.vectorMinus(odometersVelocities);

        odometersVelocities.x = x;
        odometersVelocities.y = y;
        odometersVelocities.h = h;
    }

    public void update()
    {
        deviceUpdate();
        calcLocalVelocity();
    }
}
