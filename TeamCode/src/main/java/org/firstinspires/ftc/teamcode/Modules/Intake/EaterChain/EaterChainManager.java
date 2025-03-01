package org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.OpModes.TeleOp.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class EaterChainManager {
    private EaterTask task = EaterTask.MOVE;
    private boolean isTargeted = false;
    private IntakeModules modules1;

    public void startEat(){
        task = EaterTask.TO_EAT;
        timer.reset();
    }

    public LiftPosition liftRequest = LiftPosition.DOWN;
    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }

    public enum EaterTask {
        TO_EAT, EAT, END_EAT, SCORE,TARGET, MOVE;
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
        EaterTask.TO_EAT.init(
                ()-> {
                    modules.transfer.eat();
                    modules.transfer.up();
                    modules.eaterGrip.open();
                    modules.eater.up();
                    liftRequest = LiftPosition.IN_POSITION;
                    if(timer.seconds()>0.5){
                        timer.reset();
                        task = EaterTask.TARGET;
                    }
                }
        );

        EaterTask.TARGET.init(
                ()->{
                    TeleOp.isNeedToSlow = true;

                    modules.transfer.eat();
                    modules.transfer.target();
                    modules.eaterGrip.open();

                    modules.eater.down();

                    modules.scorerGrip.open();
                    modules.scorer.human();

                    if(isTargeted){
                        TeleOp.isNeedToSlow = false;
                        timer.reset();
                        task = EaterTask.EAT;
                    }

                }
        );

        EaterTask.EAT.init(
               ()->{
                   TeleOp.isNeedToSlow = false;
                   modules.eaterGrip.close();
                   modules.transfer.down();
                   if(timer.seconds()>0.4){
                       timer.reset();
                       task = EaterTask.END_EAT;
                   }
               }
       );

       EaterTask.END_EAT.init(
               ()->{
                    modules.transfer.in();
                    modules.scorer.human();

                    modules.transfer.up();
                    modules.eater.up();
                    modules.eaterGrip.regrip();

                    if(timer.seconds()> 0.6){
                        if(isTargeted) {
                            timer.reset();
                            task = EaterTask.SCORE;
                        }
                    }
               }
       );

       EaterTask.SCORE.init(
               ()->{
                   modules.transfer.down();
                   modules.transfer.eatEnd();
                   modules.eater.down();

                   if(timer.seconds()>0.4){
                       modules.eaterGrip.open();
                       timer.reset();
                       task = EaterTask.MOVE;
                   }
               }
       );
        EaterTask.MOVE.init(
            ()->{
                liftRequest = LiftPosition.IN_POSITION;

                modules.eater   .up();
                modules.transfer.target();
                modules.transfer.in();
            }
        );
    }

    public void setModules(IntakeModules modules) {this.modules = modules;}
    private IntakeModules modules = new IntakeModules();

    ElapsedTime timer = new ElapsedTime();

    public void update(){
        Robot.telemetryPacket.put("eater task",task.toString());
        task.update();
    }

    public boolean isDone(){
        return task == EaterTask.MOVE;
    }

}
