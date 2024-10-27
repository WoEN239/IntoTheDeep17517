package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class VelocityViewer implements Listener {
    private Robot robot;
    private final VelocityLocalViewer localViewer = new VelocityLocalViewer();

    public void init(Robot robot) {
        this.robot = robot;
        localViewer.init(robot);
    }

    private final Position velocityGlobal = new Position(0, 0, 0);

    public Position getVelocityGlobal() {
        return velocityGlobal;
    }

    public VelocityLocalViewer getLocalViewer() {
        return localViewer;
    }

    private void calcGlobalVelocity() {
        velocityGlobal.copyFrom(localViewer.getVelocityLocal());
        velocityGlobal.rotateIt(robot.positionViewer.getPositionGlobal().h);
    }

    @Override
    public void read() {
        localViewer.update();
        calcGlobalVelocity();
    }
}
