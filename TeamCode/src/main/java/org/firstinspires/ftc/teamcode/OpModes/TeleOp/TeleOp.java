package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Math.BorderButton;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotConstant;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    public void callRun(){
        DevicePool.isLiftInit = false;

        robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
        BaseMode.isField = true;
        isNeedToCall = false;
    }

    BorderButton rotateButton = new BorderButton();
    BorderButton moveStateButton = new BorderButton();

    public static boolean isNeedToSlow = false;
    double trigers = TransferPosition.eat;
    public void loopRun() {
        RobotConstant.MAX_MOTOR_TICKS_VEL = 2400;

        double actTrigers = gamepad1.right_trigger * 0.015 - 0.015 * gamepad1.left_trigger;
        trigers += actTrigers;

        if(trigers > TransferPosition.eat){
            trigers = TransferPosition.eat;
        }
        if(trigers < TransferPosition.in){
            trigers = TransferPosition.in;
        }

        Transfer.eatPos = trigers;

        Position targetVel = new Position(-gamepad1.left_stick_y *abs(gamepad1.left_stick_y) *700,
                               -gamepad1.left_stick_x  * abs(gamepad1.left_stick_x) * 1000,
                                 gamepad1.right_stick_x  *600);

        if(isNeedToSlow){
            targetVel.linearMultiply(0.1);
            targetVel.angleMultiply(0.15 );
        }

        robot.driveTrain.setVelocityTarget(targetVel);

        if(gamepad1.right_bumper){
            trigers = TransferPosition.eat;
            robot.intake.centerEat();
        }

        if(gamepad1.left_bumper){
            robot.intake.wallEat();
            isNeedToSlow= false;
        }
        robot.intake.setTargeted(gamepad1.triangle);

        if(rotateButton.get(gamepad1.dpad_left)){
            robot.intake.rotateEater(10);
        }

        if(rotateButton.get(gamepad1.dpad_right)){
            robot.intake.rotateEater(-10);
        }

        if(gamepad1.square){
            robot.intake.swipe();
        }

        if(moveStateButton.get(gamepad1.circle)){
            if(robot.driveTrain.getState() == DriveTrainManager.RobotState.TELE_OP){
                robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP_ANGLE_CONTROL);
                robot.driveTrain.setManualPosition(
                        new Position
                        (0,0,robot.driveTrain.getPosition().h)
                );
            }else{
                robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
            }
        }

        robot.intake.setLiftManualMode(gamepad1.dpad_down||gamepad1.dpad_up);
        if(gamepad1.dpad_up){
            robot.intake.setManualLiftVoltage(12);
        }
        if(gamepad1.dpad_down){
            robot.intake.setManualLiftVoltage(-12);
        }


        Robot.telemetryPacket.put("position robot",robot.driveTrain.getPosition().toString());
        telemetry.update();
        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
