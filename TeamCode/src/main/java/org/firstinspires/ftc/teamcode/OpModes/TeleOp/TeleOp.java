package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static java.lang.Math.abs;

import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Devices.IntakeDevices;
import org.firstinspires.ftc.teamcode.Math.BorderButton;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Config.TransferPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

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
    double trigers = TransferPosition.normal;
    public void loopRun() {

        double actTrigers = gamepad1.right_trigger*0.115 - 0.115 * gamepad1.left_trigger;
        trigers += actTrigers;

        if(trigers > TransferPosition.eat ){
            trigers = TransferPosition.eat;
        }
        if(trigers < TransferPosition.normal ){
            trigers = TransferPosition.normal;
        }

        Position targetVel = new Position(-gamepad1.left_stick_y *abs(gamepad1.left_stick_y) *700,
                             -gamepad1.left_stick_x  * abs(gamepad1.left_stick_x)  *700, //+ trigers,
                                 gamepad1.right_stick_x  *700);
        if(isNeedToSlow){
            targetVel.linearMultiply(0.25);
            targetVel.angleMultiply(0.5);
        }

        robot.driveTrain.setVelocityTarget(targetVel);

        if(gamepad1.right_bumper){
            robot.intake.centerEat();
        }
        Transfer.eatPos = trigers;

        if(gamepad1.left_bumper){
            robot.intake.wallEat();
        }
        robot.intake.setTargeted(gamepad1.triangle);

        if(rotateButton.get(gamepad1.dpad_left)){
            robot.intake.rotateEater(15);
        }

        if(rotateButton.get(gamepad1.dpad_right)){
            robot.intake.rotateEater(-15);
        }

        if(gamepad1.square){
            robot.intake.swipe();
        }

        if(moveStateButton.get(gamepad1.circle)){
            if(robot.driveTrain.getState() == DriveTrainManager.RobotState.TELE_OP){
                robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP_ANGLE_CONTROL);
                robot.driveTrain.setManualPosition(
                        new Position(
                        0,0,robot.driveTrain.getPosition().h)
                );
            }else{
                robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
            }
        }

        robot.intake.setLiftManual(gamepad1.ps);


        telemetry.update();
        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
    double liftManP = 0;
}
