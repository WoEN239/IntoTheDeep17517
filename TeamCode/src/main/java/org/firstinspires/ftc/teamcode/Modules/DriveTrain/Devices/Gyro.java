package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class Gyro{
    private static Gyro instance;

    public static Gyro getInstance() {
        if(instance == null){
            instance = new Gyro();
        }
        return instance;
    }

    private boolean isUnInit = true;
    private  IMU imu;
    private  double angle;
    private  final ElapsedTime timer = new ElapsedTime();

    public void init(HardwareMap hardwareMap) {
        if(!isUnInit) {
          return;
        }
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot
                (RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        reset();
        isUnInit = false;
    }

    public  void reset() {imu.resetYaw();}

    public  void update() {
        if(timer.seconds()>0.05) {
            angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
            timer.reset();
        }
    }
    public double getAngle() {return angle;}
}
