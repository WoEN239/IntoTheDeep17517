package org.firstinspires.ftc.teamcode.Robot.RobotSimulation;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import java.util.function.Supplier;

public class TaskDelay {
    private static Supplier<Boolean> isDone = ()-> false;
    public static boolean isDone() {
        Robot.telemetryPacket.put("resetDelay",timer.seconds());
        Robot.telemetryPacket.put("Delay size",delay);

        return isDone.get();
    }
    static double delay = 0;
    static ElapsedTime timer = new ElapsedTime();
    public static void setDelay(double delay){
        timer.reset();
        TaskDelay.delay = delay;
        isDone = ()-> timer.seconds()>delay;
    }
}
