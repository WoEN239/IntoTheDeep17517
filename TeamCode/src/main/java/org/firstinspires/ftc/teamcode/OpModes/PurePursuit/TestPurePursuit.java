package org.firstinspires.ftc.teamcode.OpModes.PurePursuit;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionsPool;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Task;

/*
  Writing by EgorKhvostikov
*/
public class TestPurePursuit extends BaseMode {
    /*
       Примерный автоном на PP (red team)
    */
    @Override
    public void doing(){
        robot.purePursuit.addWayPoints(
                /* едем к корзине */
        new WayPoint(PositionsPool.redBasketScoring,
        new Task( ()-> robot.intake.setTarget(IntakeState.WAIT_UP)  ),
        new Task( ()-> robot.intake.setTarget(IntakeState.WAIT_DOWN))),
               /* eдем к первому образцу */
        new WayPoint(PositionsPool.redFirstElement,
        new Task( ()-> robot.intake.setTarget(IntakeState.WAIT_EAT)  ),
        new Task( ()-> robot.intake.setTarget(IntakeState.WAIT_DOWN))),
                /* едем к корзине */
        new WayPoint(PositionsPool.redBasketScoring,
        new Task( ()-> robot.intake.setTarget(IntakeState.WAIT_UP)  ),
        new Task( ()-> robot.intake.setTarget(IntakeState.WAIT_DOWN))),
                /* паркуемся */
        new WayPoint(PositionsPool.redCenterEat)

        );
    }
}
