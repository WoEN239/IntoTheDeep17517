package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Brush.Brush;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeStates.IntakeBrushState;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class BrushChainManager {
    private IntakeBrushState state  = IntakeBrushState.DOWN;
    private IntakeBrushState target = IntakeBrushState.DOWN;
    private void setTarget(IntakeBrushState t){target = t;}

    /* modules */
    private final Brush brush       = new Brush();
    private final Transfer transfer = new Transfer();

    /* state function */
    private void updateDown(){
        brush.off();
        brush.up();
        brush.closeWall();
        transfer.normal();
    }

    private void updateEat(){
        brush.on();
        brush.down();
        brush.openWall();
        transfer.eat();
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
        switch (state){
            case EAT:
                updateEat();
                break;
            case DOWN:
                updateDown();
                break;
        }
    }

    private void updateTransmission(){
        switch (target){
            case EAT:
                toEat();
                break;
            case DOWN:
                toDown();
                break;
        }
    }

    /* main update */
    ElapsedTime timer = new ElapsedTime();
    private boolean f = true;

    public void update(){
        Robot.telemetry.addData("State :", state.toString());
        Robot.telemetry.addData("Target state :", target.toString());
        if(target != state){
            if(f){
                timer.reset();
            }
            updateTransmission();
            f = false;
            isDone = false;
        }else {
            updateState();
            f = true;
            isDone = true;
        }
    }

    boolean isDone = false;
    public boolean isDone(){
        return isDone;
    }

    /* end */
}
