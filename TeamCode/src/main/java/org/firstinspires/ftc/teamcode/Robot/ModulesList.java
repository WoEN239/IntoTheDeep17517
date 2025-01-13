package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionPidController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.VelocityPidController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.Gyro;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.FieldSensors;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener.PositionListener;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PurePursuit;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.VelocityListener.VelocityListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.OpenCv.Camera;

/*
  Writing by EgorKhvostikov
*/
public class ModulesList {

    public VelocityPidController velocityPidController = new VelocityPidController();
    public PositionPidController positionPidController = new PositionPidController();

    public PositionListener positionListener = new PositionListener();
    public VelocityListener velocityListener = new VelocityListener();
    public Gyro imu = new Gyro();
    public FieldSensors fieldSensors = new FieldSensors();

    public Camera camera = new Camera();
    public PurePursuit purePursuit = new PurePursuit();

    public Grabber grabber = new Grabber();
    public LiftListener liftListener = new LiftListener();
    public LiftController liftController = new LiftController();



}