package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class Sensors {
    public static DigitalChannel downButton;
    public static AnalogInput leftColorSensor;
    public static AnalogInput rightColorSensor;

    public static void init(HardwareMap hardwareMap) {
        leftColorSensor = hardwareMap.get(AnalogInput.class, "leftColorSensor");
        downButton = hardwareMap.get(DigitalChannel.class, "downButton");
        rightColorSensor = hardwareMap.get(AnalogInput.class, "rightColorSensor");
    }

    public void reset() {
        downButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
