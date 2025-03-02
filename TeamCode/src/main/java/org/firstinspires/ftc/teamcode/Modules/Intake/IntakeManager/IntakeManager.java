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
    public void centerEat(){
        castCenterEat();
    }

    public void wallEat(){
        castWallEat();
    }

    public void swipe(){
        castSwipe();
    }

    public void scoreAxis(){
        castScoreAxis();
    }

}
