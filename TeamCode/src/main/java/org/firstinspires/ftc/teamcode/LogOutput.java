package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;

public class LogOutput implements Controller {

    Robot robot;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        update();
    }


    public void addData(String text, Object value) {
        RobotLog.d(String.format("text = %s, value = %s", text, value.toString()));
    }

    public void update() {

    }
}
