package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@Autonomous

public class LiftTest extends BaseMode {
    @Override
    public void loopRun() {
        LiftHangingMotors.liftRightMotor.setPower(1);
        LiftHangingMotors.liftLeftMotor.setPower(1);
    }

    boolean pos = true;
}
