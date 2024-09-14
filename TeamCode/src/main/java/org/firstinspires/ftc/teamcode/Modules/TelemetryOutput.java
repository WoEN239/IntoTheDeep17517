package org.firstinspires.ftc.teamcode.Modules;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Robot;

@Config

public class TelemetryOutput {

    Robot robot;

    public static boolean lift = false;
    public static boolean driveTrain = false;
    public static boolean odometry = false;
    public static boolean grabber = false;
    public static boolean leftFrontDrivePos = false;
    public static boolean leftBackDrivePos = false;
    public static boolean rightFrontDrivePos = false;
    public static boolean rightBackDrivePos = false;

    Telemetry telemetry;

    public TelemetryOutput(Robot robot){
        this.robot = robot;
        robot.opMode.telemetry = new MultipleTelemetry(robot.opMode.telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry = robot.opMode.telemetry;
    }

    public void update(){
        if(lift){
        }
        if(driveTrain){
            if(leftBackDrivePos)
                telemetry.addData("leftBackDrivePos", robot.devicePool.leftBackDrive.getVel());
            else{
                if(leftFrontDrivePos)
                    telemetry.addData("leftFrontDrivePos", robot.devicePool.leftForwardDrive.getVel());
                else{
                    if(rightBackDrivePos)
                        telemetry.addData("rightBackDrivePos", robot.devicePool.rightBackDrive.getVel());
                    else{
                        if(rightFrontDrivePos)
                            telemetry.addData("rightFrontDrivePos", robot.devicePool.rightForwardDrive.getVel());
                        else{
                            telemetry.addData("leftBackDrivePos", robot.devicePool.leftBackDrive.getVel());
                            telemetry.addData("leftFrontDrivePos", robot.devicePool.leftForwardDrive.getVel());
                            telemetry.addData("rightBackDrivePos", robot.devicePool.rightBackDrive.getVel());
                            telemetry.addData("rightFrontDrivePos", robot.devicePool.rightForwardDrive.getVel());
                        }
                    }
                }
            }

        }
        if(grabber){

        }
        telemetry.update();
    }



}
