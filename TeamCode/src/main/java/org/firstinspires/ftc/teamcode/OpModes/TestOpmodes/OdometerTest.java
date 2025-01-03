package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
@TeleOp
public class OdometerTest extends BaseMode {
    Position position = new Position();
    Position velocity = new Position();

    @Override
    public void doing() {
        Robot.telemetry.addData("xPos", position.x);
        Robot.telemetry.addData("yPos", position.y);
        Robot.telemetry.addData("hPos", position.h);
    }
}
