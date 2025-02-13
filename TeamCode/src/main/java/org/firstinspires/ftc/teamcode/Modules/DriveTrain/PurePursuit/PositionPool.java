package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/
@Config
public class PositionPool {

    public static Position start = new Position(60,160,0);
    public static Position park = new Position(-150, 150,0);
    public static Position basketScoring = new Position(150,152,135);
    public static Position firstElement = new Position(122,138,90);

}