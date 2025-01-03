package org.firstinspires.ftc.teamcode.Robot;
import java.util.ArrayList;

public class TaskManager {
    public static TaskManager instance = new TaskManager();
    public static TaskManager getInstance() {return instance;}

    private final ArrayList<PurePursuitTask> tasks = new ArrayList<>();
    public void addTask(PurePursuitTask t ){tasks.add(t);}

    public void updateTasks() {
        Robot.telemetry.addData("taskInQueue", tasks.toString());

        for (PurePursuitTask i : tasks) {
            if(!i.isRunOnce) {
                i.run();
            }
            if(i.isDone()){
                i.end();
            }
        }
        tasks.removeIf(PurePursuitTask::isDone);
    }

}
