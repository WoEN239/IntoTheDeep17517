package org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager;

import org.firstinspires.ftc.teamcode.Modules.Intake.ChainManager.ChainManager;

/*
 Writing by EgorKhvostikov
*/
public class IntakeManager extends ChainManager {

    public void cancel(){
        castCancel();
    }
    public void centerEat(){
        castCenterEat();
    }
    public void autoEat(){
        castAutoCenterEat();
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
