package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

/**
 * Writing by EgorKhvostikov and @MrFrosty1234
 */
@TeleOp
public class Auto extends BaseMode {

    @Override
    public void callRun() {
        robot.driveTrain.addWayPoints(new WayPoint(new Position(100,0,0),
                new PurePursuitTask("edf",
                        ()->true,
                        ()->robot.driveTrain.setManualPosition(new Position(100,0,0))
                )),
                new WayPoint(new Position(100,50,10),
                        new PurePursuitTask("edf",
                                ()->true,
                                ()->robot.driveTrain.setManualPosition(new Position(100,50,0))
                        )),
                new WayPoint(new Position(50,0,10),
                        new PurePursuitTask("edf",
                                ()->true,
                                ()->robot.driveTrain.setManualPosition(new Position(50,0,0))
                        ))
                );

        isNeedToCall = false;
    }
    @Override
    public void loopRun(){
        FieldView.position = robot.driveTrain.position;
        FieldView.circle = robot.driveTrain.getPidTarget();
    }
}
