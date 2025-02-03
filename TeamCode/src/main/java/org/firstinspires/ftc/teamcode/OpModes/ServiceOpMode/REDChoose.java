package org.firstinspires.ftc.teamcode.OpModes.ServiceOpMode;

import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;

public class REDChoose extends BaseMode {
    @Override
    public void loopRun() {
        Robot.myTeam = Team.RED;
        Robot.telemetryPacket.put("team", Robot.myTeam.toString());
    }
}
