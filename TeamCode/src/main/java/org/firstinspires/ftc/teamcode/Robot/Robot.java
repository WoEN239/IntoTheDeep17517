package org.firstinspires.ftc.teamcode.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Devices.Battery;
import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeStateMachine;

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
    public static Telemetry telemetry = FtcDashboard.getInstance().getTelemetry();
    public ElapsedTime timer = new ElapsedTime();
    public static boolean isDebug = false;
    public static double voltage = 12;
    public double getSeconds() {return timer.seconds();}
    public static Team myTeam = Team.BLUE;

    public DriveTrainManager  driveTrain = new DriveTrainManager ();
    public IntakeStateMachine intake     = new IntakeStateMachine();

    public void init(LinearOpMode opMode){
        this.opMode = opMode;
        if(!isDebug) {
            DevicePool.init(opMode.hardwareMap);
            intake.init();
        }
        driveTrain.init();
    }

    public void update(){
        Battery.getInstance().update();
        TaskManager.getInstance().updateTasks();
        driveTrain.update();

        telemetry.addData("time",timer.seconds());
        telemetry.update();
    }

}
