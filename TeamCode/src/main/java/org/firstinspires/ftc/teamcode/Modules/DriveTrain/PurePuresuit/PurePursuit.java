package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePuresuit;

import org.firstinspires.ftc.teamcode.Math.Position;

public class PurePursuit {
    public static Line targetLine = new Line();
    public static double localRadius = 1.5;

    public static Position getVirtualTarget(Position pos){
        Position t = targetLine.findProection(pos);
        t.vectorPlus(new Position().copyFrom(targetLine.singleVector).linearMultiply(localRadius));
        return t;
    }
}
