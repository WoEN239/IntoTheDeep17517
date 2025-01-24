package org.firstinspires.ftc.teamcode.Modules.Intake.ScoreChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class ScoreChainManager {

    private ScoreTask task = ScoreTask.MOVE;
    public LiftPosition liftRequest = LiftPosition.DOWN;
    private boolean isLiftAtTarget = false;
    public void setLiftAtTarget(boolean liftAtTarget) {
        isLiftAtTarget = liftAtTarget;
    }

    public boolean isTargeted = false;

    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }

    public void scoreAxis(){
        timer.reset();
        task = ScoreTask.TO_AXIS;
    }

    public void scoreBasket(){
        timer.reset();
        task = ScoreTask.TO_BASKET;
    }

    public enum ScoreTask {
        TO_AXIS,TO_BASKET,SCORE_AXIS,SCORE_BASKET,END_SCORE,MOVE;
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
        ScoreTask.TO_AXIS.init(
                ()->{
                    modules.grip.close();
                    modules.grip.out();
                    modules.innerTransfer.out();

                    liftRequest = LiftPosition.HIGHEST_AXIS;
                    if((isLiftAtTarget || timer.seconds()>2) && timer.seconds()>0.1){
                        timer.reset();
                        task = ScoreTask.SCORE_AXIS;
                    }
                }
        );
        ScoreTask.SCORE_AXIS.init(
                ()->{
                    if(isTargeted) {
                        liftRequest = LiftPosition.SCORE_AXIS;
                        if ((isLiftAtTarget || timer.seconds() > 2) && timer.seconds() > 0.5) {
                            modules.grip.open();
                            timer.reset();
                            task = ScoreTask.END_SCORE;
                        }
                    }else{
                        timer.reset();
                    }
                }
        );



        ScoreTask.TO_BASKET.init(
                ()->{
                    modules.grip.close();
                    modules.grip.out();
                    modules.innerTransfer.out();

                    liftRequest = LiftPosition.LOWEST_BASKET;
                    if((isLiftAtTarget || timer.seconds()>2) && timer.seconds()>0.1){
                        timer.reset();
                        task = ScoreTask.SCORE_BASKET;
                    }
                }
        );

        ScoreTask.SCORE_BASKET.init(
                ()->{
                    if(isTargeted) {
                        modules.grip.open();
                        if (timer.seconds() > 0.2) {
                            timer.reset();
                            task = ScoreTask.END_SCORE;
                        }
                    }else{
                        timer.reset();
                    }
                }
        );

        ScoreTask.END_SCORE.init(
                ()->{
                    liftRequest = LiftPosition.IN_POSITION;
                    modules.innerTransfer.centre();
                    modules.grip.in();
                    modules.grip.open();
                    modules.transfer.normal();

                    if(isLiftAtTarget || timer.seconds()>2){
                        liftRequest = LiftPosition.DOWN;
                        timer.reset();
                        task = ScoreTask.MOVE;
                    }
                }
        );
        ScoreTask.MOVE.init(
                ()->{
                    IntakeManager.setState(IntakeManager.IntakeState.DOWN);

                    liftRequest = LiftPosition.DOWN;

                    modules.innerTransfer.centre();
                    modules.transfer.normal();

                    modules.grip.in();
                    modules.grip.open();

                    modules.brush.in();
                    modules.brush.off();

                }
        );

    }

    /* modules */
    public void setModules(IntakeModules modules) {this.modules = modules;}
    private IntakeModules modules = new IntakeModules();

    /* main update */
    ElapsedTime timer = new ElapsedTime();

    public void update(){

        Robot.telemetryPacket.put("liftAtTarget",isLiftAtTarget);

        Robot.telemetryPacket.put("Task score", task.toString());

        task.update();

    }

    public boolean isDone(){
        return task == ScoreTask.MOVE;
    }

    /* end */

}
