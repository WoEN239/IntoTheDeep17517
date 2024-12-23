package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeStateMachine;

import java.util.function.Supplier;

public class PurePursuitTask {
    private final Runnable [] run;
    private Supplier<Boolean> isDone = ()->false;
    private Runnable [] end = new Runnable[]{};
    public void run(){
        for (Runnable i: run) {
            i.run();
        }
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

    public PurePursuitTask(Supplier<Boolean> isDone, Runnable... run ) {
        this.run = run;
        this.isDone = isDone;
    }

    public PurePursuitTask(Runnable... run) {
        this.run = run;
    }
    public static PurePursuitTask easyBuild(IntakeState state, IntakeStateMachine intake){
       return new PurePursuitTask(intake::isDone,()->intake.setTarget(state));
    }
}
