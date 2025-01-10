package org.firstinspires.ftc.teamcode.Devices;

import static com.qualcomm.hardware.ams.AMSColorSensor.AMS_TCS34725_ADDRESS;

import static org.firstinspires.ftc.teamcode.Devices.FixColorSensor.fix;

import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;
import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDeviceWithParameters;

import java.lang.reflect.Field;

/*
  Writing by @MrFrosty1234
*/

public class Sensors {
    public static DigitalChannel downLeftButton;

    public static DigitalChannel downRightButton;
    public static AnalogInput leftColorSensor;
    public static AnalogInput rightColorSensor;
    public static AdafruitI2cColorSensor sampleSensor;

    public static void init(HardwareMap hardwareMap) {
        leftColorSensor = hardwareMap.get(AnalogInput.class, "leftColorSensor");
        downLeftButton = hardwareMap.get(DigitalChannel.class, "downLeftButton");
        rightColorSensor = hardwareMap.get(AnalogInput.class, "rightColorSensor");
        downRightButton = hardwareMap.get(DigitalChannel.class, "downRightButton");
        sampleSensor = fix(hardwareMap.get(AdafruitI2cColorSensor.class, "puckSensor"));

        reset();
    }

    public static void reset() {
        downLeftButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
