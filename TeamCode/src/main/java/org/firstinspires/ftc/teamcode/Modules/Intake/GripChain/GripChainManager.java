package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeModules;

public class GripChainManager {
    private GripTask task = GripTask.PASS;

    public void startEat() {

    }
    public void endEat(){
        task = GripTask.PASS;
    }

    public enum GripTask {
        TARGETING,EAT,SCORE,PASS;
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
        GripTask.EAT.init(

                ()->modules.grip.close(),
                ()->modules.innerTransfer.out(),
                ()->{if(timer.seconds()>0.5) task = GripTask.PASS;}

        );
        GripTask.TARGETING.init(

                ()->modules.grip.open(),
                ()->modules.innerTransfer.out()
        );
        GripTask.SCORE.init(

                ()->modules.innerTransfer.out(),
                ()->{if(timer.seconds()>2) task = GripTask.PASS;},
                ()->{
                    if(timer.seconds()>1){
                        modules.grip.open();
                    }else{
                        modules.grip.close();
                    }
                }
        );

    }

    /* modules */
    public void setModules(IntakeModules modules) {this.modules = modules;}
    private IntakeModules modules = new IntakeModules();

    /* main update */
    ElapsedTime timer = new ElapsedTime();
    private boolean f = true;

    public void update(){
        if(task !=  GripTask.PASS){
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
        return task == GripTask.PASS;
    }

    /* end */
}
