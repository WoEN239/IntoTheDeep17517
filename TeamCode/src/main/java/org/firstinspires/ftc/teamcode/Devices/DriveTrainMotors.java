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

        yOdometer.init("fakeMotor", hardwareMap);
        leftOdometer.init("fakeMotor", hardwareMap);
        rightOdometer.init("fakeMotor", hardwareMap);
        reset();
        //initPid();
    }

    public static void reset(){
        resetEncoders();

        leftBackDrive    .setDir(MOTOR_DIRECTION.BACK);
        leftForwardDrive .setDir(MOTOR_DIRECTION.BACK);
        rightBackDrive   .setDir(MOTOR_DIRECTION.FORWARD);
        rightForwardDrive.setDir(MOTOR_DIRECTION.FORWARD);

        leftBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
    private void initPid(){
        leftBackDrive.pidF    =new Pid(new PidStatus(0,0,0,0.005,0,0));
        leftBackDrive.pidF    =new Pid(new PidStatus(0,0,0,0.005,0,0));
        leftBackDrive.pidF    =new Pid(new PidStatus(0,0,0,0.005,0,0));
        leftBackDrive.pidF    =new Pid(new PidStatus(0,0,0,0.005,0,0));
        leftBackDrive.pidB    =new Pid(new PidStatus(0,0,0,0.005,0,0));
        leftForwardDrive.pidB =new Pid(new PidStatus(0,0,0,0.005,0,0));
        rightBackDrive.pidB   =new Pid(new PidStatus(0,0,0,0.005,0,0));
        rightForwardDrive.pidB=new Pid(new PidStatus(0,0,0,0.005,0,0));

    }
    public static void resetEncoders() {
        leftBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightForwardDrive.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void initMode() {
        leftBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightForwardDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
