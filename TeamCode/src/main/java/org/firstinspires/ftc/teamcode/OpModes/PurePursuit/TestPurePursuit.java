package org.firstinspires.ftc.teamcode.OpModes.PurePursuit;
import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionsPool.*;

import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;

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
       new WayPoint(redBasketScoring,
       PurePursuitTask.easyBuild(IntakeState.WAIT_UP, robot.intake),
       PurePursuitTask.easyBuild(IntakeState.WAIT_DOWN, robot.intake)),
                /* eдем к первому образцу */
       new WayPoint(redFirstElement,
       PurePursuitTask.easyBuild(IntakeState.WAIT_EAT,robot.intake),
       PurePursuitTask.easyBuild(IntakeState.WAIT_DOWN, robot.intake)),
                /* едем к корзине */
        new WayPoint(redBasketScoring,
        PurePursuitTask.easyBuild(IntakeState.WAIT_UP,robot.intake),
        PurePursuitTask.easyBuild(IntakeState.WAIT_DOWN, robot.intake)),
                /* паркуемся */
        new WayPoint(redCenterScoring)
        );
    }
}
