package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;
public class Lift implements IModule {
    LiftPosition liftPosition;
    Robot robot;

    public Lift(){
        this.robot = new Robot(robot.opMode);
    }

    @Override
    public void init(Robot robot) {

    }

    public void reset() {
        robot.devicePool.rightLiftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.devicePool.rightLiftMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robot.devicePool.leftLiftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.devicePool.leftLiftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
