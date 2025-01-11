package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Task;

/**
 * Writing by EgorKhvostikov and @MrFrosty1234
 */

@Autonomous
public class Auto extends BaseMode {
    @Override
    public void doing() {
        robot.addToQueue(new Task(
                        () -> robot.getSeconds() > 5,
                        () -> robot.getSeconds() > 9,
                        new Runnable[]{},
                        new Runnable[]{
                                () -> robot.velocityController.moveGlobal(new Position(1200, 0,0))
                        },

                        new Runnable[]{
                                () -> robot.velocityController.moveGlobal(new Position(0, 0,0))
                        }
                )
        );

        robot.update();
        robot.updateTasks();
    }
}
