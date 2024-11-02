package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeState;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;
import org.firstinspires.ftc.teamcode.Robot;

@Autonomous

public class LiftTest extends BaseOpMode {
    @Override
    public void doing() {
        if (pos) {
            robot.liftController.setHighBasket();
            if (robot.liftController.isAtTarget())
                pos = !pos;
        } else {
            robot.liftController.setDownPos();
            if (robot.liftController.isAtTarget())
                pos = !pos;
        }


        telemetry.addData("rightPos", LiftHangingMotors.liftRightMotor.getPosition());
        telemetry.addData("pos", robot.liftListener.getPosition());
        telemetry.addData("leftPos", LiftHangingMotors.liftLeftMotor.getPosition());
        telemetry.addData("isAtTarget", robot.liftController.isAtTarget());

    }

    boolean pos = true;
}
