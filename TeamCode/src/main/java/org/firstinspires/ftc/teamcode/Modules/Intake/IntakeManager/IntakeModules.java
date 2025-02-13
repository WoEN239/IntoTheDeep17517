package org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager;

import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Eater.Eater;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.EaterGrip.EaterGrip;
import org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain.Scorer.Scorer;
import org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain.ScorerGrip.ScorerGrip;

public class IntakeModules {
    public final Transfer  transfer  = new Transfer() ;
    public final EaterGrip eaterGrip = new EaterGrip();
    public final Eater     eater     = new Eater()    ;

    public final ScorerGrip scorerGrip = new ScorerGrip();
    public final Scorer     scorer     = new Scorer()    ;

    public void init(){
        transfer  .init();
        eaterGrip .init();
        eater     .init();

        scorer    .init();
        scorerGrip.init();

    }
}
