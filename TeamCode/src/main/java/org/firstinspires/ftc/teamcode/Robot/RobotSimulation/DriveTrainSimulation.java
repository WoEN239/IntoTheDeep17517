package org.firstinspires.ftc.teamcode.Robot.RobotSimulation;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;

public class DriveTrainSimulation {
    public static Position position = new Position();
    public static Position velocity = new Position();

    static ElapsedTime time = new ElapsedTime();
    public static void updatePosition(){
        if(time.seconds()>0.02) {
            position.x += velocity.x * time.seconds();
            position.y += velocity.y * time.seconds();
            position.h += velocity.h * time.seconds();
            time.reset();
        }
    }
}
