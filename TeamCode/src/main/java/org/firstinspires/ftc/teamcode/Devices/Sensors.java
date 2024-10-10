package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensors {
    HardwareMap hardwareMap;

    DigitalChannel downButton;

    AnalogInput leftColorSensor;
    AnalogInput rightColorSensor;



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
