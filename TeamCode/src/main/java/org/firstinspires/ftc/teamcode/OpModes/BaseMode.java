package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public abstract class BaseMode extends LinearOpMode {
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

            dropSamples = gamepad1.left_bumper;
            grabSamples = gamepad1.right_bumper;

            highBasket = gamepad1.dpad_up;
            lowBasket = gamepad1.dpad_down;

            toDown = gamepad1.square;

            toLowAxis = gamepad1.cross;
            toHighAxis = gamepad1.triangle;


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

    protected boolean dropSamples;
    protected boolean grabSamples;

    protected boolean highBasket;
    protected boolean lowBasket;

    protected boolean toDown;

    protected boolean toLowAxis;
    protected boolean toHighAxis;



    public abstract void doing();

}
