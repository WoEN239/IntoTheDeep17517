package org.firstinspires.ftc.teamcode.OpModes;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeState;
import org.firstinspires.ftc.teamcode.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    public void doing() {

        robot.velocityController.move(
                new Position(leftStickY*2400, leftStickX*2400, rightStickX*2400)
        );

        if(waitDown)robot.intake.setTarget(IntakeState.WAIT_DOWN);
        if(waitUp)  robot.intake.setTarget  (IntakeState.WAIT_UP);
        if(waitEat) robot.intake.setTarget (IntakeState.WAIT_EAT);

        Robot.telemetry.addData("Voltage: ",Robot.voltage);
        Robot.telemetry.addData("Circle", gamepad1.a);
        Robot.telemetry.addData("sticks ",leftStickX+leftStickY+rightStickX);
        Robot.telemetry.addData("Lift target  ",robot.liftController.getTargetPosition());

    }
}
