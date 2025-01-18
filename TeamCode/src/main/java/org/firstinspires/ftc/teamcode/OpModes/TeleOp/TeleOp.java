package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    Position position = new Position();

    public void loopRun() {
        BaseMode.isField = true;
        //position.copyFrom(robot.positionListener.getPosition());
        robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
        robot.driveTrain.setVelocityTarget(
                new Position(-gamepad1.left_stick_y*240, gamepad1.left_stick_x*240, gamepad1.right_stick_x*100)
        );

//        if(waitDown)robot.intake.setTarget(IntakeState.WAIT_DOWN);
//        if(waitUp)  robot.intake.setTarget(IntakeState.WAIT_UP)  ;
//        if(waitEat) robot.intake.setTarget(IntakeState.WAIT_EAT) ;

        Robot.telemetryPacket.put("Voltage: ",Robot.voltage);
        Robot.telemetryPacket.put("Circle", gamepad1.a);
//        Robot.telemetry.addData("sticks ",leftStickX+leftStickY+rightStickX);
//        Robot.telemetry.addData("Lift target  ",robot.liftController.getTargetPosition());

        Robot.telemetryPacket.put("x", robot.driveTrain.position.x);
        Robot.telemetryPacket.put("h", robot.driveTrain.position.h);
        Robot.telemetryPacket.put("y", robot.driveTrain.
                position.y);

        FieldView.position = robot.driveTrain.position;
        FieldView.circle = robot.driveTrain.getPidTarget();
    }
}
