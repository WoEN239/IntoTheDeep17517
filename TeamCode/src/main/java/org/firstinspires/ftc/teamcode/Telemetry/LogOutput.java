package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.Robot.Robot;

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


    public void addData(String text, Object value) {
        RobotLog.d(String.format("text = %s, value = %s",text, value.toString()));
    }

    @Override
    public void update() {
        Map<String, Object> map = Collections.emptyMap();
        map.put(text, value);
        addLoggingObjects(map);
    }

    public Object addLoggingObjects(Map<String, Object> map) {
        Object pair = "";

        for(Map.Entry<String, Object> entry : map.entrySet()){
            addData(entry.getKey(),entry.getValue());
            pair = entry.getValue();
        }
        return pair;
    }
}
