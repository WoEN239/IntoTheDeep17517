package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import org.firstinspires.ftc.teamcode.Devices.IntakeServo;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    Position position = new Position();

    public void doing() {
        BaseMode.isField = true;

        robot.intake.setTarget(IntakeState.WAIT_EAT);
        position.copyFrom(robot.positionListener.getPositionGlobal());
        robot.velocityController.moveGlobal(
                new Position(-leftStickY*500, leftStickX*500, rightStickX*360)
        );
        Robot.telemetry.addData("state",robot.intake.getState().toString());
//if(waitDown)robot.intake.setTarget(IntakeState.WAIT_DOWN);
// if(waitUp)  robot.intake.setTarget(IntakeState.WAIT_UP)  ;
// if(waitEat) robot.intake.setTarget(IntakeState.WAIT_EAT) ;
//
//        Robot.telemetry.addData("Voltage: ",Robot.voltage);
//        Robot.telemetry.addData("Circle", gamepad1.a);
//        Robot.telemetry.addData("sticks ",leftStickX+leftStickY+rightStickX);
//        Robot.telemetry.addData("Lift target  ",robot.liftController.getTargetPosition());
//
//        Robot.telemetry.addData("x", position.x);
//        Robot.telemetry.addData("h", position.h);
//        Robot.telemetry.addData("y", position.y);
//

    }
}
