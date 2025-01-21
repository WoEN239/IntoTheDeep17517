package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;

public class WayPoint {
    public PurePursuitTask onLineTask ;
    public PurePursuitTask onPointTask;
    public Position position;

    public WayPoint(Position position, PurePursuitTask onLineTask, PurePursuitTask onPointTask) {
        this.onLineTask = onLineTask;
        this.onPointTask = onPointTask;
        this.position = position;
    }

    public WayPoint(Position position, PurePursuitTask onPointTask) {
        this.position = position;
        this.onPointTask = onPointTask;
        this.onLineTask = PurePursuitTask.Stub;
    }

    public WayPoint(Position position) {
        this.onLineTask = PurePursuitTask.Stub;
        this.onPointTask = PurePursuitTask.Stub;
        this.position = position;
    }
}
