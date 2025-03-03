package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpModes.Auto.WayPointsPool;
import org.firstinspires.ftc.teamcode.Robot.Robot;
/*
  Writing by EgorKhvostikov
*/

public abstract class BaseMode extends LinearOpMode {
    protected Robot robot;
    protected WayPointsPool wayPointsPool;
    public static boolean isCamera = false;
    public static boolean isField = true;
    {
        Robot.isDebug = false;
    }

    protected void initOpMode() {
        Robot.reset();
        Robot.getInstance().init(this);
        robot = Robot.getInstance();
        wayPointsPool = new WayPointsPool(robot);
    }

    private   boolean firstInit  = true;
    protected boolean isNeedToCall = true;
    @Override
    public void runOpMode(){
        if(firstInit) {
            initOpMode();
            firstInit = false;
        }
        initServo();
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

//        System.exit(0);
    }

    public void callRun(){};
    public abstract void loopRun();
    public void initServo(){}
}
