package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices;

import static com.qualcomm.hardware.rev.RevHubOrientationOnRobot.xyzOrientation;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;

public class Gyro implements IModule {
    private IMU imu;
    private double angle;
    private double speed;
    @Override
    public void init(Robot robot) {
        imu = robot.hardwareMap.get(IMU.class,"imu");

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot
                (RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.    Parameters(orientationOnRobot));
        reset();
    }
    public void reset(){
        imu.resetYaw();
    }

    @Override
    public void update() {
        angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        speed = imu.getRobotAngularVelocity(AngleUnit.DEGREES).xRotationRate;
    }
    public double getAngle(){
        return angle;
    }

    public double getSpeed() {
        return speed;
    }
}
