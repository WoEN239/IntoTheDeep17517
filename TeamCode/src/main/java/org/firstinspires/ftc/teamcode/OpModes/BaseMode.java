package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
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
        Robot.getInstance().init(this);
        robot = Robot.getInstance();
    }

    private   boolean firstInit  = true;
    protected boolean firstStart = true;

    @Override
    public void runOpMode() {
        if (opModeInInit() && firstInit) {
            initOpMode();
            firstInit = false;
        }
        waitForStart();
        while (opModeIsActive()) {
            if (firstStart) {
                Robot.getInstance().timer.reset();
                firstStart = false;
            }
            doing();
            Robot.getInstance().update();
        }
        stop();
    }

    public abstract void doing();

}
