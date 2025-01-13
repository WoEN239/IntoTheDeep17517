package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    Position position = new Position();

    public void doing() {
        BaseMode.isField = true;
        //position.copyFrom(robot.positionListener.getPosition());
//        robot.velocityPidController.moveGlobal(
//                new Position(leftStickY*360, leftStickX*360, rightStickX*360)
//        );

//        if(waitDown)robot.intake.setTarget(IntakeState.WAIT_DOWN);
//        if(waitUp)  robot.intake.setTarget(IntakeState.WAIT_UP)  ;
//        if(waitEat) robot.intake.setTarget(IntakeState.WAIT_EAT) ;

        Robot.telemetry.addData("Voltage: ",Robot.voltage);
        Robot.telemetry.addData("Circle", gamepad1.a);
//        Robot.telemetry.addData("sticks ",leftStickX+leftStickY+rightStickX);
//        Robot.telemetry.addData("Lift target  ",robot.liftController.getTargetPosition());

        Robot.telemetry.addData("x", position.x);
        Robot.telemetry.addData("h", position.h);
        Robot.telemetry.addData("y", position.y);


    }
}
