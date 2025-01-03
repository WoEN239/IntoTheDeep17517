package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.PositionPool;
/*
  Writing by EgorKhvostikov
*/
public enum Team {
    BLUE(PositionPool.blueStart), RED(PositionPool.redStart);

    public final Position startPos = new Position();
    Team(Position p){
        startPos.copyFrom(p);
    }
}
