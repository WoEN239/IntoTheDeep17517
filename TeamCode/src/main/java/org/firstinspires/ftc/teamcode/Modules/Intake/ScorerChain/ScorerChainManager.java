package org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Modules.Intake.Manager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class ScorerChainManager {
    private ScorerTask task = ScorerTask.EAT;
    public LiftPosition liftRequest = LiftPosition.IN_POSITION;
    private boolean isTargeted = false;

    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }

    public void startEat(){
        task = ScorerTask.TO_EAT;
        timer.reset();
    }
    public void swipe(){
        task = ScorerTask.SWIPE;
        timer.reset();
    }
    public void score(){
        task = ScorerTask.AXIS_TARGETING;
        timer.reset();
    }

    public enum ScorerTask {
        TO_EAT,EAT,END_EAT,WALL_TARGETING, AXIS_TARGETING,SCORE,MOVE, SWIPE;

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
                    modules.transfer.target();
                    modules.transfer.in();

                    if(timer.seconds()>0.5){
                        timer.reset();
                        task = ScorerTask.WALL_TARGETING;
                    }

                }
        );
        ScorerTask.WALL_TARGETING.init(
                ()->{
                    modules.scorer.wall();
                    if(isTargeted){
                        timer.reset();
                        task = ScorerTask.EAT;
                    }
                }
        );
        ScorerTask.EAT.init(
                ()-> {
                    modules.scorerGrip.close();
                    if (timer.seconds() > 0.5) {
                        modules.scorer.eatAccept();
                    }
                    if (timer.seconds() > 0.8) {
                        timer.reset();
                        task = ScorerTask.END_EAT;
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
                        task = ScorerTask.AXIS_TARGETING;
                    }
                }
        );
        ScorerTask.AXIS_TARGETING.init(
                ()->{
                    liftRequest = LiftPosition.SCORE_AXIS;
                    if(isTargeted){
                        task = ScorerTask.SCORE;
                        timer.reset();
                    }else{
                        modules.scorerGrip.close();
                        modules.scorer.target();
                    }

                }
        );
        ScorerTask.SCORE.init(
                ()->{
                    modules.scorer.score();
                    if(timer.seconds()>0.7){
                        modules.scorerGrip.open();
                    }
                    if(timer.seconds()>0.9){
                        timer.reset();
                        task = ScorerTask.MOVE;
                    }
                }
        );

        ScorerTask.MOVE.init(
                ()->{
                    modules.scorer.eatAccept();
                    if(timer.seconds()>1) {
                        task = ScorerTask.TO_EAT;
                        timer.reset();
                    }
                }
        );

        ScorerTask.SWIPE.init(
                ()->{
                    liftRequest = LiftPosition.SWIPE;
                    modules.scorerGrip.close();
                    modules.scorer.swipe();
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
