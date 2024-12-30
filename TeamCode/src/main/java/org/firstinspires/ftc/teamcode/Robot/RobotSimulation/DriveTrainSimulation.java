package org.firstinspires.ftc.teamcode.Robot.RobotSimulation;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;

public class DriveTrainSimulation {
    public static  Position position      = new Position();
    public static  Position localPosition = new Position();
    public static  Position velocity      = new Position();
    private static Position oldPosition   = new Position();
    static ElapsedTime time = new ElapsedTime();
    public static void updatePosition(){
        if(time.seconds()>0.001) {
            localPosition.x += velocity.x * time.seconds();
            localPosition.y += velocity.y * time.seconds();
            localPosition.h += velocity.h * time.seconds();

            Position dp = new Position() .copyFrom(localPosition). vectorMinus(oldPosition);
            oldPosition.copyFrom(localPosition);
            dp.rotateVector(localPosition.h);

            position.vectorPlus(dp);
            position.h = localPosition.h;

            time.reset();
        }
    }
    public static void reset(){
        position      = new Position();
        localPosition = new Position();
        velocity      = new Position();
        oldPosition   = new Position();
    }
}
