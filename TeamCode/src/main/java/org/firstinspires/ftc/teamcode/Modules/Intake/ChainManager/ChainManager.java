package org.firstinspires.ftc.teamcode.Modules.Intake.ChainManager;

/*
 Writing by EgorKhvostikov
*/

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.BrushChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.GripChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.ScoreChain.ScoreChainManager;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public abstract class ChainManager {
    private final BrushChainManager brushChainManager = new BrushChainManager();
    private final GripChainManager  gripChainManager  = new GripChainManager ();
    private final ScoreChainManager scoreChainManager = new ScoreChainManager();
    private final IntakeModules modules = new IntakeModules();

    private ChainState state = ChainState.MOVE;
    private final LiftManager liftManager = new LiftManager();

    private boolean isDone = false;
    public boolean isDone(){return  isDone;}

    public boolean isLiftDone(){return liftManager.isDone();}
    public double getLiftPos() {return liftManager.position;}

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

    protected void castBrushEat(){
        brushChainManager.startEat();
        state = ChainState.BRUSH;
    }
    protected void castCancel(){
        state = ChainState.MOVE;
    }

    protected void castWallEat(){
        gripChainManager.setEat();
        state = ChainState.GRIP;
    }

    protected void castBasketScore(){
        scoreChainManager.scoreBasket();
        state  = ChainState.SCORE;
    }

    protected void castAxisScore(){
        scoreChainManager.scoreAxis();
        state  = ChainState.SCORE;
    }

    public void setTargeted(boolean t){
        scoreChainManager.setTargeted(t);
        gripChainManager.setTargeted(t);
    }

    public void update(){
        Robot.telemetryPacket.put("intake state",IntakeManager.getState());
        Robot.telemetryPacket.put("chain state", state.toString());

        switch (state){
            case MOVE:
                modules.brush.off();
                modules.brush.in();

                modules.grip.in();
                modules.transfer.normal();

                liftManager.setTarget(LiftPosition.DOWN);
                isDone = true;
                break;
            case SCORE:
                liftManager.setTarget(scoreChainManager.liftRequest);
                scoreChainManager.setLiftAtTarget(liftManager.isDone());
                scoreChainManager.update();

                isDone = scoreChainManager.isDone();
                break;
            case BRUSH:
                liftManager.setTarget(brushChainManager.liftRequest);
                brushChainManager.update();

                isDone = brushChainManager.isDone();
                break;
            case GRIP:
                liftManager.setTarget(gripChainManager.liftRequest);
                gripChainManager.update();

                isDone = gripChainManager.isDone();
                break;
        }

        liftManager.update();

    }

    public void setLiftManual(boolean liftManual) {
        liftManager.setManual(liftManual);
    }
    public void setManualTarget(double manualTarget){
        liftManager.setManTarget(manualTarget);
    }

}

