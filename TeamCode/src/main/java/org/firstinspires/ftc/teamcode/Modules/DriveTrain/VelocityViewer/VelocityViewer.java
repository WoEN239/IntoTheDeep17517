package org.firstinspires.ftc.teamcode.Modules.DriveTrain.VelocityViewer;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class VelocityViewer implements Listener {
    private Robot robot;
    private VelocityLocalViewer localViewer = new VelocityLocalViewer();

    public void init(Robot robot){
        this.robot = robot;
        localViewer.init(robot);
    }
    private final Position velocityGlobal = new Position(0,0,0);

    public Position getVelocityGlobal() {
        return velocityGlobal;
    }

    public VelocityLocalViewer getLocalViewer() {
        return localViewer;
    }

    private void calcGlobalVelocity(){
        velocityGlobal.h = robot.imu.getSpeed();
        localViewer.deltaVel.rotateIt(robot.imu.getAngle());
        velocityGlobal.plus(localViewer.deltaVel);
    }
    @Override
    public void read(){
        localViewer.update();
        calcGlobalVelocity();
    }
}
