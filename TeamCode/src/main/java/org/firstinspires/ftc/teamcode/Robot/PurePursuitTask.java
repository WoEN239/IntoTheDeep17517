package org.firstinspires.ftc.teamcode.Robot;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeStateMachine;

import java.util.function.Supplier;

public class PurePursuitTask {
    public final Runnable [] run;
    private Supplier<Boolean> isDone = ()->false;
    private Runnable [] end = new Runnable[]{};
    public boolean isRunOnce = false;
    private String  name = "";
    public static final PurePursuitTask Stub = new PurePursuitTask(
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
        return isDone.get();
    }

    public PurePursuitTask( Supplier<Boolean> isDone, Runnable[] end, Runnable... run) {
        this.run = run;
        this.isDone = isDone;
        this.end = end;
    }

    public PurePursuitTask(String name,Supplier<Boolean> isDone, Runnable... run ) {
        this.run = run;
        this.isDone = isDone;
        this.name = name;
    }

    public PurePursuitTask(Runnable... run) {
        this.run = run;
    }
    public static PurePursuitTask easyBuild(IntakeState state, IntakeStateMachine intake){
       return new PurePursuitTask(state.toString(),intake::isDone,()->intake.setTarget(state));
    }
    @NonNull
    @Override
    public String toString(){
        return name;
    }
}
