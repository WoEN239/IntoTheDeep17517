package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@Autonomous

public class LiftTest extends LinearOpMode {
    LiftManager liftManager = new LiftManager();
    boolean pos = true;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while (opModeIsActive()) {
            if (pos) {
                liftManager.setTarget(LiftPosition.HIGHEST_AXIS);
                if (liftManager.isDone())
                    pos = false;
            }
            if(!pos){
                liftManager.setTarget(LiftPosition.IN_POSITION);
                if (liftManager.isDone())
                    pos = true;
            }
        }
    }
}
