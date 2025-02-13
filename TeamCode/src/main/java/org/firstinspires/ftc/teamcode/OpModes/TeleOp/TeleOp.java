package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import org.firstinspires.ftc.teamcode.Math.BorderButton;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrainManager;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    public void callRun(){

        BaseMode.isField = true;
        isNeedToCall = false;
    }

    BorderButton rotateButton = new BorderButton();

    public void loopRun() {
        robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
        double trigers = - gamepad1.right_trigger*100 + gamepad1.left_trigger*100;
        if(gamepad1.ps){
            trigers = 0;
        }
        robot.driveTrain.setVelocityTarget(
                new Position(-gamepad1.left_stick_y*240,
                        -gamepad1.left_stick_x*240 + trigers,

                        gamepad1.right_stick_x*225)
        );

        if(gamepad1.right_bumper){
            robot.intake.centerEat();
        }
        if(gamepad1.left_bumper){
            robot.intake.wallEat();
        }

        if(rotateButton.get(gamepad1.dpad_left)){
            robot.intake.rotateEater(15);
        }

        if(rotateButton.get(gamepad1.dpad_right)){
            robot.intake.rotateEater(-15);
        }

        robot.intake.setTargeted(gamepad1.dpad_down);



        robot.intake.setLiftManual(gamepad1.ps);
        if(gamepad1.ps){
            if(gamepad1.left_trigger>0.1){
                liftManP = -5;
            }
            if(gamepad1.right_trigger>0.1){

                liftManP = 8 ;
            }
            robot.intake.setManualTarget(liftManP);
        }

        Robot.telemetryPacket.put("x", robot.driveTrain.getPosition().x);
        Robot.telemetryPacket.put("h", robot.driveTrain.getPosition().h);
        Robot.telemetryPacket.put("y", robot.driveTrain.getPosition().y);

        telemetry.update();
        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
    double liftManP = 0;
}
