package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

public class Odometrs {
    HardwareMap hardwareMap;

    private HashMap<DcMotorEx,Integer> directionMap = new HashMap();
    public DcMotorEx odometrLeftY;
    public DcMotorEx odometrRightY;
    public DcMotorEx odometrX;

    public Odometrs(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        odometrLeftY = hardwareMap.get(DcMotorEx.class, "fake");
        odometrRightY = hardwareMap.get(DcMotorEx.class, "fake");
        odometrX = hardwareMap.get(DcMotorEx.class, "fake");
    }

    public void reset(){
        odometrX.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometrLeftY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometrRightY.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        odometrX.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        odometrLeftY.setMode(DcMotor.RunMode .RUN_WITHOUT_ENCODER);
        odometrRightY.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
