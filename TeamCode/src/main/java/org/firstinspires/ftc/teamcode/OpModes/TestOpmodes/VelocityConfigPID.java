package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionPidController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp(group = "Test")
@Config
public class VelocityConfigPID extends BaseMode {
    public static Position velPos = new Position();
    public static Position velTarget = new Position(0,0,0);
    ElapsedTime timer = new ElapsedTime();
    public static int k = 5;
    int n = 1;
    @Override
    public void loopRun() {
        DriveTrainMotors.initPid();
        robot.driveTrain.setState(DriveTrainManager.RobotState.TELE_OP);
        velPos.copyFrom(robot.driveTrain.localVelocity);
        Robot.telemetryPacket.put("xV", velPos.x);
        Robot.telemetryPacket.put("yV", velPos.y);
        Robot.telemetryPacket.put("hV", velPos.h);
        Robot.telemetryPacket.put("xT", n*velTarget.x);
        Robot.telemetryPacket.put("yT", n*velTarget.y);
        Robot.telemetryPacket.put("hT", n*velTarget.h);
        if(timer.seconds()%(2*k) > k){
            robot.driveTrain.setVelocityTarget(velTarget);
            n = 1;
        }else{
            robot.driveTrain.setVelocityTarget(new Position(-velTarget.x,-velTarget.y,-velTarget.h));
            n = -1;
        }
    }
}
