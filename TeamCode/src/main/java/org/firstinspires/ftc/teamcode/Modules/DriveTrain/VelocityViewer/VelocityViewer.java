package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

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
        velocityGlobal    .rotateVector(robot.positionViewer.getPositionRealGlobal().h);

        velocityRealGlobal.copyFrom(velocityGlobal);
        velocityRealGlobal.linearMultiply(RobotConstant.ENC_TIK_PER_SM);
    }

    @Override
    public void read() {
        odometers.update();
        calcGlobalVelocity();
    }
}
