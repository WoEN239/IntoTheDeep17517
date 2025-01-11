package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftMode;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    Position position = new Position();

    public void doing() {
        BaseMode.isField = true;

        if(gamepad1.right_bumper) robot.intake.setTarget(IntakeState.WAIT_WALL_EAT);
        if(gamepad1.left_bumper) robot.intake.setTarget(IntakeState.WAIT_AXIS);


        double manT = 12*gamepad1.left_trigger + -12 * gamepad1.right_trigger;
        robot.liftController.setManualTarget(manT);
        if(abs(manT) > 2){
            robot.liftController.setMode(LiftMode.MANUAL);
        }else{
            robot.liftController.setMode(LiftMode.AUTO);
        }


        position.copyFrom(robot.positionListener.getPositionGlobal());
        robot.velocityController.moveGlobal(
                new Position(-leftStickY*500, leftStickX*500, rightStickX*360)
        );
        Robot.telemetry.addData("state",robot.intake.getState().toString());
        Robot.telemetry.addData("liftPos", robot.liftListener.getPosition());
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
