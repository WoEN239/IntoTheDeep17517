package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Odometry;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Modules.ModulesList;
import org.firstinspires.ftc.teamcode.Modules.TelemetryOutput;

import java.security.PublicKey;
import java.util.ArrayList;

public class Robot extends ModulesList {
    public LinearOpMode opMode;
    private final ArrayList<Task> taskQueue = new ArrayList<>();
    public DevicePool devicePool;
    public Odometry odometry;
    public HardwareMap hardwareMap;
    public ElapsedTime timer = new ElapsedTime();
    public TelemetryOutput telemetryOutput;
    public double getSeconds(){
        return timer.seconds();
    }
    public Robot(LinearOpMode opMode){
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;
        devicePool = new DevicePool(this);
        telemetryOutput = new TelemetryOutput(this);
        this.odometry = new Odometry();
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
        //timer.reset();
    }
    public void update(){
        for (IModule i:modules
        ) {
            i.update();
            telemetryOutput.update();
        }
    }
    public void updateTasks(){
        taskQueue.forEach(Task::run);
    }
}
