package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Modules.Intake.GrabberStateMachine;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;
import org.firstinspires.ftc.teamcode.OpenCv.SimplesAndTagsDetectPipeline;

import java.util.ArrayList;

public class Robot extends ModulesList {
    public LinearOpMode opMode;
    public DevicePool devicePool;
    public HardwareMap hardwareMap;
    public SimplesAndTagsDetectPipeline pipeLine = new SimplesAndTagsDetectPipeline();
    public GrabberStateMachine grabberStateMachine = new GrabberStateMachine();


    public static Telemetry telemetry = FtcDashboard.getInstance().getTelemetry();
    public ElapsedTime timer = new ElapsedTime();
    private final ArrayList<Task> taskQueue = new ArrayList<>();

    public Robot(LinearOpMode opMode) {
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;
        this.devicePool = new DevicePool(hardwareMap);
        Robot.telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), opMode.telemetry);

        grabberStateMachine.init(this);
        if (BaseOpMode.isCamera) {
            camera.init(this);
        }
    }

    public void addToQueue(Task task) {
        taskQueue.add(task);
    }

    public Task getTask() {
        return taskQueue.get(0);
    }

    public void init() {
        for (IModule i : modules
        ) {
            i.init(this);
        }
    }

    public void update() {
        for (IModule i : modules
        )
        {
            i.read();
        }
        for (IModule i : modules
        )
        {
            i.update();
        }
        grabberStateMachine.update();
        telemetry.update();
    }

    public void updateTasks() {
        taskQueue.forEach(Task::run);
    }

    public double getSeconds() {
        return timer.seconds();
    }


    public static TEAM myTeam = TEAM.BLUE;
}
