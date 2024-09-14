package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Events.Task;

@Autonomous
public class Auto extends BaseOpMode{
    boolean first = true;
    @Override
    public void doing(){
        robot.addToQueue(new Task(
                            () -> robot.getSeconds()>5,
                            () -> robot.getSeconds()>20,
                            new Runnable[]{
                                    ()->robot.devicePool.leftBackDrive.setPower(0.2),
                            },
                            new Runnable[]{
                                    ()->robot.devicePool.leftBackDrive.setPower(0)
                            }
                    )
            );
        FtcDashboard.getInstance().getTelemetry().addData("g",robot.getSeconds());
        FtcDashboard.getInstance().getTelemetry().update();
        robot.update();
        robot.updateTasks();
    }

}
