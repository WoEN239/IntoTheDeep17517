package org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
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
        TO_EAT, EAT, END_EAT, RE_GRIP,TARGET_HUMAN, MOVE;
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
                        task = EaterTask.EAT;
                    }
                }
       );
       EaterTask.EAT.init(
               ()->{
                   modules.eater.down();
                   modules.transfer.down();
                   modules.scorerGrip.open();
                   modules.scorer.regrip();

                   if(isTargeted){
                       modules.eaterGrip.close();
                       if(timer.seconds()>0.2){
                           timer.reset();
                           task = EaterTask.END_EAT;
                       }
                   }else{
                       modules.eaterGrip.open();
                       timer.reset();
                   }

               }
       );
       EaterTask.END_EAT.init(
               ()->{

                    modules.transfer.up();
                    modules.eater.up();
                    modules.eaterGrip.regrip();
                    if(timer.seconds()>0.6){
                        modules.transfer.normal();
                    }

                    if(timer.seconds()> 1.2){
                        timer.reset();
                        task = EaterTask.RE_GRIP;
                    }
               }
       );

        EaterTask.RE_GRIP.init(
                ()->{
                    modules.scorerGrip.regrip();
                    if(timer.seconds()>0.3){
                        modules.eaterGrip.open();
                    }
                    if(timer.seconds()>0.5){
                        modules.eater.down();
                        modules.transfer.down();
                    }
                    if(timer.seconds()>0.7){
                        timer.reset();
                        task = EaterTask.TARGET_HUMAN;
                    }
                }
        );

       EaterTask.TARGET_HUMAN.init(
               ()->{
                   if(isTargeted){
                       modules.scorerGrip.open();
                       task = EaterTask.MOVE;
                   }else {
                       modules.scorerGrip.close();
                   }
                   modules.scorer.human();
                   if(timer.seconds()>0.3){
                       modules.eater   .up();
                       modules.transfer.up();
                       modules.transfer.normal();
                   }
               }
       );
        EaterTask.MOVE.init(
            ()->{
                liftRequest = LiftPosition.IN_POSITION;

                modules.eater   .up();
                modules.transfer.up();
                modules.transfer.normal();
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
