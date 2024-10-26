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
        downButton = hardwareMap.get(DigitalChannel.class, "fakeSensorD");
        leftColorSensor = hardwareMap.get(AnalogInput.class, "fakeSensorA");
        rightColorSensor = hardwareMap.get(AnalogInput.class, "fakeSensorA");

    }

    public void reset() {
        downButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
