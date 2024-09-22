package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.VelocityController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer.PositionViewer;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer.VelocityViewer;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Modules.Lift;

public class ModulesList {
    public Lift lift = new Lift();
    public VelocityController velocityController = new VelocityController();
    public PositionController positionController = new PositionController();
    public PositionViewer positionViewer = new PositionViewer();
    public VelocityViewer velocityViewer = new VelocityViewer();
    public Gyro imu = new Gyro();
    protected IModule modules[] = new IModule[]{
            lift, velocityController,imu,velocityViewer,positionViewer,positionController
    };

}