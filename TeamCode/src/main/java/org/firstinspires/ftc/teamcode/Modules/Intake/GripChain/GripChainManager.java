package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.BrushChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class GripChainManager {
    private GripTask task = GripTask.MOVE;
    private boolean isTargeted = false;

    public void setEat(){
        task = GripTask.TO_EAT;
        timer.reset();
    }

    public LiftPosition liftRequest = LiftPosition.DOWN;
    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }

    public enum GripTask {
        TO_EAT,EAT,END_EAT,MOVE;
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
        GripTask.TO_EAT.init(
                ()-> {
                    if (timer.seconds() < 0.5) {
                        modules.grip.open();
                        modules.grip.in();
                        modules.innerTransfer.centre();
                    }

                    if (timer.seconds() > 0.55) {
                        liftRequest = LiftPosition.IN_POSITION;
                    }

                    if (timer.seconds() > 0.7) {
                        modules.innerTransfer.out();
                    }

                    if (timer.seconds() > 0.8) {
                        timer.reset();
                        task = GripTask.EAT;
                    }
                }
       );
       GripTask.EAT.init(
               ()->{
                   liftRequest = LiftPosition.WALL_EAT;
                   modules.grip.out();
                   if(timer.seconds()>0.3 && isTargeted){
                       modules.grip.close();
                       timer.reset();
                       task = GripTask.END_EAT;
                   }

               }
       );
       GripTask.END_EAT.init(
               ()->{
                   modules.grip.close();
                   if(timer.seconds()>0.3){
                       modules.innerTransfer.centre();
                   }
                   if(timer.seconds()>0.7){
                       liftRequest = LiftPosition.IN_POSITION;
                       timer.reset();
                       task = GripTask.MOVE;
                   }
               }
       );
       GripTask.MOVE.init(
               ()->{
                   modules.grip.close();
                   modules.grip.out();

                   modules.innerTransfer.out();
               }
       );

    }

    /* modules */
    public void setModules(IntakeModules modules) {this.modules = modules;}
    private IntakeModules modules = new IntakeModules();

    /* main update */
    ElapsedTime timer = new ElapsedTime();

    public void update(){

        task.update();
    }

    public boolean isDone(){
        return task == GripTask.MOVE;
    }

    /* end */
}
