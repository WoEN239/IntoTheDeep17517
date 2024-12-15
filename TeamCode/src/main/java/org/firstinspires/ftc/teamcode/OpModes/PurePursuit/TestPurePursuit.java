package org.firstinspires.ftc.teamcode.OpModes.PurePursuit;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

public class TestPurePursuit extends BaseMode {
    @Override
    public void doing(){
        robot.purePursuit.addWayPoints(new WayPoint(new Position(0,0,0)),new WayPoint(new Position(5,10,0)));
    }
}
