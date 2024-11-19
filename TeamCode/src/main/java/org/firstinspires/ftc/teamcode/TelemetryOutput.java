package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;

public class TelemetryOutput implements Controller {

    Robot robot;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        update();
    }


    public void addData(String text, Object value) {
        robot.opMode.telemetry.addData("%d text" ,String.format(text, value.toString()));
        FtcDashboard.getInstance().getTelemetry().addData(text, value);
        RobotLog.d("[Ultlog]");
        RobotLog.d(text, value);
    }


    public void update(){
        robot.opMode.telemetry.update();
        FtcDashboard.getInstance().getTelemetry().update();
    }
}
