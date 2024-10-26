package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public abstract class BaseOpMode extends LinearOpMode {
    protected Robot robot;
    public static boolean isCamera = false;

    protected void initOpMode() {
        robot = new Robot(this);
        robot.init();
    }

    private boolean firstInit = true;
    protected boolean firstStart = true;

    @Override
    public void runOpMode() {
        if (opModeInInit() && firstInit) {
            initOpMode();
            firstInit = false;
        }
        waitForStart();
        while (opModeIsActive()) {
            rightStickX = gamepad1.right_stick_x;
            rightStickY = gamepad1.right_stick_y;
            leftStickX = gamepad1.left_stick_x;
            leftStickY = gamepad1.left_stick_y;

            if (firstStart) {
                robot.timer.reset();
                firstStart = false;
            }
            doing();
            robot.updateTasks();
            robot.update();
        }
    }

    protected double rightStickX;
    protected double rightStickY;
    protected double leftStickX;
    protected double leftStickY;

    public abstract void doing();

}
