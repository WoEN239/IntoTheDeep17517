package org.firstinspires.ftc.teamcode.Modules.Intake.Diff;

import com.qualcomm.robotcore.hardware.Servo;

public class Diff {
    //вверх - вниз pitch

    private final Servo plus ;
    private final Servo minus;

    public Diff(Servo plus, Servo minus) {
        this.plus  = plus;
        this.minus = minus;
    }

    public void setTarget(double yaw, double pitch){
        plus.setPosition (( yaw   + pitch)/270.0);
        minus.setPosition((-yaw  + pitch)/270.0);
    }

}
