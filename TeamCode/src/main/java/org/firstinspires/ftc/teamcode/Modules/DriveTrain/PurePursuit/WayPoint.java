package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Task;

public class WayPoint {
    public PurePursuitTask onLineTask  = new PurePursuitTask(new Runnable[]{});
    public PurePursuitTask onPointTask = new PurePursuitTask(new Runnable[]{});
    public Position position;

    public WayPoint(Position position, PurePursuitTask onLineTask, PurePursuitTask onPointTask) {
        this.onLineTask = onLineTask;
        this.onPointTask = onPointTask;
        this.position = position;
    }

    public WayPoint(Position position, PurePursuitTask onPointTask) {
        this.position = position;
        this.onPointTask = onPointTask;
    }

    public WayPoint(Position position) {
        this.position = position;
    }
}
