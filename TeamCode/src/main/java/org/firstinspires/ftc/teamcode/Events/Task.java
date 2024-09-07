package org.firstinspires.ftc.teamcode.Events;

import java.util.function.Supplier;

public class Task {
    private Runnable run;
    private Supplier<Boolean> isDone;
    private final double guardTime;
    private final double bornTime;

    public Task(Runnable run,Supplier<Boolean> isDone, double guardTime){
        this.isDone = isDone;
        this.run = run;
        this.guardTime = guardTime;
        this.bornTime = System.currentTimeMillis();
    }
    public void run(){
        run.run();
    }

    public boolean isDone(){
        boolean target =  isDone();
        return System.currentTimeMillis()-bornTime>guardTime || target;
    }
}
