package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Eater.Eater;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.EaterChain.EaterGrip.EaterGrip;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain.Scorer.Scorer;
import org.firstinspires.ftc.teamcode.Modules.Intake.ScorerChain.ScorerGrip.ScorerGrip;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOpManual extends BaseMode {
    Position position = new Position();

    Transfer transfer = new Transfer();
    EaterGrip eaterGrip = new EaterGrip();
    Eater eater = new Eater();

    Scorer scorer = new Scorer();
    ScorerGrip scorerGrip = new ScorerGrip();

    LiftManager liftManager = new LiftManager();


    public void callRun(){
        transfer.init();
        eaterGrip.init();
        eater.init();

        liftManager.init();

        scorer.init();
        scorerGrip.init();

        isNeedToCall = false;
    }

    public void loopRun() {
        BaseMode.isField = true;

        robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
        robot.driveTrain.setVelocityTarget(
                new Position(-gamepad1.left_stick_y*240, gamepad1.left_stick_x*240, gamepad1.right_stick_x*100)
        );


        if(gamepad1.dpad_up) {
            eater.up();
        }

        if(gamepad1.dpad_down) {
            eater.down();
        }

        if(gamepad1.dpad_left) {
           scorer.regrip();
        }

        if(gamepad1.dpad_right) {
            scorer.target();
        }

        if(gamepad1.right_bumper){
            liftManager.setTarget(LiftPosition.SWIPE);
        }

        if(gamepad1.left_bumper){
            liftManager.setTarget(LiftPosition.HIGHEST_AXIS);
        }



        liftManager.computePosition();
        liftManager.update();



        Robot.telemetryPacket.put("Voltage ",Robot.voltage);

        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
