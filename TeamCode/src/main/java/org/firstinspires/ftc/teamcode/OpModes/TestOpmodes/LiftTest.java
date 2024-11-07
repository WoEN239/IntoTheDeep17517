package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@Autonomous

public class LiftTest extends BaseMode {
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
        telemetry.addData("leftPos", LiftHangingMotors.liftMotor.getPosition());
        telemetry.addData("isAtTarget", robot.liftController.isAtTarget());

    }

    boolean pos = true;
}
