package org.firstinspires.ftc.teamcode.Events;

import java.util.function.Supplier;

public class Task {
    private final Runnable run;
    private Supplier<Boolean> isDone;
    private final Supplier<Boolean> isStart;

    public Task(Supplier<Boolean> isStart, Supplier<Boolean> isDone,Runnable run){
        this.isDone = isDone;
        this.run = run;
        this.isStart = isStart;
    }
    public void run(){
        if(isStart.get()){
            if (!isDone.get()){
                run.run();
            }else{
                isDone = ()->false;
            }
        }

    }

}
