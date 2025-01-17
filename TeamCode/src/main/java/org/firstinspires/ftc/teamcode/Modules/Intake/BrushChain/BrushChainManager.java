package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorDetective;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeModules;

public class BrushChainManager {
    private BrushTask task = BrushTask.EAT;

    public void startEat(){
        task = BrushTask.TO_EAT;
    }

    public void endEat(){
        task = BrushTask.END_EAT;
    }
    public void target(){
        task = BrushTask.TARGETING;
    }
    public void  score(){
        task = BrushTask.SCORE;
    }

    public enum BrushTask {
        TO_EAT,EAT,END_EAT,RE_GRIP,MOVE, TARGETING, SCORE;
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
        BrushTask.TO_EAT.init(
            ()-> {
                modules.transfer.eat();
                modules.brush.openWall();
                modules.brush.on();
                task = BrushTask.MOVE;
            },
            ()->{if(timer.seconds()>1) task = BrushTask.EAT;}

        );

        BrushTask.EAT.init(
                ()->{
                if(modules.sampleDetect.colorDetective != ColorDetective.OUR){
                    modules.brush.openWall();
                    modules.brush.on();
                    modules.transfer.eat();
                }else{
                    task = BrushTask.END_EAT;
                    timer.reset();
                }
            }

        );

        BrushTask.END_EAT.init(
                ()->{
                    if(timer.seconds()>0.5){
                        timer.reset();
                        task = BrushTask.RE_GRIP;
                    }
                    modules.brush.closeWall();
                    modules.brush.off();
                    modules.transfer.normal();
                }
        );

        BrushTask.RE_GRIP.init(
                ()->{
                    modules.innerTransfer.in();
                    modules.brush.openWall();
                    if(timer.seconds()>0.5) {
                        modules.grip.close();
                    }
                    if(timer.seconds()>0.6){
                        task = BrushTask.MOVE;
                    }
                }
        );
        BrushTask.TARGETING.init(
                ()->modules.grip.close(),
                ()->modules.innerTransfer.out()
        );
        BrushTask.SCORE.init(
                ()->modules.grip.open()
        );

    }

    /* modules */
    public void setModules(IntakeModules modules) {this.modules = modules;}
    private IntakeModules modules = new IntakeModules();

    /* main update */
    ElapsedTime timer = new ElapsedTime();
    private boolean f = true;

    public void update(){
        if(task !=  BrushTask.MOVE){
            if(f){
                timer.reset();
                f = false;
            }
            task.update();
        }else {
            f = true;
        }

    }

    public boolean isDone(){
        return task == BrushTask.MOVE;
    }

    /* end */
}
