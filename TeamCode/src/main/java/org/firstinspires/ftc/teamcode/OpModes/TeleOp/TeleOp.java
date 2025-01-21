package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorSensor;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {

    public void callRun(){
        BaseMode.isField = true;
        isNeedToCall = false;
    }

    public void loopRun() {
        robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
        robot.driveTrain.setVelocityTarget(
                new Position(-gamepad1.left_stick_y*240, -gamepad1.left_stick_x*240, gamepad1.right_stick_x*250)
        );

        if(gamepad1.right_bumper){
            robot.intake.startBrushEat();
        }



        Transfer.eatPos = 1;

        Robot.telemetryPacket.put("green", ColorSensor.sensor.green());
        FieldView.position = robot.driveTrain.position;
        FieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
