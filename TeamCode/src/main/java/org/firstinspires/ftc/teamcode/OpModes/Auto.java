package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Events.Task;

/**
 * Writing by EgorKhvostikov and @MrFrosty1234
 */

@Autonomous
public class Auto extends BaseOpMode {
    boolean first = true;

    @Override
    public void doing() {
        robot.addToQueue(new Task(
                        () -> robot.getSeconds() > 5,
                        () -> robot.getSeconds() > 20,
                        new Runnable[]{
                                () -> robot.devicePool.driveTrainMotors.leftBackDrive.setPower(0.2),
                        },
                        new Runnable[]{
                                () -> robot.devicePool.driveTrainMotors.leftBackDrive.setPower(0)
                        }
                )
        );

        robot.update();
        robot.updateTasks();
    }

}
