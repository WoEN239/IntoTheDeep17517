package org.firstinspires.ftc.teamcode.Devices;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;

/*
 *Writing by @MrFrosty1234
 */
@Config
public class DriveTrainMotors {
    public static Motor rightForwardDrive = new Motor();
    public static Motor rightBackDrive = new Motor();
    public static Motor leftForwardDrive = new Motor();
    public static Motor leftBackDrive = new Motor();
    public static Motor yOdometer = new Motor();
    public static Motor rightOdometer = new Motor();
    public static Motor leftOdometer = new Motor();

    public static void init(HardwareMap hardwareMap) {

        leftBackDrive    .init("motorLB", hardwareMap);
        rightForwardDrive.init("motorRF", hardwareMap);
        rightBackDrive   .init("motorRB", hardwareMap);
        leftForwardDrive .init("motorLF", hardwareMap);

        yOdometer.init("yOdometer", hardwareMap);
        leftOdometer.init("motorLB", hardwareMap);
        rightOdometer.init("rightOdometer", hardwareMap);
        reset();
        initPid();
    }

    public static void reset(){
        leftBackDrive    .setDir(MotorDirection.BACK);
        leftForwardDrive .setDir(MotorDirection.BACK);
        rightBackDrive   .setDir(MotorDirection.FORWARD);
        rightForwardDrive.setDir(MotorDirection.FORWARD);

        rightOdometer.setDir(MotorDirection.FORWARD);
        leftOdometer .setDir(MotorDirection.BACK);

        leftBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftForwardDrive.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightForwardDrive.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.dev.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static void initPid(){
        leftBackDrive    .pidStatusF .copyFrom(new PidStatus(0,0,0,-0.5326,0.0053,8e-07,2e-10,maxI,zeroBorder));
        leftForwardDrive .pidStatusF .copyFrom(new PidStatus(kp,ki,kd,-0.5376,0.0052,5e-07,1e-10,maxI,zeroBorder));
        rightBackDrive   .pidStatusF .copyFrom(new PidStatus(kp,ki,kd,0.7869,0.0056,-9e-07,2e-10,maxI,zeroBorder));
        rightForwardDrive.pidStatusF .copyFrom(new PidStatus(kp,ki,kd,0.7843,0.0052,-6e-07,1e-10,maxI,zeroBorder));

        leftBackDrive    .pidStatusB .copyFrom(new PidStatus(0,0,0,0.53441305077374747889,0.00564107079947058904,
                                                          -0.00000101446464637286,0.00000000023490347045,maxI,zeroBorder));
        leftForwardDrive .pidStatusB .copyFrom(new PidStatus(kp,ki,kd,0.7582,0.0052,-7e-07,2e-10,maxI,zeroBorder));
        rightBackDrive   .pidStatusB .copyFrom(new PidStatus(kp,ki,kd,-0.8063,0.0052,6e-07,2e-10,maxI,zeroBorder));
        rightForwardDrive.pidStatusB .copyFrom(new PidStatus(kp,ki,kd,-1.0408,0.0054,6e-07,1e-10,maxI,zeroBorder));

    }
    public static double ki         = 0.001;
    public static double kp         = 0.0035;
    public static double kd         = 0;
    public static double maxI       = 4;
    public static double zeroBorder = 3;

}
