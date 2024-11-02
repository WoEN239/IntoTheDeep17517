package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

public class PositionPIDConfig extends BaseMode{

    public static Position target = new Position();

    @Override
    public void doing() {
        robot.positionController.move(target);
    }
}
