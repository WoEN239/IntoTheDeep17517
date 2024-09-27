package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpenCv.myPipeLine;
import org.firstinspires.ftc.teamcode.Robot;

public abstract class BaseOpMode extends LinearOpMode {
    protected Robot robot;
    protected boolean isCamera = false;

    protected void initOpMode(){
        robot = new Robot(this);
        robot.init();
        if(isCamera){
            robot.camera.startStream();
        }
    }
    private boolean firstInit = true;
    protected boolean firstStart = true;
    @Override
    public void runOpMode(){
        if (opModeInInit() && firstInit){
          initOpMode();
          firstInit = false;
        }
        waitForStart();
        while (opModeIsActive()){
            if(firstStart){
                robot.timer.reset();
                firstStart = false;
            }
            doing();
            robot.updateTasks();
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
