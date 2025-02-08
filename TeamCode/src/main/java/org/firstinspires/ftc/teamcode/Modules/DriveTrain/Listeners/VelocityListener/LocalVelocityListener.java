package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.VelocityListener;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.DeviceValueMap;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;

/*
 Writing by EgorKhvostikov
*/

public class LocalVelocityListener {

    private final Position velocityTick = new Position();
    public Position getVelocityTick(){return velocityTick;}

    private final Position velocity = new Position();
    public Position getVelocity(){return velocity;}

    private final DeviceValueMap deviceValue = new DeviceValueMap();
    public void setDeviceValue(DeviceValueMap m){deviceValue.copyFrom(m);}

    public static double k = 0;

    public void computeVelocity() {
        double x = ( deviceValue.rightOdometer + deviceValue.leftOdometer) / 2.0;
        double h    = ((deviceValue.rightOdometer - deviceValue.leftOdometer) / 2.0) / RobotConstant.TIK_PER_ANGLE;
        double yFix = (k*Math.toRadians(h))/RobotConstant.SM_PER_ODOMETER_TIK;
        double y    = deviceValue.yOdometer;
        y+= yFix;

        velocityTick.x = x;
        velocityTick.y = y;
        velocityTick.h = -h;

        velocity.copyFrom(velocityTick);
        velocity.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

}
