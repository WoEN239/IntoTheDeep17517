package org.firstinspires.ftc.teamcode.OpenCv;

import org.opencv.core.Scalar;

public class RangeHvsConfig {
    public double V = 0;
    public double H = 0;
    public double S = 0;
    public Scalar toScalar(){
        return new Scalar(H,V,S);
    }

}
