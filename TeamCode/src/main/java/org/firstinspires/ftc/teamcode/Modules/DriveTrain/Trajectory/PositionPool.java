package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Math.Position;

/*
  Writing by EgorKhvostikov
*/

@Config
public class PositionPool {

    public static Position start = new Position(0,-160,90);


    public static Position humanElement = new Position(123,-110 ,-90);

    public static Position wall = new Position  (123,-140,90);

    public static Position chamber = new Position(0,-70,90);
    public static Position fChamber = new Position(0,-75,90);


}