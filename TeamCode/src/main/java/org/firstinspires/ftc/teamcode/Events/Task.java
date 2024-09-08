package org.firstinspires.ftc.teamcode.Events;

import java.util.function.Supplier;

public class Task {
    private final Runnable run;
    private final Supplier<Boolean> isDone;
    private final double guardTime;
    private final double bornTime;

    public Task(Runnable run,Supplier<Boolean> isDone, double guardTime,double bornTime){
        this.isDone = isDone;
        this.run = run;
        this.guardTime = guardTime;
        this.bornTime = bornTime;
    }
    public void run(double timeNowSeconds){
        if(timeNowSeconds>=bornTime){
            if(!isDone.get()) {
                run.run();
            }
        }

    }

}
