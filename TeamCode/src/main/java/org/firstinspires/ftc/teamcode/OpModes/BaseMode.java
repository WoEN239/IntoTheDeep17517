package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Robot;

/*
  Writing by EgorKhvostikov
*/

public abstract class BaseMode extends LinearOpMode {
    protected Robot robot;
    public static boolean isCamera = false;

    public final GamepadConfig gamepad = new GamepadConfig(this.gamepad1);

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
            gamepad.read();
            if (firstStart) {
                robot.timer.reset();
                firstStart = false;
            }
            doing();
            robot.updateTasks();
            robot.update();
        }
    }


    public abstract void doing();

}
