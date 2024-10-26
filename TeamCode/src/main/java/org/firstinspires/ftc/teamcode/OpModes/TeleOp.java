package org.firstinspires.ftc.teamcode.OpModes;

import org.firstinspires.ftc.teamcode.Math.Position;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends BaseOpMode {
    public void doing() {
        robot.velocityController.move(new Position(
                rightStickX,rightStickY,leftStickY
        ));
    }
}
