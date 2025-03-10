package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/

@Config
public class PositionPool {

    public static Position start = new Position(0,-160,90);

    public static Position park = new Position(-150, 150,0);

    public static Position humanElement = new Position(124,-105 ,-90);
    public static Position humanScore   = new Position(124,-105 , 85);

    public static Position wall = new Position  (124,-145,90);

    public static Position chamber = new Position(0,-65,90);
    public static Position fChamber = new Position(0,-75,90);


}