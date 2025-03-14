package org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.Manager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.OpModes.TeleOp.TeleOp;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class EaterChainManager {
    private EaterTask task = EaterTask.MOVE;
    private boolean isTargeted = false;

    public void startEat(){
        task = EaterTask.TO_EAT;
        timer.reset();
    }

    public void autoEat(){
        task = EaterTask.TO_AUTO_EAT;
        timer.reset();
    }

    public LiftPosition liftRequest = LiftPosition.IN_POSITION;
    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }

    public enum EaterTask {
        TO_EAT, EAT, END_EAT, SCORE,TARGET, MOVE,
        TO_AUTO_EAT,AUTO_TARGETING,AUTO_ACCEPT_EAT,AUTO_HOLD_IN,AUTO_SCORE, AUTO_LAUNCH;
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
                    modules.scorer.regrip();

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
                    modules.scorer.regrip();

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

                   if(timer.seconds()>0.7) {
                       modules.eaterGrip.open();
                   }
                   if(timer.seconds()>1){
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

        EaterTask.TO_AUTO_EAT.init(
                ()->{
                    liftRequest = LiftPosition.IN_POSITION;
                    modules.transfer.target();
                    modules.transfer.normal();
                    modules.eater.down();
                    modules.eaterGrip.open();

                    modules.scorer.regrip();
                    if(timer.seconds()>0.5){
                        timer.reset();
                        task = EaterTask.AUTO_TARGETING;
                    }
                }
        );
        EaterTask.AUTO_TARGETING.init(
                ()->{
                    modules.transfer.normal();
                    modules.eater.down();
                    if(isTargeted){
                        timer.reset();
                        task = EaterTask.AUTO_ACCEPT_EAT;
                    }
                }
        );

        EaterTask.AUTO_ACCEPT_EAT.init(
                ()->{
                    modules.transfer.down();
                    modules.eater.down();
                    //modules.transfer.eat();
                    if(timer.seconds()>0.5){
                        modules.transfer.down();
                    }
                    if(timer.seconds()>0.6){
                        modules.eaterGrip.close();
                    }
                    if(timer.seconds()>0.7){
                        timer.reset();
                    //    task = EaterTask.AUTO_HOLD_IN;
                        task = EaterTask.AUTO_LAUNCH;
                    }
                }
        );
        EaterTask.AUTO_HOLD_IN.init(
                ()->{
                    modules.transfer.up();
                    modules.eaterGrip.close();
                    modules.transfer.normal();
                    if(isTargeted){
                        timer.reset();
                        task = EaterTask.AUTO_SCORE;
                    }
                }
        );

        EaterTask.AUTO_LAUNCH.init(
                () -> {
                    liftRequest = LiftPosition.LAUNCH;
                    if(timer.seconds() > 0.6){
                        modules.transfer.in();
                    }
                    if(timer.seconds() > 0.75)
                    {
                        modules.eater.up();
                        modules.transfer.up();
                    }
                    if(timer.seconds() > 0.85){
                        modules.eaterGrip.open();
                    }
                    if(timer.seconds() > 0.9){
                        timer.reset();
                        task = EaterTask.MOVE;
                    }
                }
        );

        EaterTask.AUTO_SCORE.init(
                ()->{
                    modules.transfer.eatEnd();
                    modules.transfer.down();
                    modules.eater.down();
                    if(timer.seconds()>0.2){
                        modules.eaterGrip.open();
                    }
                    if(timer.seconds()>0.4){
                        timer.reset();
                        task = EaterTask.MOVE;
                    }
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
