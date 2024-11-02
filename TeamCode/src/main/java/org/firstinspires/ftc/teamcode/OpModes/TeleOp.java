package org.firstinspires.ftc.teamcode.OpModes;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeState;
import org.firstinspires.ftc.teamcode.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    public void doing() {
        robot.velocityController.move(
                new Position(gamepad.leftStickY*2400, gamepad.leftStickX*2400, gamepad.rightStickX*2400)
        );

        if(gamepad.waitDown) robot.intake.setTarget(IntakeState.WAIT_DOWN);
        if(gamepad.waitUp) robot.intake.setTarget  (IntakeState.WAIT_UP);
        if(gamepad.waitEat) robot.intake.setTarget (IntakeState.WAIT_EAT);

        Robot.telemetry.addData("state: ",robot.intake.getState().toString());
        Robot.telemetry.addData("Voltage: ",Robot.voltage);
        Robot.telemetry.addData("sticks ",gamepad.leftStickX+gamepad.leftStickY+gamepad.rightStickX);

    }
}
