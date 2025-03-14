package org.firstinspires.ftc.teamcode.Modules.Intake.Manager;

import org.firstinspires.ftc.teamcode.Modules.Intake.ChainManager.ChainManager;

/*
 Writing by EgorKhvostikov
*/
public class IntakeManager extends ChainManager {

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
