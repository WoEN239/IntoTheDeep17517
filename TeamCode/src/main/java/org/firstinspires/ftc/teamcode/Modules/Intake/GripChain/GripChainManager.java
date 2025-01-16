package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain;

import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.Grip.Grip;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer.InnerTransfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeStates.IntakeGripState;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;

public class GripChainManager {
    private IntakeGripState state  = IntakeGripState.EAT;
    private IntakeGripState target = IntakeGripState.EAT;
    private void setTarget(IntakeGripState t){target = t;}
    private LiftPosition liftRequest = LiftPosition.DOWN;
    public void setLiftRequest(LiftPosition liftRequest) {
        this.liftRequest = liftRequest;
    }

    /* modules */
    private final Grip grip       = new Grip();
    private final InnerTransfer innerTransfer = new InnerTransfer();

    /* state function */
    private void updateEat(){
        grip.open();
        innerTransfer.out();
    }

    private void updateScoring(){
        grip.close();
        innerTransfer.out();
        liftRequest = LiftPosition.HIGHEST_AXIS;
    }
    /* */

}
