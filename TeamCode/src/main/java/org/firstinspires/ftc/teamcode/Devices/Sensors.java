package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
  Writing by @MrFrosty1234
*/

public class Sensors {
    public static DigitalChannel downLeftButton;
    public static DigitalChannel downRightButton;
    public static DigitalChannel upRightButton;
    public static DigitalChannel upLeftButton;
    public static AnalogInput leftColorSensor;
    public static AnalogInput rightColorSensor;

    public static void init(HardwareMap hardwareMap) {
        leftColorSensor = hardwareMap.get(AnalogInput.class, "leftColorSensor");
        downLeftButton = hardwareMap.get(DigitalChannel.class, "downLeftButton");
        upLeftButton = hardwareMap.get(DigitalChannel.class, "upLeftButton");
        rightColorSensor = hardwareMap.get(AnalogInput.class, "rightColorSensor");
        downRightButton = hardwareMap.get(DigitalChannel.class, "downRightButton");
        upRightButton = hardwareMap.get(DigitalChannel.class,"upRightButton");
        reset();
    }

    public static void reset() {
        downLeftButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
