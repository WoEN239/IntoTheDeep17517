package org.firstinspires.ftc.teamcode.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.IModule;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeStateMachine;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.OpModes.StateMachine.AutonomusStateMachine;
import org.firstinspires.ftc.teamcode.OpenCv.SimplesAndTagsDetectPipeline;

import java.util.ArrayList;
import java.util.Arrays;

/*
  Writing by EgorKhvostikov
*/
public class Robot extends ModulesList {
    public LinearOpMode opMode;
    public HardwareMap hardwareMap;
    public SimplesAndTagsDetectPipeline pipeLine = new SimplesAndTagsDetectPipeline();

    public IntakeStateMachine intake = new IntakeStateMachine();
    public AutonomusStateMachine autonomusStateMachine = new AutonomusStateMachine();

    public static Telemetry telemetry = FtcDashboard.getInstance().getTelemetry();

    public ElapsedTime timer = new ElapsedTime();
    private final ArrayList<Task> taskQueue = new ArrayList<>();
    private final ArrayList<PurePursuitTask> purePursuitTasks = new ArrayList<>();

    public static boolean isDebug = false;
    public static double voltage = 12;

    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;

        Robot.telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), opMode.telemetry);
    }

    public void addToQueue(Task task) {
        taskQueue.add(task);
    }

    public void init() {
        DevicePool.init(hardwareMap);

        if (BaseMode.isCamera) {
        //    camera.init(this);
        }

        for (IModule i : modules
        ) {
            i.init(this);
        }
        intake.init(this);
        //autonomusStateMachine.init(this);

    }
    public void initSimulation(){
        for (IModule i: simulationModules
        ) {
            i.init(this);
        }
    }
    public void updateSimulation(){
        for (IModule i: simulationModules
        ) {
            i.read();
            i.update();
        }
        telemetry.update();
    }

    public void update() {
        for (IModule i : modules
        )
        {
            i.read();
        }
        //autonomusStateMachine.update();
        intake.update();
        for (IModule i : modules
        )
        {
            i.update();
        }
        telemetry.update();
    }

    public void updateTasks() {
        taskQueue.forEach(Task::run);
    }

    public void updatePPTasks() {
        Robot.telemetry.addData("taskInQueue", purePursuitTasks.toString());

        for (PurePursuitTask i : purePursuitTasks) {
            if(!i.isRunOnce) {
                i.run();
            }
            if(i.isDone()){
                i.end();
            }
        }
        purePursuitTasks.removeIf(PurePursuitTask::isDone);
    }
    public PurePursuitTask getPPTask(int index){
        return purePursuitTasks.get(index);
    }
    public void addPPTask(PurePursuitTask t){
        purePursuitTasks.add(t);
    }
    public double getSeconds() {
        return timer.seconds();
    }

    public static Team myTeam = Team.BLUE;
}