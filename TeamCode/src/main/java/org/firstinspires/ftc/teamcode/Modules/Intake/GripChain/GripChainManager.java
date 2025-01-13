package org.firstinspires.ftc.teamcode.Modules.Intake.GripChain;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Brush.Brush;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.Grip.Grip;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer.InnerTransfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeStates.IntakeBrushState;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeStates.IntakeGripState;

public class GripChainManager {
    private IntakeGripState state  = IntakeGripState.EAT;
    private IntakeGripState target = IntakeGripState.EAT;
    private void setTarget(IntakeGripState t){target = t;}

    /* modules */
    private final Grip grip       = new Grip();
    private final InnerTransfer innerTransfer = new InnerTransfer();

    /* state function */
    private void updateEat(){
        grip.open();
    }

    private void updateScoring(){
        grip.close();
    }
    /* */

}
