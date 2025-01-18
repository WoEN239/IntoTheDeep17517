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
    protected boolean isNeedToCall = true;
    @Override
    public void runOpMode(){
        if(firstInit) {
            initOpMode();
            Robot.getInstance().timer.reset();
            firstInit = false;
        }
        waitForStart();
        while (opModeIsActive()){
            if(isNeedToCall){
                callRun();
            }
            loopRun();
            robot.update();
        }

        firstInit = true;
        isNeedToCall = true;
        //  System.exit(0);
    }

    public void callRun(){};
    public abstract void loopRun();

}
