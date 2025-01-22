package org.firstinspires.ftc.teamcode.Robot;

import static org.firstinspires.ftc.teamcode.Devices.ColorSensorFix.fix;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Devices.Battery;
import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor.ColorSensor;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager;
import org.firstinspires.ftc.teamcode.OpenCv.Camera;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

import java.lang.reflect.Field;

/*
  Writing by EgorKhvostikov
*/
public class Robot{
    private static Robot instance;

    public static Robot getInstance(){
        if(instance == null){
            instance = new Robot();
        }
        return instance;
    }

    public LinearOpMode opMode;
    public static TelemetryPacket telemetryPacket = new TelemetryPacket();
    public ElapsedTime timer = new ElapsedTime();
    public static boolean isDebug = false;
    public static double voltage = 12;
    public double getSeconds() {return timer.seconds();}
    public static Team myTeam = Team.BLUE;

    public DriveTrainManager  driveTrain = new DriveTrainManager ();
    public IntakeManager intake     = new IntakeManager();
    public Camera camera = new Camera();

    public void init(LinearOpMode opMode){
        this.opMode = opMode;
        if(!isDebug) {
            DevicePool.init(opMode.hardwareMap);
        }

        driveTrain.init();
        driveTrain.setState       (DriveTrainManager.RobotState.TRAVELING);
        intake.init();

    }


    public void update(){

        Battery.getInstance().update();
        TaskManager.getInstance().updateTasks();

        driveTrain.update();
        intake.update();


        telemetryPacket.put("time",timer.seconds());
        FieldView.updateField();

        FtcDashboard.getInstance().sendTelemetryPacket(telemetryPacket);

        Robot.telemetryPacket = new TelemetryPacket();
    }

}
