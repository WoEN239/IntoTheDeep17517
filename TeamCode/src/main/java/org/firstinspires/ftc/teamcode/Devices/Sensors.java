package org.firstinspires.ftc.teamcode.Devices;

import static org.firstinspires.ftc.teamcode.Devices.FixColorSensor.fix;

import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
  Writing by @MrFrosty1234
*/

public class Sensors {
    public static DigitalChannel downButton;

    public static DigitalChannel upButton;
    public static AnalogInput leftColorSensor;
    public static AnalogInput rightColorSensor;
    public static AdafruitI2cColorSensor sampleSensor;

    public static void init(HardwareMap hardwareMap) {
        leftColorSensor = hardwareMap.get(AnalogInput.class, "leftColorSensor");
        downButton = hardwareMap.get(DigitalChannel.class, "downButton");
        rightColorSensor = hardwareMap.get(AnalogInput.class, "rightColorSensor");
        upButton = hardwareMap.get(DigitalChannel.class, "upButton");
        sampleSensor = fix(hardwareMap.get(AdafruitI2cColorSensor.class, "puckSensor"));


        reset();
    }

    public static void reset() {
        downButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
