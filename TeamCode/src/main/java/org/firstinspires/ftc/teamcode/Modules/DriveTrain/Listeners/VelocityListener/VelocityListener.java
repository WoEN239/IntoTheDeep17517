package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.VelocityListener;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;

/*
 Writing by EgorKhvostikov
*/

public class VelocityListener {

    private final Position velocityTik = new Position();
    public Position getVelocityTik() {return velocityTik;}

    private final Position velocity = new Position();
    public Position getVelocity(){return velocity;}

    private final Position localVelocity = new Position();
    public void setLocalVelocity(Position p){localVelocity.copyFrom(p);}

    private double h = 0;
    public void setH(double h){this.h = h;}

    public void computeVelocity() {
        velocityTik.copyFrom(localVelocity);
        velocityTik.rotateVector(h);

        velocity.copyFrom(velocityTik);
        velocity.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

}
