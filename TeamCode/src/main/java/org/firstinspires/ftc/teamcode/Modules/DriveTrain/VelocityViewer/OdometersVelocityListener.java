package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;

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

    private void calcLocalVelocity() {
        double x = (rightOdometer.getVelocity() + leftOdometer.getVelocity()) / 2.0;
        double h = (rightOdometer.getVelocity() - leftOdometer.getVelocity()) / 2.0;
        double y = (yOdometer.getVelocity());

        deltaVel = new Position(x, y, h);
        deltaVel.positionMinus(odometersVelocities);

        odometersVelocities.x = x;
        odometersVelocities.y = y;
        odometersVelocities.h = h;
    }

    public void update() {
        calcLocalVelocity();
    }
}
