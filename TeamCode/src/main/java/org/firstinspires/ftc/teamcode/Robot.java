package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Devices.DevicePool;
import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Modules.ModulesList;

import java.util.ArrayList;

public class Robot extends ModulesList {
    LinearOpMode opMode;
    private ArrayList<Task> taskQueue = new ArrayList<>();
    public DevicePool devicePool;
    public HardwareMap hardwareMap;

    public Robot(LinearOpMode opMode){
        this.opMode = opMode;
        this.hardwareMap = opMode.hardwareMap;
        devicePool = new DevicePool(this.hardwareMap);
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

}
