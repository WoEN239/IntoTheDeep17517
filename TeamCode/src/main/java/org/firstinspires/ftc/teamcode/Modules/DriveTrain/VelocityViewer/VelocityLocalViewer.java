package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class VelocityLocalViewer{
    public void init(Robot robot){
        velRightBackM = robot.devicePool.driveTrainMotors.rightBackDrive;
        velLeftBackM  = robot.devicePool.driveTrainMotors.leftBackDrive;
        velRightForM  = robot.devicePool.driveTrainMotors.rightForwardDrive;
        velLeftForM   = robot.devicePool.driveTrainMotors.leftForwardDrive;
    }
    private final Position velocityLocal = new Position(0,0,0);
    public Position getVelocityLocal() {
        return velocityLocal;
    }
    private Motor velRightBackM;
    private Motor velLeftForM;
    private Motor velRightForM;
    private Motor velLeftBackM;

    public Position deltaVel;
    private void calcLocalVelocity(){
        double x = (velRightBackM.getVelocity()+velRightForM.getVelocity()+velLeftBackM.getVelocity()+velLeftForM.getVelocity())/4.0;
        double y = (velRightBackM.getVelocity()-velRightForM.getVelocity()-velLeftBackM.getVelocity()+velLeftForM.getVelocity())/4.0;
        double h = (-velRightBackM.getVelocity()-velRightForM.getVelocity()+velLeftBackM.getVelocity()+velLeftForM.getVelocity())/4.0;
        deltaVel = new Position(x,y,h);
        deltaVel.minus(velocityLocal);
        velocityLocal.x = x;
        velocityLocal.y = y;
        velocityLocal.h = h;
    }
    public void update(){
        calcLocalVelocity();
    }
}
