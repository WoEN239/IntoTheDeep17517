package org.firstinspires.ftc.teamcode.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.Battery;
import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.IntakeManager;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.TaskManager;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

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
    public static void reset(){
        instance = null;
    }

    public static TelemetryPacket telemetryPacket = new TelemetryPacket();
    public static boolean isDebug = false;
    public static double voltage = 12;
    public static Team myTeam = Team.BLUE;

    private final ElapsedTime timer  = new ElapsedTime();
    public double getSeconds() {return timer.seconds();}

    public DriveTrainManager driveTrain = new DriveTrainManager ();
    public IntakeManager intake  = new IntakeManager();
    public FieldView fieldView  = new FieldView();

    public LinearOpMode opMode;

    public void init(LinearOpMode opMode){
        this.opMode = opMode;

        if(!isDebug) {
            DevicePool.init(opMode.hardwareMap);
        }

        driveTrain.init();
        driveTrain.setState(DriveTrainManager.RobotState.TRAVELING);
        intake.init();

        timer.reset();
    }


    public void update(){
        Battery.getInstance().update();
        TaskManager.getInstance().updateTasks();

        driveTrain.update();
        intake.update();

        telemetryPacket.put("time",timer.seconds());
        fieldView.updateField();

        FtcDashboard.getInstance().sendTelemetryPacket(telemetryPacket);
        Robot.telemetryPacket = new TelemetryPacket();
    }

}
