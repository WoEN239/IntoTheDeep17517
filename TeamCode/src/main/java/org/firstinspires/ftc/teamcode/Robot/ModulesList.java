package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.Devices.Battery;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.VelocityController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.FieldSensors.FieldSensors;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer.PositionViewer;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PurePursuit;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RoadRunner;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer.VelocityViewer;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.IModule;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Grabber;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.OpenCv.Camera;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

/*
  Writing by EgorKhvostikov
*/
public class ModulesList {

    public VelocityController velocityController = new VelocityController();
    public PositionController positionController = new PositionController();

    public PositionViewer positionViewer = new PositionViewer();
    public VelocityViewer velocityViewer = new VelocityViewer();
    public Gyro imu = new Gyro();
    public FieldSensors fieldSensors = new FieldSensors();

    public RoadRunner roadRunner = new RoadRunner();
    public Camera camera = new Camera();
    public PurePursuit purePursuit = new PurePursuit();

    public FieldView fieldView = new FieldView();

    public Grabber grabber = new Grabber();
    public LiftListener liftListener = new LiftListener();
    public LiftController liftController = new LiftController();


    public Battery battery = new Battery();
    protected IModule[] modules = new IModule[]{
            roadRunner,purePursuit, imu, velocityViewer, positionViewer,
            positionController,velocityController, liftListener, grabber, liftController,
            fieldSensors,battery, fieldView
    };

}