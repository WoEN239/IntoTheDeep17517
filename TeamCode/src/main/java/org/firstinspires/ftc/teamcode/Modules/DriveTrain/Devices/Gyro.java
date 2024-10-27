package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

import java.lang.annotation.ElementType;

/**
 * Writing by EgorKhvostikov
 */

public class Gyro implements Listener {
    private IMU imu;
    private double angle;
    private double speed;
    private final ElapsedTime timer = new ElapsedTime();
    @Override
    public void init(Robot robot) {
        imu = robot.hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot
                (RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        reset();
    }

    public void reset() {
        imu.resetYaw();
    }
    private boolean isNewValue = true;
    @Override
    public void read() {
        if(timer.seconds()>0.05) {
            angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            speed = imu.getRobotAngularVelocity(AngleUnit.RADIANS).xRotationRate;
            timer.reset();
            isNewValue = true;
        }else{
            isNewValue = false;
        }
    }
    public boolean isNewValue(){
        return isNewValue;
    }
    public double getAngle() {
        return angle;
    }

    public double getSpeed() {
        return speed;
    }
}
