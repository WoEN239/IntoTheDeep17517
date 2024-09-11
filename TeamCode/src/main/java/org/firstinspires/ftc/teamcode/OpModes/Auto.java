package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Events.Task;
import org.firstinspires.ftc.teamcode.Math.Position;
@Autonomous
public class Auto extends BaseOpMode{
    boolean first = true;
    @Override
    public void doing(){
        if(first) {
            robot.addToQueue(new Task(
                            () -> robot.timer.seconds() > 1,
                            () -> false,
                            () -> robot.devicePool.leftBackDrive.setPower(1)
                    )
            );
            first = false;
        }

    }
}
