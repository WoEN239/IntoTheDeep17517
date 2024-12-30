package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

/*
  Writing by EgorKhvostikov
*/

public abstract class BaseMode extends LinearOpMode {
    protected Robot robot;
    public static boolean isCamera = false;
    public static boolean isField = true;
    {
        Robot.isDebug = false;
    }
    protected void initOpMode() {
        robot = new Robot(this);
        robot.init();
    }

    private boolean firstInit = true;
    protected boolean firstStart = true;
    protected boolean isDone = false;

    @Override
    public void runOpMode() {
        if (opModeInInit() && firstInit) {
            initOpMode();
            firstInit = false;
        }
        waitForStart();
        while (opModeIsActive() && !isDone) {
            read();
            if (firstStart) {
                robot.timer.reset();
                firstStart = false;
            }
            doing();
            robot.updateTasks();
            robot.updatePPTasks();
            robot.update();

        }
        stop();
    }


    public abstract void doing();

    protected double rightStickX = 0;
    protected double rightStickY = 0;
    protected double leftStickX = 0;
    protected double leftStickY = 0;

    protected boolean dropSamples = false;
    protected boolean grabSamples = false;

    protected boolean highBasket = false;
    protected boolean lowBasket = false;

    protected boolean toDown = false;

    protected boolean toLowAxis = false;
    protected boolean toHighAxis = false;

    protected boolean waitDown = false;
    protected boolean waitUp = false;
    protected boolean waitEat = false;

    public void read(){

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

        waitDown = gamepad1.a;
        waitUp   = gamepad1.b;
        waitEat  = gamepad1.x;
    }
}
