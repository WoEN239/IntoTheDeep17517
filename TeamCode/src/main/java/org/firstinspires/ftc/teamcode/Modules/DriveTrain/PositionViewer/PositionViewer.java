package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class PositionViewer implements Listener {
    private Robot robot;
    private final PositionLocalViewer localViewer = new PositionLocalViewer();

    @Override
    public void init(Robot robot) {
        localViewer.init(robot);
        this.robot = robot;
    }

    private final Position positionGlobal = new Position(0, 0, 0);

    public Position getPositionGlobal() {
        return positionGlobal;
    }

    public PositionLocalViewer getLocalViewer() {
        return localViewer;
    }

    private void calcGlobalPosition() {
        positionGlobal.h = robot.imu.getAngle();
        localViewer.deltaPosition.rotateIt(positionGlobal.h);
        positionGlobal.plus(localViewer.deltaPosition);
    }

    @Override
    public void read() {
        localViewer.update();
        calcGlobalPosition();
    }
}
