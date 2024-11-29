package org.firstinspires.ftc.teamcode.Math;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;
@Config
public class ExpanentoinFilter {
    public static double k = 0.1;
    private double x = 0;
    private final ElapsedTime timer = new ElapsedTime();

    public double getX() {
        return x;
    }

    //☺☺☺

    public void update(double d1, double d2) {
        double dt = timer.seconds();
        timer.reset();

        x = x + d1 + d2*(dt / (k + dt));
    }
}