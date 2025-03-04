package org.firstinspires.ftc.teamcode.Modules.Intake.ChainManager;

/*
 Writing by EgorKhvostikov
*/

import org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain.ScorerChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.EaterChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager.IntakeModules;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public abstract class ChainManager {
    private final ScorerChainManager scorerChainManager = new ScorerChainManager();
    private final EaterChainManager eaterChainManager = new EaterChainManager();
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

        scorerChainManager.setModules(modules);
        eaterChainManager.setModules(modules);

        scorerChainManager.initTasks();
        eaterChainManager .initTasks();

    }

    protected void castAutoCenterEat(){
        eaterChainManager.autoEat();
        state = ChainState.EATER;
    }

    protected void castSwipe(){
        scorerChainManager.swipe();
        state = ChainState.SCORER;
    }
    protected void castScoreAxis(){
        scorerChainManager.score();
        state = ChainState.SCORER;
    }

    protected void castCenterEat(){
        eaterChainManager.startEat();
        state = ChainState.EATER;
    }
    protected void castCancel(){
        state = ChainState.MOVE;
    }

    protected void castWallEat(){
        scorerChainManager.startEat();
        state = ChainState.SCORER;
    }
    public void setTargeted(boolean t){
        eaterChainManager.setTargeted(t);
        scorerChainManager.setTargeted(t);
    }

    public void update(){
        Robot.telemetryPacket.put("intake state",IntakeManager.getState());
        Robot.telemetryPacket.put("chain state", state.toString());

        switch (state){
            case MOVE:
                liftManager.setTarget(LiftPosition.DOWN);
                isDone = true;
                break;
            case SCORER:
                liftManager.setTarget(scorerChainManager.liftRequest);
                scorerChainManager.update();

                isDone = scorerChainManager.isDone();
                break;
            case EATER:
                liftManager.setTarget(eaterChainManager.liftRequest);
                eaterChainManager.update();

                isDone = eaterChainManager.isDone();
                break;
        }

        liftManager.update();

    }

    public void rotateEater(double y){
        modules.eater.rotate(y);
    }

    public void setLiftManual(boolean liftManual) {
        liftManager.setManual(liftManual);
    }
    public void setManualTarget(double manualTarget){
        liftManager.setManTarget(manualTarget);
    }

}

