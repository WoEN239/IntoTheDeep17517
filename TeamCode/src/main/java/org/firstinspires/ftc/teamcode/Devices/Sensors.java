package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
  Writing by @MrFrosty1234
*/

public class Sensors {
    public static DigitalChannel leftButton;
    public static DigitalChannel rightButton;


    public static void init(HardwareMap hardwareMap) {
        leftButton  = hardwareMap.get(DigitalChannel.class, "leftButton"       );
        rightButton = hardwareMap.get(DigitalChannel.class, "rightButton"      );
        reset();
    }

    public static void reset() {
        leftButton .setMode(DigitalChannel.Mode.INPUT);
        rightButton.setMode(DigitalChannel.Mode.INPUT);
    }

}
