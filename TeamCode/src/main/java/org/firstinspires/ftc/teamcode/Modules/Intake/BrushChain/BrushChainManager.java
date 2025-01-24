package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorDetective;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class BrushChainManager {
    private BrushTask task = BrushTask.EAT;
    public LiftPosition liftRequest = LiftPosition.DOWN;

    public void startEat(){
        task = BrushTask.TO_EAT;
        timer.reset();
    }

    public void endEat(){
        task = BrushTask.END_EAT;
    }

    public enum BrushTask {
        TO_EAT,EAT,CLEAR,END_EAT,RE_GRIP,TO_MOVE,MOVE;
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
            ()->liftRequest = LiftPosition.DOWN,
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

                    modules.grip.in();
                    modules.grip.open();

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
                    if(modules.sampleDetect.getColor() == ColorDetective.OPPONENT || timer.seconds() < 0.4){
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

                   // modules.grip.close();
                    modules.grip.in();

                    modules.brush.closeWall();
                    modules.brush.off();
                    modules.transfer.normal();

                    if(timer.seconds()<0.7) {
                        modules.brush.up();
                    }

                    if(timer.seconds()>0.75) {
                        modules.brush.down();
                    }


                    if(timer.seconds()>1){
                        timer.reset();
                        task = BrushTask.RE_GRIP;
                    }

                }
        );

        BrushTask.RE_GRIP.init(
                ()->{
                    modules.brush.down();

                    modules.innerTransfer.in();
                    modules.grip.in();


                    if(timer.seconds()>0.2 && timer.seconds()<0.5){
                        modules.grip.close();
                    }

                    if( timer.seconds()>0.5){
                        modules.grip.close();
                    }

                    if(timer.seconds()>0.7){
                        task = BrushTask.TO_MOVE;
                        timer.reset();
                    }
                }
        );
        BrushTask.TO_MOVE.init(
                ()->{
                    if(timer.seconds()<0.5) {
                        modules.innerTransfer.centre();
                    }

                    if(timer.seconds()>0.55){
                        liftRequest = LiftPosition.IN_POSITION;
                    }

                    if(timer.seconds()>0.8){
                        modules.innerTransfer.out();
                    }

                    if(timer.seconds()>0.9){
                        timer.reset();
                        task = BrushTask.MOVE;
                    }
                }
        );

        BrushTask.MOVE.init(
                ()->{
                    IntakeManager.setState(IntakeManager.IntakeState.SAMPLE_IN_GRIP);

                    modules.innerTransfer.out();
                    modules.transfer.normal();

                    modules.grip.close();
                    modules.grip.out();

                    modules.brush.off();
                    modules.brush.in();

                }
        );

    }

    public void setModules(IntakeModules modules) {this.modules = modules;}
    private IntakeModules modules = new IntakeModules();

    ElapsedTime timer = new ElapsedTime();

    public void update(){
        modules.sampleDetect.update();

        Robot.telemetryPacket.put("sample in brush",modules.sampleDetect.getColor());

        Robot.telemetryPacket.put("Task brush", task.toString());

        task.update();

    }

    public boolean isDone(){
        return task == BrushTask.MOVE;
    }
}
