package org.firstinspires.ftc.teamcode.Robot;

import java.util.function.Supplier;

/**
 * Writing by EgorKhvostikov
 */

public class Task {
    private final Runnable[] run;
    private final Runnable[] end;
    private final Runnable[] init;
    private Supplier<Boolean> isDone;
    private Supplier<Boolean> isStart;

    public Task(Supplier<Boolean> isStart, Supplier<Boolean> isDone,  Runnable[] init, Runnable[] run, Runnable[] end) {
        this.isDone = isDone;
        this.run = run;
        this.end = end;
        this.init = init;
        this.isStart = isStart;
    }

    public Task(Supplier<Boolean> isStart, Supplier<Boolean> isDone, Runnable[] run) {
        this.isStart = isStart         ;
        this.isDone  = isDone          ;
        this.run     = run             ;
        this.end     = new Runnable[]{};
        this.init    = new Runnable[]{};
    }

    public Task(Runnable... init /*еденичное действие*/){
        this.init    = init            ;
        this.isStart = ()->false       ;
        this.isDone  = ()->true        ;
        this.end     = new Runnable[]{};
        this.run     = new Runnable[]{};
    }
    public static Task Stub = new Task(
            () -> false,
            () -> true ,
            new Runnable[]{},
            new Runnable[]{},
            new Runnable[]{}
    );

    public boolean isDone(){
        return isDone.get();
    }

    boolean firstEnd   = true;
    boolean firstStart = true;

    public void run() {
        if(firstStart){
            for(Runnable i : init){
                i.run();
            }
            firstStart = false;
        }

        if (isStart.get()) {
            isStart = () -> true;
            if (!isDone.get()) {
                for (Runnable i : run) {
                    i.run();
                }

            } else {
                if (firstEnd) {
                    for (Runnable i : end) {
                        i.run();
                    }
                }
                firstEnd = false;
                isDone = () -> true;
            }
        }

    }

}
