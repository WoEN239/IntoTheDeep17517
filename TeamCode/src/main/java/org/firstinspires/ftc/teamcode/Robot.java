package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RoadRunner;
import org.firstinspires.ftc.teamcode.Modules.IModule;

import java.util.ArrayList;

public class Robot extends ModulesList {
    public  LinearOpMode opMode;
    public DevicePool devicePool;
    public HardwareMap hardwareMap;
    public static Telemetry telemetry = FtcDashboard.getInstance().getTelemetry();

    private final ArrayList<Task> taskQueue = new ArrayList<>();
    public ElapsedTime timer = new ElapsedTime();

    public RoadRunner roadRunner;

    public Robot(LinearOpMode opMode){
        this.opMode = opMode;
        Robot.telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(),opMode.telemetry);
        this.hardwareMap = opMode.hardwareMap;
        devicePool = new DevicePool(this);
        roadRunner = new RoadRunner(this);
    }
    public void addToQueue(Task task){
        taskQueue.add(task);
    }
    public Task getTask(){
        return taskQueue.get(0);
    }
    public void init(){
        for (IModule i:modules
        ) {
            i.init(this);
        }
    }
    public void update(){
        for (IModule i:modules
        ) {
            i.update();
        }
    }
    public void updateTasks(){
        taskQueue.forEach(Task::run);
    }

    public double getSeconds(){
        return timer.seconds();
    }

}
