package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.Task;

/*
  Writing by EgorKhvostikov
*/
public class WayPoint {
    public Task onLineTask ;
    public Task onPointTask;
    public Position position;

    private boolean isSpline = false;
    public boolean isSpline() {return isSpline;}

    public double beginTangent = 0;
    public double endTangent = 0;

    public WayPoint(Position position, Task onLineTask, Task onPointTask) {
        this.onLineTask = onLineTask;
        this.onPointTask = onPointTask;
        this.position = position;
    }

    public WayPoint(Position position, Task onPointTask) {
        this.position = position;
        this.onPointTask = onPointTask;
        this.onLineTask = Task.Stub;
    }

    public WayPoint(Position position) {
        this.onLineTask = Task.Stub;
        this.onPointTask = Task.Stub;
        this.position = position;
    }

    public WayPoint toSpline(double begin, double end){
        beginTangent = begin;
        endTangent = end;
        isSpline = true;
        return this;
    }

    @NonNull
    @Override
    public String toString(){
        return position.toString();
    }
}
