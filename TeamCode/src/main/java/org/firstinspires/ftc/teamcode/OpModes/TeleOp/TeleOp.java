package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.BrushMotorPowers;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    public void callRun(){
        Robot.myTeam = Team.RED;
        BaseMode.isField = true;
        isNeedToCall = false;
    }

    public void loopRun() {
        robot.driveTrain.setState(DriveTrainManager.RobotState.TELEOP);
        robot.driveTrain.setVelocityTarget(
                new Position(-gamepad1.left_stick_y*240,
                        -gamepad1.left_stick_x*240 - gamepad1.right_trigger*100 + gamepad1.left_trigger*100,

                        -gamepad1.right_stick_x*225)
        );

        if(gamepad1.right_bumper){
            telemetry.addData("Driver is a crocodile",!robot.intake.brushEat());
        }

        if(gamepad1.left_bumper){
            telemetry.addData("Driver is a crocodile",!robot.intake.wallEat());
        }


        if(gamepad1.dpad_up){
            telemetry.addData("Driver is a crocodile",!robot.intake.scoreAxis());
        }

        if(gamepad1.triangle){
            telemetry.addData("Driver is a crocodile",!robot.intake.scoreBasket());
        }
        robot.intake.setTargeted(gamepad1.dpad_down);

        if(gamepad1.a) {
            BrushMotorPowers.forward = 12;
        }else{
            BrushMotorPowers.forward = -12;
        }


        Transfer.eatPos = 1;


        Robot.telemetryPacket.put("x", robot.driveTrain.getPosition().x);
        Robot.telemetryPacket.put("h", robot.driveTrain.getPosition().h);
        Robot.telemetryPacket.put("y", robot.driveTrain.getPosition().y);

        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
