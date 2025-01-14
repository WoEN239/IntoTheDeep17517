package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class Sensors {
    public static DigitalChannel downLeftButton;
    public static DigitalChannel downRightButton;
    public static AnalogInput leftColorSensor;
    public static AnalogInput rightColorSensor;

    public static void init(HardwareMap hardwareMap) {
        downLeftButton = hardwareMap.get(DigitalChannel.class, "downLeftButton");
        downRightButton = hardwareMap.get(DigitalChannel.class, "downRightButton");
        leftColorSensor = hardwareMap.get(AnalogInput.class, "leftColorSensor");
        rightColorSensor = hardwareMap.get(AnalogInput.class, "rightColorSensor");

    }

    public void reset() {
        downLeftButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
