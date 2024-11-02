package org.firstinspires.ftc.teamcode.OpModes;
import org.firstinspires.ftc.teamcode.Math.Position;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseMode {
    public void doing() {
        robot.velocityController.move(
                new Position(leftStickY*2400,leftStickX*2400,rightStickX*2400)
        );
    }
}
