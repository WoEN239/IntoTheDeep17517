package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;

import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;

@Config
public class ColorSensor {
    public static AdafruitI2cColorSensor sensor;
    private ColorDetective colorDetective = ColorDetective.NOTHING;

    public static double red = 0;
    public static double yellowGreenDetect = 80;
    public static double detect = 25;

    public ColorDetective getColor(){
        return colorDetective;
    }

    public void init() {
        sensor = Sensors.sampleSensor;
    }

    public void computeColorDetect(){
        if(sensor.blue() > detect || sensor.red() > detect){
            if(sensor.blue() > sensor.red()){
                if(Robot.myTeam == Team.BLUE){
                    colorDetective = ColorDetective.OUR;
                }else{
                    colorDetective = ColorDetective.OPPONENT;
                }
            }else{

                if(Robot.myTeam == Team.RED){
                    colorDetective = ColorDetective.OUR;
                }else{
                    colorDetective = ColorDetective.OPPONENT;
                }

                if(sensor.green()>yellowGreenDetect){
                    colorDetective = ColorDetective.OUR;
                }
            }
        }else{
            colorDetective = ColorDetective.NOTHING;
        }

    }

}
