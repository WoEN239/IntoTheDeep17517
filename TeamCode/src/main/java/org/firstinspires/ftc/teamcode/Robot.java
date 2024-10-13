package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.OpModes.BaseOpMode;
import org.firstinspires.ftc.teamcode.OpenCv.myPipeLine;
import java.util.ArrayList;

public class Robot extends ModulesList {
    public  LinearOpMode opMode;
    public DevicePool devicePool;
    public HardwareMap hardwareMap;
    public static Telemetry telemetry = FtcDashboard.getInstance().getTelemetry();

    private final ArrayList<Task> taskQueue = new ArrayList<>();
    public ElapsedTime timer = new ElapsedTime();

    public myPipeLine pipeLine = new myPipeLine();

    public Robot(LinearOpMode opMode){
        this.opMode = opMode;
        Robot.telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(),opMode.telemetry);
        this.hardwareMap = opMode.hardwareMap;
        devicePool = new DevicePool(hardwareMap);
        if(BaseOpMode.isCamera){
            camera.init(this);
        }
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
            if(i instanceof Listener){
                i.read();
            }
        }
        for (IModule i:modules
        ) {
            if(i instanceof Controller){
                i.update();
            }
        }
        telemetry.update();
    }
    public void updateTasks(){
        taskQueue.forEach(Task::run);
    }

    public double getSeconds(){
        return timer.seconds();
    }

    public enum TEAM
    {
        BLUE,RED
    }
    public static TEAM myTEAM = TEAM.BLUE;
}
