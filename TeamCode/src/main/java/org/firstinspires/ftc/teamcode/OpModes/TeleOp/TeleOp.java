package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorSensor;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.BrushMotorPowers;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {

    ColorSensor colorSensor = new ColorSensor();
    public void callRun(){
        Robot.myTeam = Team.BLUE;
        BaseMode.isField = true;
        isNeedToCall = false;
        colorSensor.init();
    }

    public void loopRun() {
        robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
        robot.driveTrain.setVelocityTarget(
                new Position(-gamepad1.left_stick_y*240, -gamepad1.left_stick_x*240, -gamepad1.right_stick_x*250)
        );

        if(gamepad1.right_bumper){
            robot.intake.startBrushEat();
        }
        if(gamepad1.left_bumper){
            robot.intake.wallEat();
        }

        if(gamepad1.a) {
            BrushMotorPowers.forward = 12;
        }else{
            BrushMotorPowers.forward = -12;
        }

        if(gamepad1.dpad_up){
            robot.intake.basketScore();
        }

        if(gamepad1.triangle){
            robot.intake.axisScore();
        }

        robot.intake.setTargeted(gamepad1.dpad_down);


        Transfer.eatPos = 1;

        FieldView.position = robot.driveTrain.position;
        FieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
