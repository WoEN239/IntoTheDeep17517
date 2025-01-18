package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener;

import org.firstinspires.ftc.teamcode.Math.ExponentFilter;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.DeviceValueMap;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;

/*
 Writing by EgorKhvostikov
*/
public class LocalPositionListener {
    private final Position tikPosition = new Position(0, 0, 0);

    private final Position localPositions = new Position(0, 0, 0);
    public void reset(){
        localPositions.copyFrom(new Position());
        tikPosition.copyFrom(new Position());
    }

    public Position getLocalPositions() {
        if(Robot.isDebug){
            return DriveTrainSimulation.localPosition;
        }
        return localPositions;
    }


    private final DeviceValueMap deviceValue = new DeviceValueMap();
    public void setDeviceValue(DeviceValueMap m ){ deviceValue.copyFrom(m);}

    private final Position deltaPos = new Position();
    public Position getDeltaPos() {return deltaPos;}

    ExponentFilter filter = new ExponentFilter();
    public static double k = 15.25;

    double s1Old = 0;
    double xHOld = 0;
    public void computePosition() {
        double x = (deviceValue.rightOdometer + deviceValue.leftOdometer) / 2.0;

        double hClean = ((deviceValue.rightOdometer - deviceValue.leftOdometer) / 2.0) / RobotConstant.TIK_PER_ANGLE;

        double yFix = (k*Math.toRadians(hClean))/RobotConstant.SM_PER_ODOMETER_TIK;
        double y    = (deviceValue.yOdometer);
        y+= yFix;


        hClean = Position.normalizeAngle(hClean);

        double d1 = hClean          - s1Old;
        double d2 = deviceValue.gyro - xHOld;
        d2 = Position.normalizeAngle(d2);
        filter.update(d1,d2);

        s1Old = hClean;
        xHOld = filter.getX();

        double h = filter.getX();
        h = Position.normalizeAngle(h);

        Robot.telemetryPacket.put("clean h", hClean);
        Robot.telemetryPacket.put("filter", h);


        deltaPos.copyFrom(new Position(x, y, h));
        deltaPos.vectorMinus(tikPosition);

        tikPosition.x = x;
        tikPosition.y = y;
        tikPosition.h = h;

        localPositions.copyFrom(tikPosition);
        localPositions.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

}
