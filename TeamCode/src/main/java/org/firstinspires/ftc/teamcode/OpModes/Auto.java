package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov and @MrFrosty1234
 */

@Autonomous
public class Auto extends BaseOpMode {
    @Override
    public void doing() {
        robot.addToQueue(new Task(
                        () -> robot.getSeconds() > 5,
                        () -> robot.getSeconds() > 20,
                        new Runnable[]{
                                () -> DriveTrainMotors.leftBackDrive.setPower(0.2),
                        },
                        new Runnable[]{
                                () -> DriveTrainMotors.leftBackDrive.setPower(0)
                        }
                )
        );

        robot.update();
        robot.updateTasks();
    }

}
