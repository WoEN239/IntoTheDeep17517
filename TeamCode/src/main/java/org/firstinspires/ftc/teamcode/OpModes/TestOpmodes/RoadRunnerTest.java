package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@Autonomous(group = "Test")
public class RoadRunnerTest extends BaseMode {
    @Override
    public void doing() {
        robot.addToQueue(new Task(
                () -> true,
                () -> false,
                new Runnable[]{
                        () -> robot.roadRunner.moveToTrajectory(
                                robot.roadRunner.builder()
                                        .lineToX(10)
                                        .build()
                        )
                },
                new Runnable[]{
                        () -> robot.velocityController.move(new Position())
                }
        ));
    }
}
