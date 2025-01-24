package org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.ColorSensor;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Devices.Sensors;
import org.firstinspires.ftc.teamcode.Math.ArrayExtra;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;

/*
  Writing by EgorKhvostikov
*/
@Config
public class ColorSensor {
    public static AdafruitI2cColorSensor sensor;
    private ColorDetective colorDetective = ColorDetective.NOTHING;
    private ColorDetective filterColorDetective = ColorDetective.NOTHING;

    public static double yellowGreenDetect = 60;
    public static double detect            = 20;

    public ColorDetective getColor(){
        return filterColorDetective;
    }

    public void init() {
        sensor = Sensors.sampleSensor;
    }

    private final double [] redReads = new double[5];
    private final double [] greenReads = new double[5];
    private final double [] blueReads = new double[5];

    private double green = 0;
    private double blue = 0;
    private double red = 0;


    ElapsedTime timer = new ElapsedTime();

    ElapsedTime timerDetect = new ElapsedTime();
    public void update(){
        if (timer.seconds() > 0.05) {

            ArrayExtra.updateLikeBuffer(sensor.red(), redReads);
            red = ArrayExtra.findMedian(redReads);

            ArrayExtra.updateLikeBuffer(sensor.blue(), blueReads);
            blue = ArrayExtra.findMedian(blueReads);

            ArrayExtra.updateLikeBuffer(sensor.green(), greenReads);
            green = ArrayExtra.findMedian(greenReads);

            timer.reset();
        }
        Robot.telemetryPacket.put("green",green);
        Robot.telemetryPacket.put("blue",blue);
        Robot.telemetryPacket.put("red",red);

        computeColorDetect();
        if(colorDetective != lastDetect){
            timerDetect.reset();
        }

        if(timerDetect.seconds()>0.5){
            filterColorDetective = colorDetective;
        }
    }

    ColorDetective lastDetect = ColorDetective.NOTHING;
    private void computeColorDetect(){

        if(blue<detect && red < detect){
            lastDetect = colorDetective;
            colorDetective = ColorDetective.NOTHING;
            return;
        }

        if(green > yellowGreenDetect ){
            lastDetect = colorDetective;
            colorDetective = ColorDetective.OUR;
            return;
        }

        if(Robot.myTeam == Team.BLUE){
            if(blue>red){
                lastDetect = colorDetective;
                colorDetective = ColorDetective.OUR;
            }else{
                lastDetect = colorDetective;
                colorDetective = ColorDetective.OPPONENT;
            }
            return;
        }

        if(Robot.myTeam == Team.RED){
            if(blue>red){
                lastDetect = colorDetective;
                colorDetective = ColorDetective.OPPONENT;
            }else{
                lastDetect = colorDetective;
                colorDetective = ColorDetective.OUR;
            }

        }
    }

}
