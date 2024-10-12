package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

/**
 * Writing by @MrFrosty1234
 */

public class Odometrs {
    HardwareMap hardwareMap;

    private HashMap<DcMotorEx,Integer> directionMap = new HashMap();
    public Motor odometrLeftY;
    public Motor odometrRightY;
    public Motor odometrX;

    public Odometrs(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        odometrLeftY = hardwareMap.get(Motor.class, "fake");
        odometrRightY = hardwareMap.get(Motor.class, "fake");
        odometrX = hardwareMap.get(Motor.class, "fake");
    }

    public void reset(){
        odometrX.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometrLeftY.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometrRightY.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometrX.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        odometrLeftY.dev.setMode(DcMotor.RunMode .RUN_WITHOUT_ENCODER);
        odometrRightY.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public double getVelocity(DcMotorEx odometer){
        return odometer.getVelocity()*directionMap.get(odometer);
    }
    public double getPosition(DcMotorEx odometer){
        return odometer.getCurrentPosition()*directionMap.get(odometer);
    }
    public void setDirection(DcMotorEx odometer,int direction){
        directionMap.put(odometer, direction);
    }
}
