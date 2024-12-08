package org.firstinspires.ftc.teamcode.OpModes.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePuresuit.Line;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePuresuit.PurePursuit;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Task;

public class TestPurePursuit extends BaseMode {
    @Override
    public void doing(){
        robot.addToQueue(new Task(
                ()->true,
                ()->robot.getSeconds()>35,
                new Runnable[]{
                        ()->PurePursuit.targetLine = new Line().makeWithTwoPoint(
                                robot.positionViewer.getPositionRealGlobal(), new Position(10,10,0) )
                },
                new Runnable[]{
                        ()-> robot.positionController.move(PurePursuit.getVirtualTarget(
                                robot.positionViewer.getPositionRealGlobal()
                        ))
                },
                new Runnable[]{
                        ()->isDone = true
                }
        ));
    }
}
