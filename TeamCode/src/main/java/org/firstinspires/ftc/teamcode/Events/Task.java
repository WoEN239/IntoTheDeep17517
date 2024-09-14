package org.firstinspires.ftc.teamcode.Events;

import com.acmerobotics.dashboard.FtcDashboard;

import java.util.function.Supplier;

public class Task {
    private final Runnable [] run;
    private final Runnable [] end;
    private Supplier<Boolean> isDone;
    private final Supplier<Boolean> isStart;

    public Task(Supplier<Boolean> isStart, Supplier<Boolean> isDone,Runnable[] run, Runnable[] end){
        this.isDone = isDone;
        this.run = run;
        this.end = end;
        this.isStart = isStart;
    }
    public void run(){
        if(isStart.get()){
            if (!isDone.get()){
                for (Runnable i:run) {
                    i.run();
                };
            }else{
                for (Runnable i: end) {
                    i.run();
                }
                isDone = ()->true;
            }
        }

    }

}
