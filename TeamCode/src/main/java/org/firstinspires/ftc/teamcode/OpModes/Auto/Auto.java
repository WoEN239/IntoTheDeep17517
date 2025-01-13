package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;
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
                        () -> robot.getSeconds() > 7,
                        new Runnable[]{},
                        new Runnable[]{
                                () -> robot.velocityController.moveGlobal(new Position(-75, 0,0))
                        },

                        new Runnable[]{
                                () -> robot.velocityController.moveGlobal(new Position(0, 0,0))
                        }
                )
        );
        robot.addToQueue(new Task(
                        () -> robot.getSeconds() > 7,
                        () -> robot.getSeconds() > 7.1,
                        new Runnable[]{},
                        new Runnable[]{
                                () -> robot.velocityController.moveGlobal(new Position(50, 0,0))
                        },

                        new Runnable[]{
                                () -> robot.velocityController.moveGlobal(new Position(0, 0,0))
                        }
                )
        );
        robot.addToQueue(new Task(
                        () -> robot.getSeconds() > 9,
                        () -> robot.getSeconds() > 11,
                        new Runnable[]{},
                        new Runnable[]{
                                () -> robot.intake.setTarget(IntakeState.WAIT_WALL_EAT)
                        },

                        new Runnable[]{
                                () -> robot.intake.setState(IntakeState.WAIT_WALL_EAT)
                        }
                )
        );
        robot.addToQueue(new Task(
                        () -> robot.getSeconds() > 12,
                        () -> robot.getSeconds() > 14,
                        new Runnable[]{},
                        new Runnable[]{
                                () -> robot.velocityController.moveGlobal(new Position(75, 0,0))
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
