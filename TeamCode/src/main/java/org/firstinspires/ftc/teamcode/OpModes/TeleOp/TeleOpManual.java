package org.firstinspires.ftc.teamcode.OpModes.TeleOp;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Brush.Brush;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorSensor;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.InnerTransfer.InnerTransfer;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOpManual extends BaseMode {
    Position position = new Position();
    Brush  brush = new Brush();
    Transfer transfer = new Transfer();
    ColorSensor colorSensor = new ColorSensor();
    InnerTransfer innerTransfer = new InnerTransfer();
    LiftManager liftManager = new LiftManager();

    public void callRun(){
        transfer.init();
        brush.init();
        colorSensor.init();
        innerTransfer.init();
        liftManager.init();
        isNeedToCall = false;
    }

    public void loopRun() {
        BaseMode.isField = true;

        robot.driveTrain.setState(DriveTrainManager.RobotState.TELEOP);
        robot.driveTrain.setVelocityTarget(
                new Position(-gamepad1.left_stick_y*240, gamepad1.left_stick_x*240, gamepad1.right_stick_x*100)
        );


        Transfer.eatPos = gamepad1.left_trigger;
        //transfer.eat();
        colorSensor.update();

        if(gamepad1.dpad_up) {
            brush.up();
        }
        if(gamepad1.dpad_down) {
            brush.down();
        }

        if(gamepad1.dpad_right) {
            innerTransfer.in();
        }
        if(gamepad1.dpad_left) {
            innerTransfer.out();
        }

        if(gamepad1.right_bumper){
            liftManager.setTarget(LiftPosition.LOWEST_BASKET);
        }

        if(gamepad1.left_bumper){
            liftManager.setTarget(LiftPosition.HIGHEST_AXIS);
        }



        liftManager.computePosition();
        liftManager.update();


        Robot.telemetryPacket.put("sample Color", colorSensor.getColor().toString());

        Robot.telemetryPacket.put("Voltage ",Robot.voltage);

        Robot.telemetryPacket.put("x", robot.driveTrain.getPosition().x);
        Robot.telemetryPacket.put("h", robot.driveTrain.getPosition().h);
        Robot.telemetryPacket.put("y", robot.driveTrain.getPosition().y);

        robot.fieldView.position = robot.driveTrain.getPosition();
        robot.fieldView.circle   = robot.driveTrain.getPidTarget();
    }
}
