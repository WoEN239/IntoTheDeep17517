package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor;

import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;

import org.firstinspires.ftc.teamcode.Devices.Sensors;

public class ColorSensor {
    AdafruitI2cColorSensor sensor;
    public ColorDetective colorDetective = ColorDetective.NOTHING;

    public static double red = 0;
    public static double blue = 0;
    public static double green = 0;

    public ColorDetective getColor(){
        updateColors();
        return colorDetective;
    }

    public void init() {
        sensor = Sensors.sampleSensor;
    }

    private void updateColors(){
        if(sensor.red() > red){
            colorDetective = ColorDetective.OPPONENT;
        }
        else{
            if(sensor.blue() > blue){
                colorDetective = ColorDetective.OUR;
            }
            else{
                if(sensor.green() > green){
                    colorDetective = ColorDetective.YELLOW;
                }
                else{
                    colorDetective = ColorDetective.NOTHING;
                }
            }
        }
    }

}
