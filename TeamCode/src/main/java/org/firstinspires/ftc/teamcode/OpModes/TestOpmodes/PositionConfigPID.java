package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp(group = "Test")
@Config
public class PositionConfigPID extends BaseMode {
    public static Position pos = new Position()                ;
    public static Position target = new Position(0,0,0);
    ElapsedTime timer = new ElapsedTime();
    public static int k = 5;
    int n = 1;
    @Override
    public void loopRun() {
        DriveTrainMotors.initPid();
        pos.copyFrom(robot.driveTrain.getPosition());
        Robot.telemetryPacket.put("xV", pos.x);
        Robot.telemetryPacket.put("yV", pos.y);
        Robot.telemetryPacket.put("hV", pos.h);
        Robot.telemetryPacket.put("xT", n*target.x);
        Robot.telemetryPacket.put("yT", n*target.y);
        Robot.telemetryPacket.put("hT", n*target.h);
       if(timer.seconds()%(2*k) > k){
            robot.driveTrain.setState(DriveTrainManager.RobotState.POINT);
            robot.driveTrain.setManualPosition(target);
           n = 1;
        }else{
           robot.driveTrain.setState(DriveTrainManager.RobotState.POINT);
            robot.driveTrain.setManualPosition(new Position(-target.x,-target.y,-target.h));
            n = -1;
        }
    }
}