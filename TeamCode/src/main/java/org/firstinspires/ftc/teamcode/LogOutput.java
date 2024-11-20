package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.ILogiable;

import java.util.Collections;
import java.util.Map;

@Config

public class LogOutput implements ILogiable {

    Robot robot;

    public static String text = "void";
    public static Object value = new Object();

    public LogOutput(Robot robot) {
        this.robot = robot;
        update();
    }


    public void addData( Map<String, Object> map) {
        RobotLog.d(String.format("text = %s, value = %s",map.get(text)));
    }

    public void update() {
        Map<String, Object> map = addLoggingObjects(text, value);
        addData(map);
    }

    @Override
    public Map<String, Object> addLoggingObjects(String text, Object value) {
        Map<String, Object> map = Collections.emptyMap();
        map.put(text, value);
        return map;
    }
}
