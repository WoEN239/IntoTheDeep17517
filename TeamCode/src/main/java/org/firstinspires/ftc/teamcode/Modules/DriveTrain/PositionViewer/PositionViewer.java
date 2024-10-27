package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class PositionViewer implements Listener {
    private final PositionLocalViewer localViewer = new PositionLocalViewer();

    @Override
    public void init(Robot robot) {
        localViewer.init(robot);
    }

    private final Position positionGlobal = new Position(0, 0, 0);

    public Position getPositionGlobal() {
        return positionGlobal;
    }

    public PositionLocalViewer getLocalViewer() {
        return localViewer;
    }

    private void calcGlobalPosition() {
        positionGlobal.h = localViewer.getPositionLocal().h;
        Position dp = new Position();
        dp.copyFrom(localViewer.deltaPositionLocal);
        dp.rotateIt(positionGlobal.h);
        positionGlobal.plus(dp);
    }

    @Override
    public void read() {
        localViewer.update();
        calcGlobalPosition();
    }
}
