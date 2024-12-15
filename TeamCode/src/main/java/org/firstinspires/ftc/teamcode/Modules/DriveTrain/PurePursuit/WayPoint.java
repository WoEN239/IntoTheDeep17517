package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Task;

public class WayPoint {
    public Task onLineTask  = Task.Stub;
    public Task onPointTask = Task.Stub;
    public Position position;

    public WayPoint(Task onLineTask, Task onPointTask, Position position) {
        this.onLineTask = onLineTask;
        this.onPointTask = onPointTask;
        this.position = position;
    }

    public WayPoint(Position position, Task onPointTask) {
        this.position = position;
        this.onPointTask = onPointTask;
    }

    public WayPoint(Position position) {
        this.position = position;
    }
}
