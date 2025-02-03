package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
@Config
public class PositionPool {

    public static Position blueStart         = new Position(60,160,180);
    public static Position bluePark         = new Position(-150, 150,0);
    public static Position redStart          = new Position(-60,-160,0);

    public static Position redCenterEat      = new Position(-50,0,0);
    public static Position blueCenterEat     = new Position(0,0,0);

    public static Position redCenterScoring  = new Position(0,-50,-90);
    public static Position blueCenterScoring = new Position(-68,0,0);

    public static Position blueHuman         = new Position(-5,100,0);
    public static Position redHuman          = new Position();

    public static Position redBasketScoring  = new Position(-150,-152,45);
    public static Position blueBasketScoring = new Position(150,152,135);

    public static Position blueFirstElement   = new Position(122,138,90);
    public static Position redFirstElement   = new Position(-122,-138,-90);

}