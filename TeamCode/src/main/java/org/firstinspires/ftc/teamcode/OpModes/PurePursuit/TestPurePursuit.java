package org.firstinspires.ftc.teamcode.OpModes.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegment;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Task;

public class TestPurePursuit extends BaseMode {
    @Override
    public void doing(){
        robot.addToQueue(new Task(
                ()->true,
                ()->robot.purePursuit.isDone(),
                new Runnable[]{
                        ()->robot.purePursuit.addWayPoint(
                                new Position(0,0,0)),
                        ()->robot.purePursuit.addWayPoint(
                                new Position(10,10,0))
                },
                new Runnable[]{},
                new Runnable[]{
                        ()->isDone = true
                }
        ));
    }
}
