package org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager;

import org.firstinspires.ftc.teamcode.Modules.Intake.ChainManager.ChainManager;

/*
 Writing by EgorKhvostikov
*/
public class IntakeManager extends ChainManager {
    private static IntakeState  state  = IntakeState.DOWN;

    public static IntakeState getState() {
        return state;
    }

    public static void setState(IntakeState state) {
        IntakeManager.state = state;
    }

    public enum IntakeState {
        BRUSH_EAT,DOWN,SAMPLE_IN_GRIP, WALL_EAT, SCORE
    }

    public void cancel(){
        castCancel();
        state = IntakeState.DOWN;
    }
    public boolean brushEat(){
        if(state == IntakeState.BRUSH_EAT || state == IntakeState.DOWN){
            castBrushEat();
            state = IntakeState.BRUSH_EAT;
            return true;
        }
        else return false;
    }

    public boolean wallEat(){
        if(state == IntakeState.WALL_EAT || state == IntakeState.DOWN){
            castWallEat();
            state = IntakeState.WALL_EAT;
            return true;
        }
        else return false;
    }

    public boolean scoreAxis(){
        if(state == IntakeState.WALL_EAT || state == IntakeState.SCORE){
            castAxisScore();
            state = IntakeState.SCORE;
            return true;
        }
        else return false;
    }

    public boolean scoreBasket(){
        if(state == IntakeState.SAMPLE_IN_GRIP || state == IntakeState.SCORE){
            castBasketScore();
            state = IntakeState.SCORE;
            return true;
        }
        else return false;
    }

}
