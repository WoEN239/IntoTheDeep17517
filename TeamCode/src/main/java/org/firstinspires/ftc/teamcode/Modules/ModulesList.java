package org.firstinspires.ftc.teamcode.Modules;


import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Gyro;

public class ModulesList {
    public Lift lift = new Lift();
    public DriveTrain driveTrain= new DriveTrain();
    public Gyro imu = new Gyro();
    protected IModule modules[] = new IModule[]{
            lift,driveTrain,imu
    };

}