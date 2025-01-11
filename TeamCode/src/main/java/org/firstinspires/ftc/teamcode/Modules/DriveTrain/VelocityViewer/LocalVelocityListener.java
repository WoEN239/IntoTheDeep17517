package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;

/*
 Writing by EgorKhvostikov
*/

public class LocalVelocityListener {

    public void init() {
        rightOdometer= DriveTrainMotors.rightOdometer;
        leftOdometer = DriveTrainMotors.leftOdometer ;
        yOdometer    = DriveTrainMotors.yOdometer    ;
    }

    private final Position odometersVelocities = new Position(0, 0, 0);
    private final Position realVelocity = new Position(0, 0, 0);

    public Position getOdometersVelocities() {return odometersVelocities;}
    public Position getVelocityLocalReal(){return realVelocity;}

    private Motor    rightOdometer;
    private Motor    leftOdometer ;
    private Motor    yOdometer    ;
    public  Position deltaVel     ;

    private void deviceUpdate(){
        DriveTrainMotors.rightOdometer.update();
        DriveTrainMotors.leftOdometer .update();
        DriveTrainMotors.yOdometer    .update();
    }

    public static double k = 0;
    private void calcLocalVelocity() {
        double x = (rightOdometer.getVelocity() + leftOdometer.getVelocity()) / 2.0;
        double h    = ((-rightOdometer.getVelocity() + leftOdometer.getVelocity()) / 2.0) / RobotConstant.TIK_PER_ANGLE;
        double yFix = (k*Math.toRadians(h))/RobotConstant.SM_PER_ODOMETER_TIK;
        double y    = (yOdometer.getVelocity());
        y+= yFix;

        deltaVel = new Position(x, y, h);
        deltaVel.vectorMinus(odometersVelocities);

        odometersVelocities.x = x;
        odometersVelocities.y = y;
        odometersVelocities.h = h;

        realVelocity.copyFrom(odometersVelocities);
        realVelocity.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

    public void update()
    {
        deviceUpdate();
        calcLocalVelocity();
    }
}
