package org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Brush.Brush;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorSensor;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.Grip.Grip;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer.InnerTransfer;

public class IntakeModules {
    public final Brush brush = new Brush();
    public final Transfer transfer = new Transfer();
    public final InnerTransfer innerTransfer = new InnerTransfer();
    public final Grip grip = new Grip();
    public final ColorSensor sampleDetect = new ColorSensor();

    public void init(){
        brush.init();
        transfer.init();
        innerTransfer.init();
        grip.init();
        sampleDetect.init();
    }
}
