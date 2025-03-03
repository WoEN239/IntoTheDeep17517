package org.firstinspires.ftc.teamcode.Robot.TaskManager;

import androidx.annotation.NonNull;

import java.util.function.Supplier;

public class Task {
    public final Runnable [] run;
    private Supplier<Boolean> isDone = ()->false;
    private Runnable [] end = new Runnable[]{};
    public boolean isRunOnce = false;
    private String  name = "";

    public static final Task Stub = new Task(
            ()->true,
            new Runnable[]{},
            new Runnable[]{}
    );

    public void setDoneChecker(Supplier<Boolean> isDone) {
        this.isDone = isDone;
    }

    public void run(){
        for (Runnable i: run) {
            i.run();
        }
        isRunOnce = true;
    }
    public void end(){
        for (Runnable i: end) {
            i.run();
        }
    }
    public boolean isDone(){
        if(isDone.get() && isRunOnce){
            isDone = ()->true;
        }
        return isDone.get();
    }

    public Task(Supplier<Boolean> isDone, Runnable[] end, Runnable... run) {
        this.run = run;
        this.isDone = isDone;
        this.end = end;
    }

    public Task(String name, Supplier<Boolean> isDone, Runnable... run ) {
        this.run = run;
        this.isDone = isDone;
        this.name = name;
    }

    public Task(Runnable... run) {
        this.run = run;
    }

    public Task(Supplier<Boolean> isDone, Runnable... run) {
        this.run = run;
        this.isDone = isDone;
    }

    @NonNull
    @Override
    public String toString(){
        return name;
    }
}
