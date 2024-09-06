package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

public abstract class BaseOpMode extends LinearOpMode {
    Robot robot;
    protected void initOpMode(){
        robot = new Robot(this);
        robot.init();
    }
    boolean first = true;
    @Override
    public void runOpMode(){
        if (opModeInInit() && first){
          initOpMode();
          first = false;
        }
        waitForStart();
        while (opModeIsActive()){
            doing();
            robot.update();
        }
        rightStickX = gamepad1.right_stick_x;
        rightStickY = gamepad1.right_stick_y;
        leftStickX  = gamepad1.left_stick_x;
        leftStickY  = gamepad1.left_stick_y;
    }
    protected  double rightStickX;
    protected  double rightStickY;
    protected  double leftStickX;
    protected  double leftStickY;

    public abstract void doing();

}
