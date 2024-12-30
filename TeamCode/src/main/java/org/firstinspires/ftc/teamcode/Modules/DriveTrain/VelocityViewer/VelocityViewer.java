package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
 Writing by EgorKhvostikov
*/

public class VelocityViewer implements Listener {
    public void init(Robot robot) {
        this.robot = robot;
        odometers.init();
    }

    private Robot robot;
    private final LocalVelocityListener odometers = new LocalVelocityListener();

    private final Position velocityGlobal = new Position(0, 0, 0);
    private final Position velocityRealGlobal = new Position(0, 0, 0);

    public Position getVelocityGlobal() {
        return velocityGlobal;
    }
    public Position getVelocityRealGlobal(){return velocityRealGlobal;}

    public LocalVelocityListener getLocalVelocityListener() {
        return odometers;
    }

    private void calcGlobalVelocity() {
        velocityGlobal    .copyFrom(odometers.getOdometersVelocities());
        velocityGlobal    .rotateVector(robot.positionListener.getPositionGlobal().h);

        velocityRealGlobal.copyFrom(velocityGlobal);
        velocityRealGlobal.linearMultiply(RobotConstant.SM_PER_ODOMETER_TIK);
    }

    @Override
    public void read() {
        odometers.update();
        calcGlobalVelocity();
    }
}
