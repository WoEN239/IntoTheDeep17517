package org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain.Scorer;

import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.ScorerPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Diff.Diff;

/*
  Writing by EgorKhvostikov
*/
public class Scorer {

    private Diff scorer;

    public void init(){
        scorer = IntakeDevices.scorer;
    }

    public void wall(){
        scorer.setTarget(ScorerPosition.wallYaw, ScorerPosition.wallPitch);
    }

    public void eatAccept(){
        scorer.setTarget(ScorerPosition.eatAcceptYaw, ScorerPosition.eatAcceptPitch);
    }

    public void regrip(){
       scorer.setTarget(ScorerPosition.regripYaw,ScorerPosition.regripPitch);
    }
    public void target(){
        scorer.setTarget(ScorerPosition.targetYaw, ScorerPosition.targetPitch);
    }

    public void score(){
        scorer.setTarget(ScorerPosition.scoreYaw, ScorerPosition.scorePitch);
    }

    public void swipe(){
        scorer.setTarget(ScorerPosition.swipeYaw,ScorerPosition.swipePitch);
    }

    public void human(){
        scorer.setTarget(ScorerPosition.swipeYaw,ScorerPosition.swipePitch);
    }

}
