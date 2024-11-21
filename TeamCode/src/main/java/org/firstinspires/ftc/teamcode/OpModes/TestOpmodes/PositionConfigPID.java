package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(group = "Test")
@Config
public class PositionConfigPID extends BaseMode {
    public static Position pos = new Position()                ;
    public static Position target = new Position(0,0,0);
    ElapsedTime timer = new ElapsedTime();
    public static int k = 5;
    int n = 1;
    @Override
    public void doing() {
        DriveTrainMotors.initPid();
        pos.copyFrom(robot.positionViewer.getPositionRealGlobal());
        Robot.telemetry.addData("xV", pos.x);
        Robot.telemetry.addData("yV", pos.y);
        Robot.telemetry.addData("hV", pos.h);

        Robot.telemetry.addData("xT", n*target.x);
        Robot.telemetry.addData("yT", n*target.y);
        Robot.telemetry.addData("hT", n*target.h);
        if(timer.seconds()%(2*k) > k){
            robot.positionController.move(target);
            n = 1;
        }else{
            robot.positionController.move(new Position(-target.x,-target.y,-target.h));
            n = -1;
        }
    }
}