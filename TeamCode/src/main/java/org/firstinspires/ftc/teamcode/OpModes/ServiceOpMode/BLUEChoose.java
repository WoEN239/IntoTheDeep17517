package org.firstinspires.ftc.teamcode.OpModes.ServiceOpMode;

import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;

public class BLUEChoose extends BaseMode {
    @Override
    public void loopRun() {
        Robot.myTeam = Team.BLUE;
        Robot.telemetryPacket.put("team", Robot.myTeam.toString());
    }
}
