package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/

@Config
public class PositionPool {

    public static Position start = new Position(13,-160,90);

    public static Position humanElement = new Position(120,-110 ,-90);

    public static Position wall = new Position  (100,-143,90);

    public static Position chamber = new Position(0,-55,90);

    public static Position fChamber = new Position(13,-80,90);


}