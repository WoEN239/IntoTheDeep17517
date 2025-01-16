package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Brush.Brush;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorDetective;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorSensor;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.Grip.Grip;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer.InnerTransfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class BrushChainManager {
    private IntakeBrushState state = IntakeBrushState.DOWN;
    private IntakeBrushTask  task = IntakeBrushTask.EAT;

    private LiftPosition liftRequest = LiftPosition.DOWN;

    public LiftPosition getLiftRequest() {
        return liftRequest;
    }

    /* states */
    public enum IntakeBrushState {
        DOWN, UP;

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

    public enum IntakeBrushTask{
        TO_EAT,EAT,END_EAT,SCORE,MOVE;
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
        IntakeBrushTask.TO_EAT.init(
            ()-> {
                if (state == IntakeBrushState.DOWN) {
                    transfer.eat();
                    brush.openWall();
                    brush.on();
                } else {
                    task = IntakeBrushTask.MOVE;
                }},

                ()->{if(timer.seconds()>1) task = IntakeBrushTask.EAT;}
        );

        IntakeBrushTask.EAT.init(
                ()->{
                if(sampleDetect.colorDetective != ColorDetective.OUR){
                    brush.openWall();
                    brush.on();
                    transfer.eat();
                }else{
                    task = IntakeBrushTask.END_EAT;
                    timer.reset();
                }
            }

        );

        IntakeBrushTask.END_EAT.init(
                ()->{
                    if(timer.seconds()>1){
                        task = IntakeBrushTask.MOVE;
                    }
                    brush.closeWall();
                    brush.off();
                    transfer.normal();
                }
        );

        IntakeBrushTask.SCORE.init(
                ()->{
                    liftRequest = LiftPosition.SCORE_AXIS;
                    if(timer.seconds()>1){
                        task = IntakeBrushTask.MOVE;
                    }
                }
        );

    }

    private void initStates(){
        IntakeBrushState.DOWN.init(
                brush::off,
                brush::up,
                brush::openWall,

                transfer::normal,
                innerTransfer::in,

                grip::close,
                grip::in,

                () -> liftRequest = LiftPosition.DOWN
        );
        IntakeBrushState.UP.init(

        );

    }

    /* modules */
    private final Brush brush = new Brush();
    private final Transfer transfer = new Transfer();
    private final InnerTransfer innerTransfer = new InnerTransfer();
    private final Grip grip = new Grip();
    private final ColorSensor sampleDetect = new ColorSensor();

    /* state function */
    private void updateDown(){

    }

    private void updateEat(){
        brush.on();
        brush.down();
        brush.openWall();
        transfer.eat();
        innerTransfer.in();
        grip.open();
        grip.in();

        liftRequest = LiftPosition.DOWN;
    }

    private void updateScoring(){
        brush.off();
        brush.up();
        brush.openWall();
        transfer.normal();
        innerTransfer.out();
        grip.close();
        grip.out();

        liftRequest = LiftPosition.HIGHEST_BASKET;
    }

    /* transmission function */
    private void toEat(){
        transfer.eat();
        brush.closeWall();
        brush.on();
    }

    private void toDown(){
        transfer.normal();
        brush.openWall();
        brush.off();
    }

    /* update functions */
    private void updateState(){
       state.update();

    }

    private void updateTransmission(){
        }

    /* State */
    private boolean isTaskDone = true;

    /* main update */
    ElapsedTime timer = new ElapsedTime();
    private boolean f = true;

    public void update(){
        Robot.telemetry.addData("State :", state.toString());
        if(task !=  IntakeBrushTask.MOVE){
            if(f){
                timer.reset();
                f = false;
            }
            task.update();
        }else {
            f = true;
            state.update();
        }

    }


    /* end */
}
