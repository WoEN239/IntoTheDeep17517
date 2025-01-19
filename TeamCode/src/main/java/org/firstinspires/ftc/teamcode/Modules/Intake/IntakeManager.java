package org.firstinspires.ftc.teamcode.Modules.Intake;

/*
 Writing by EgorKhvostikov
*/

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.BrushChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.GripChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class IntakeManager {
    private BrushChainManager brushChainManager = new BrushChainManager();
    private GripChainManager  gripChainManager  = new GripChainManager();
    private IntakeState state = IntakeState.MOVE;
    private IntakeState target = IntakeState.MOVE;
    private IntakeModules modules = new IntakeModules();

    public enum IntakeState{
        MOVE, BRUSH,GRIP
    }

    public void startBrushEat(){
        brushChainManager.startEat();
    }
    public void endBrushEat(){
        brushChainManager.endEat();
    }

    public void endScoreBrush(){
        brushChainManager.score();
    }

    LiftPosition liftTarget = LiftPosition.DOWN;
    public void updateState(){
        switch (state){
            case MOVE:
                break;
            case BRUSH:
                brushChainManager.update();
                break;
            case GRIP:
                modules.transfer.normal();
                modules.brush.off();
                modules.brush.openWall();

                gripChainManager.update();
                break;
        }
    }

    public void changeState(){
        switch (target){
            case MOVE:
                state = target;
                break;
            case GRIP:
                brushChainManager.endEat();
                brushChainManager.update();
                if(brushChainManager.isDone()){
                    state = IntakeState.GRIP;
                }
                break;
            case BRUSH:
                gripChainManager.endEat();
                gripChainManager.update();
                if(gripChainManager.isDone()){
                    state = IntakeState.BRUSH;
                }
                break;
        }
    }
    ElapsedTime timer = new ElapsedTime();
    boolean isDone = false;
    private boolean f = true;
    public void update(){
        Robot.telemetryPacket.put("State :", state.toString());
        Robot.telemetryPacket.put("Target state :", target.toString());
        if(target!=state){
            if(f){
                timer.reset();
            }
            changeState();
            f = false;
            isDone = false;
        }else {
            updateState();
            f = true;
            isDone = true;
        }
    }

}

