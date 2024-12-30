package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
@Config
public class PositionsPool {

    public static Position blueStart         = new Position(0,0,0);
    public static Position redStart          = new Position(0,-150,0);

    public static Position redCenterEat      = new Position(-50,0,0);
    public static Position blueCenterEat     = new Position(0,0,0);

    public static Position redCenterScoring  = new Position(0,-50,-90);
    public static Position blueCenterScoring = new Position(0,0,0);

    public static Position redBasketScoring  = new Position(-150,-150,-45);
    public static Position blueBasketScoring = new Position(0,0,0);

    public static Position redFirstElement   = new Position(0,0,0);

}