package org.firstinspires.ftc.teamcode.Modules.Intake.SampleSensor;


import android.graphics.Color;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;

import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Listener;
import org.firstinspires.ftc.teamcode.Robot.Robot;


@Config
public class ColorSensorListener implements Listener {

    Robot robot;

    AdafruitI2cColorSensor sensor;

    public ColorDetective colorDetective = ColorDetective.NOTHING;

    public static double red = 0;
    public static double blue = 0;
    public static double green = 0;

    public ColorDetective getColor(){
        return colorDetective;
    }

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        sensor = Sensors.sampleSensor;
    }

    public void updateColors(){
        if(sensor.red() > red){
            colorDetective = ColorDetective.RED;
        }
        else{
            if(sensor.blue() > blue){
                colorDetective = ColorDetective.BLUE;
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

    @Override
    public void read(){
        updateColors();
    }
}
