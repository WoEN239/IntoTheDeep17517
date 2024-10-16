package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Writing by @MrFrosty1234
 */

public class Sensors {
    HardwareMap hardwareMap;

    public DigitalChannel downButton;

    public AnalogInput leftColorSensor;
    public AnalogInput rightColorSensor;



    public Sensors(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        downButton = hardwareMap.get(DigitalChannel.class, "downButton");

        leftColorSensor = hardwareMap.get(AnalogInput.class, "leftColorSensor");

        rightColorSensor = hardwareMap.get(AnalogInput.class, "rightColorSensor");

    }

    public void reset(){
        downButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
