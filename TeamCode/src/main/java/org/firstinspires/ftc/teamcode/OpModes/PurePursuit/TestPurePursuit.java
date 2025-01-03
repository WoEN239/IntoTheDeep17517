package org.firstinspires.ftc.teamcode.OpModes.PurePursuit;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/*
  Writing by EgorKhvostikov
*/
public class TestPurePursuit extends BaseMode {

    @Override
    public void doing(){
        Robot.getInstance().driveTrain.addWayPoints();
    }
}
