package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorDetective;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeModules;
import org.firstinspires.ftc.teamcode.Robot.Robot;

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
        TO_EAT,EAT,CLEAR,END_EAT,RE_GRIP,MOVE, TARGETING, SCORE;
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
                modules.brush.up();
                modules.brush.on();
            },
            ()->{
            if(timer.seconds()>0.7) {
                timer.reset();
                task = BrushTask.EAT;
            }
            }

        );
        BrushTask.EAT.init(
                ()->{
                if(modules.sampleDetect.getColor() == ColorDetective.NOTHING) {
                    modules.brush.openWall();
                    modules.brush.down();
                    modules.brush.on();
                    modules.transfer.eat();
                }else if(modules.sampleDetect.getColor() == ColorDetective.OPPONENT){
                    timer.reset();
                    task = BrushTask.CLEAR;
                } else{
                    timer.reset();
                    task = BrushTask.END_EAT;
                }
            }

        );
        BrushTask.CLEAR.init(
                ()->{
                    if(modules.sampleDetect.getColor() == ColorDetective.OPPONENT || timer.seconds() < 0.25){
                        modules.brush.on();
                        modules.brush.clear();
                    }else{
                        task = BrushTask.EAT;
                        timer.reset();
                    }
                }
        );

        BrushTask.END_EAT.init(
                ()->{
                    modules.innerTransfer.in();
                    modules.grip.close();
                    modules.grip.in();


                    modules.brush.closeWall();
                    modules.brush.off();
                    modules.transfer.normal();
                    modules.brush.up();

                    if(timer.seconds()>0.7){
                        timer.reset();
                        task = BrushTask.RE_GRIP;
                    }

                }
        );

        BrushTask.RE_GRIP.init(
                ()->{
                    modules.brush.in();

                    modules.innerTransfer.in();
                    modules.grip.in();

                    if(timer.seconds()>0.2 && timer.seconds() < 0.35) {
                      modules.grip.open();
                    }

                    if(timer.seconds()>0.4){
                        modules.grip.close();
                    }

                    if(timer.seconds()>0.5){
                        task = BrushTask.MOVE;
                    }
                }
//                ()->{
//                    modules.innerTransfer.in();
//                    modules.brush.openWall();
//                    if(timer.seconds()>0.5) {
//                        modules.grip.close();
//                    }
//                    if(timer.seconds()>0.6){
//                        task = BrushTask.MOVE;
//                    }
//                }
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
        modules.sampleDetect.computeColorDetect();
        Robot.telemetryPacket.put("sample in brush",modules.sampleDetect.getColor());

        Robot.telemetryPacket.put("Task brush", task.toString());
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
