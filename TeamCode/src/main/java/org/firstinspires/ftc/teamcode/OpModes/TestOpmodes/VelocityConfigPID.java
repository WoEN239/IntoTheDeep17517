package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.DriveTrainMotors;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionController;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot;

@TeleOp(group = "Test")
@Config
public class VelocityConfigPID extends BaseMode {
    public static Position velPos = new Position();
    public static Position velTarget = new Position(0,0,0);
    ElapsedTime timer = new ElapsedTime();
    public static int k = 5;
    int n = 1;
    @Override
    public void doing() {
        PositionController.isUpdate = false;
        DriveTrainMotors.initPid();
        velPos.copyFrom(robot.velocityViewer.getLocalVelocityListener().getVelocityLocalReal());
        Robot.telemetry.addData("xV", velPos.x);
        Robot.telemetry.addData("yV", velPos.y);
        Robot.telemetry.addData("hV", velPos.h);

        Robot.telemetry.addData("xT", n*velTarget.x);
        Robot.telemetry.addData("yT", n*velTarget.y);
        Robot.telemetry.addData("hT", n*velTarget.h);
        if(timer.seconds()%(2*k) > k){
            robot.velocityController.moveLocal(velTarget);
            n = 1;
        }else{
            robot.velocityController.moveLocal(new Position(-velTarget.x,-velTarget.y,-velTarget.h));
            n = -1;
        }
    }
}
