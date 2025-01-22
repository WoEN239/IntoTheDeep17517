package org.firstinspires.ftc.teamcode.Modules.Intake;

/*
 Writing by EgorKhvostikov
*/

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.BrushChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.GripChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.ScoreChain.ScoreChainManager;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class IntakeManager {
    private BrushChainManager brushChainManager = new BrushChainManager();
    private GripChainManager  gripChainManager  = new GripChainManager ();
    private ScoreChainManager scoreChainManager = new ScoreChainManager();

    private IntakeModules     modules = new IntakeModules();


    private IntakeState state = IntakeState .MOVE;
    private IntakeState target = IntakeState.MOVE;
    private LiftManager liftManager = new LiftManager();


    public enum IntakeState{
        SCORE,BRUSH,GRIP,MOVE
    }

    public void init(){
        modules.init();
        liftManager.init();

        brushChainManager.setModules(modules);
        gripChainManager.setModules(modules);
        scoreChainManager.setModules(modules);

        brushChainManager.initTasks();
        gripChainManager .initTasks();
        scoreChainManager.initTasks();

    }

    public void startBrushEat(){
        brushChainManager.startEat();
        target = IntakeState.BRUSH;
        state = IntakeState.BRUSH;
    }

    public void wallEat(){
        gripChainManager.setEat();
        target = IntakeState.GRIP;
        state = IntakeState.GRIP;
    }

    public void basketScore(){
        scoreChainManager.scoreBasket();
        state  = IntakeState .SCORE;
        target = IntakeState.SCORE;
    }

    public void axisScore(){
        scoreChainManager.scoreAxis();
        state  = IntakeState.SCORE;
        target = IntakeState.SCORE;
    }

    public void setTargeted(boolean t){
        scoreChainManager.setTargeted(t);
        gripChainManager.setTargeted(t);
    }

    public void updateState(){
        switch (state){
            case MOVE:
                modules.grip.open();
                modules.grip.in();

                modules.brush.off();
                modules.brush.in();

                modules.transfer.normal();
                modules.innerTransfer.in();
                break;
            case SCORE:
                liftManager.setTarget(scoreChainManager.liftRequest);
                scoreChainManager.setLiftAtTarget(liftManager.isDone());
                scoreChainManager.update();
                break;
            case BRUSH:
                liftManager.setTarget(brushChainManager.liftRequest);
                brushChainManager.update();
                break;
            case GRIP:
                liftManager.setTarget(gripChainManager.liftRequest);
                gripChainManager.update();
                break;
        }
        liftManager.update();
    }

//
//    public void changeState(){
//        switch (target){
//            case MOVE:
//                state = target;
//                break;
//            case GRIP:
//                brushChainManager.endEat();
//                brushChainManager.update();
//                if(brushChainManager.isDone()){
//                    state = IntakeState.GRIP;
//                }
//                break;
//            case BRUSH:
//                gripChainManager.endEat();
//                gripChainManager.update();
//                if(gripChainManager.isDone()){
//                    state = IntakeState.BRUSH;
//                }
//                break;
//        }
//    }
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
       //     changeState();
            f = false;
            isDone = false;
        }else {
            updateState();
            f = true;
            isDone = true;
        }
    }

}

