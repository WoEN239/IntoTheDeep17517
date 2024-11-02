package org.firstinspires.ftc.teamcode.OpModes;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.GrabberStateMachine;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeState;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    public void doing() {
        robot.velocityController.move(
                new Position(leftStickY*2400,leftStickX*2400,rightStickX*2400)
        );

        if(dropSamples) robot.grabberStateMachine.setState(IntakeState.DROP_SIMPLES);
        if(grabSamples) robot.grabberStateMachine.setState(IntakeState.GRAB);
        if(highBasket) robot.grabberStateMachine.setState(IntakeState.TO_HIGH_BASKET);
        if(lowBasket) robot.grabberStateMachine.setState(IntakeState.TO_LOW_BASKET);
        if(toHighAxis) robot.grabberStateMachine.setState(IntakeState.TO_HIGH_AXIS);
        if(toLowAxis) robot.grabberStateMachine.setState(IntakeState.TO_LOW_AXIS);

    }
}
