package org.firstinspires.ftc.teamcode.OpModes;

import org.firstinspires.ftc.teamcode.Math.Position;

public class TeleOp extends BaseOpMode{
    @Override
    public void doing(){
        Position target = new Position(rightStickY,rightStickX,leftStickX);
        robot.driveTrain.moveVel(target);
    }
}
