package org.firstinspires.ftc.teamcode.Modules.Intake.ChainManager;

/*
 Writing by EgorKhvostikov
*/

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.BrushChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.GripChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.ScoreChain.ScoreChainManager;

public abstract class ChainManager {
    private final BrushChainManager brushChainManager = new BrushChainManager();
    private final GripChainManager  gripChainManager  = new GripChainManager ();
    private final ScoreChainManager scoreChainManager = new ScoreChainManager();
    private final IntakeModules modules = new IntakeModules();

    private ChainState state = ChainState.MOVE;
    private final LiftManager liftManager = new LiftManager();


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
        switch (state){
            case MOVE:
                modules.brush.off();
                modules.brush.in();

                modules.transfer.normal();
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

}

