package org.firstinspires.ftc.teamcode.Robot.RobotSimulation;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.maxAccel;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.maxLinSpeed;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;

public class DriveTrainSimulation {
    public static  Position position      = new Position();
    public static  Position localPosition = new Position();
    public static  Position velocity      = new Position();
    private static Position oldPosition   = new Position();
    static ElapsedTime time = new ElapsedTime();

    private static double lastVelX = 0;
    private static double lastVelY = 0;
    private static void normalizeVelocity(){
        double x = velocity.x;
        double y = velocity.y;

        double accelX = (x - lastVelX)/time.seconds();
        if(abs(accelX)>maxAccel){
            double k = signum(accelX);
            x =  lastVelX + maxAccel*time.seconds()*k;
        }
        lastVelX = x;

        if(abs(x) > maxLinSpeed){
            x = signum(x)*maxLinSpeed;
        }
        velocity.x = x;


        double accelY = (y - lastVelY)/time.seconds();
        if(abs(accelY)>maxAccel){
            double k = signum(accelY);
            y =  lastVelY + maxAccel*time.seconds()*k;
        }
        lastVelY = y;

        if(abs(y) > maxLinSpeed){
            y = signum(y)*maxLinSpeed;
        }
        velocity.y = y;
    }

    public static void updatePosition(){
        if(time.seconds()>0.001) {
            normalizeVelocity();
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
