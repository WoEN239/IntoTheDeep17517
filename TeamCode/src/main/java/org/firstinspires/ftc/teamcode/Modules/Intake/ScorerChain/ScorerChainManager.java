package org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class ScorerChainManager {
    private ScorerTask task = ScorerTask.EAT;
    public LiftPosition liftRequest = LiftPosition.DOWN;
    private boolean isTargeted = false;

    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }

    public void startEat(){
        task = ScorerTask.TO_EAT;
        timer.reset();
    }

    public void score(){
        task = ScorerTask.END_EAT;
    }

    public enum ScorerTask {
        TO_EAT,EAT,END_EAT,TARGET,MOVE;
        private Runnable[] update;

        public void init(Runnable... run) {
            update = run;
        }

        public void update() {
            for (Runnable r : update) {
                r.run();
            }
        }

    }

    public void initTasks(){
        ScorerTask.TO_EAT.init(
                ()->{
                    liftRequest = LiftPosition.IN_POSITION;
                    modules.scorer.wall();
                    modules.scorerGrip.open();

                    modules.eater.down();
                    modules.transfer.down();
                    modules.transfer.in();

                    if(timer.seconds()>0.5){
                        timer.reset();
                        task = ScorerTask.EAT;
                    }

                }
        );
        ScorerTask.EAT.init(
                ()->{
                    if(isTargeted){
                        modules.scorerGrip.close();
                        if(timer.seconds()>0.2){
                            modules.scorer.eatAccept() ;
                        }
                        if(timer.seconds()>0.5){
                            timer.reset();
                            task = ScorerTask.END_EAT;
                        }
                    }else {
                        modules.scorerGrip.open();
                        modules.scorer.wall();
                        timer.reset();
                    }

                }
        );
        ScorerTask.END_EAT.init(
                ()->{
                    modules.scorer.target();
                    liftRequest = LiftPosition.SCORE_AXIS;
                    if(timer.seconds()>0.5){
                        modules.scorerGrip.close();
                    }
                    if(timer.seconds()>1){
                        timer.reset();
                        task = ScorerTask.TARGET;
                    }
                }
        );
        ScorerTask.TARGET.init(
                ()->{
                    if(isTargeted){
                        modules.scorer.score();
                        if(timer.seconds()>0.5){
                            modules.scorerGrip.open();
                        }
                        if(timer.seconds()>0.7){
                            timer.reset();
                            task = ScorerTask.MOVE;
                        }
                    }else{
                        modules.scorerGrip.close();
                        modules.scorer.target();
                        timer.reset();
                    }

                }
        );
        ScorerTask.MOVE.init(
                ()->{
                }
        );
    }

    public void setModules(IntakeModules modules) {this.modules = modules;}
    private IntakeModules modules = new IntakeModules();

    ElapsedTime timer = new ElapsedTime();

    public void update(){
        task.update();
        Robot.telemetryPacket.put("SCorer Task",task.toString());
    }

    public boolean isDone(){
        return task == ScorerTask.MOVE;
    }
}
