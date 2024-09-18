package org.firstinspires.ftc.teamcode.Modules.DriveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;

public class Odometry implements IModule {
    public void init(Robot robot){
        rightm = robot.devicePool.rightOdometer;
        leftm = robot.devicePool.leftOdometer;
        ym = robot.devicePool.yOdometer;
        velRightBackM = robot.devicePool.rightBackDrive;
        velLeftBackM  = robot.devicePool.leftBackDrive;
        velRightForM  = robot.devicePool.rightForwardDrive;
        velLeftForM   = robot.devicePool.leftForwardDrive;
        this.robot = robot;
    }
    Robot robot;
    private final Position velocityLocal = new Position(0,0,0);
    private final Position positionLocal = new Position(0,0,0);
    private final Position velocityGlobal = new Position(0,0,0);
    private final Position positionGlobal = new Position(0,0,0);
    Motor rightm;
    Motor leftm;
    Motor ym;

    public void reset(){
        rightm.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightm.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftm.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftm.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ym.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ym.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public Position getVelocityLocal() {
        return velocityLocal;
    }

    public Position getPositionLocal() {
        return positionLocal;
    }

    public Position getVelocityGlobal() {
        return velocityGlobal;
    }

    public Position getPositionGlobal() {
        return positionGlobal;
    }

    Position deltaPosition;
    private void calcLocalPosition(){
        double x = ym.dev.getCurrentPosition()                                            ;
        double y = (rightm.dev.getCurrentPosition() + leftm.dev.getCurrentPosition()) /2.0;
        double h = (rightm.dev.getCurrentPosition() - leftm.dev.getCurrentPosition()) /2.0;
        deltaPosition = new Position(x,y,h);
        deltaPosition.minus(positionLocal);
        positionLocal.y = y;
        positionLocal.x = x;
        positionLocal.h = h;
    }
    private void calcGlobalPosition(){
        positionGlobal.h = robot.imu.getAngle();
        deltaPosition.rotateIt(positionGlobal.h);
        positionGlobal.plus(deltaPosition);
    }
    Motor velRightBackM;
    Motor velLeftForM;
    Motor velRightForM;
    Motor velLeftBackM;
    Position deltaVel;
    public void calcLocalVelocity(){
        double x = (velRightBackM.getVel()+velRightForM.getVel()+velLeftBackM.getVel()+velLeftForM.getVel())/4.0;
        double y = (velRightBackM.getVel()-velRightForM.getVel()-velLeftBackM.getVel()+velLeftForM.getVel())/4.0;
        double h = (-velRightBackM.getVel()-velRightForM.getVel()+velLeftBackM.getVel()+velLeftForM.getVel())/4.0;
        deltaVel = new Position(x,y,h);
        deltaVel.minus(velocityLocal);
        velocityLocal.x = x;
        velocityLocal.y = y;
        velocityLocal.h = h;
    }
    public void calcGlobalVelocity(){
        velocityGlobal.h = robot.imu.getSpeed();
        deltaVel.rotateIt(positionGlobal.h);
        positionGlobal.plus(deltaVel);
    }
    @Override
    public void update(){
        calcLocalPosition();
        calcLocalVelocity();
        calcGlobalPosition();
        calcGlobalVelocity();
    }
}
