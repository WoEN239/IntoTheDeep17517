package org.firstinspires.ftc.teamcode.Math;
import static java.lang.Math.abs;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.Arrays;

/**
 * Writing by EgorKhvostikov
 */

@Config
public class Filter {
    FilterStatus status;
    double velocityTrue = 0;

    public Filter(FilterStatus status) {
        this.status = status;
        reads = new double[status.medianSize];
    }
    public void update(){
        calcNewVel();
        updateReads();
        calcMedian();
        calcVel();
        if(status.isTelemetry) {
            updateTelemetry();
        }
    }

    public double getVelocity() {
        return velocityTrue;
    }

    private double posOld = 0;
    private double posNew = 0;
    private double tOld   = 0;
    private double velMathNew = 0;
    private double medianVelNow = 0;
    private double medianVelOld = 0;
    private final double [] reads;
    ElapsedTime timer = new ElapsedTime();

    public void setPos(double posNew) {
        this.posNew = posNew;
        FtcDashboard.getInstance().getTelemetry().addData("updateMotors",posNew);
    }

    private void calcNewVel(){
        double tNow = timer.seconds();
        double dt = tNow - tOld;
        tOld = tNow;

        double dp = posNew - posOld;
        posOld = posNew;

        velMathNew = dp/dt;
    }
    private void updateReads(){
        if(status.medianSize >= 1) {
            for (int i = 0; i < status.medianSize - 1; i++) {
                reads[i] = reads[(i + 1)];
            }
            reads[status.medianSize - 1] = velMathNew;
        }
    }
    private void updateTelemetry(){
        Robot.telemetry.addData("Median vel", medianVelNow);
        Robot.telemetry.addData("Math vel", velMathNew);
        Robot.telemetry.addData("Velocity", velocityTrue);

    }
    private void calcMedian() {
        if (status.medianSize >= 1) {
            double[] sortReads = reads.clone();
            Arrays.sort(sortReads);
            this.medianVelNow = sortReads[(status.medianSize - 1) / 2];
        }
    }
    private void calcVel(){
        double k = 0;
        double dv = medianVelNow - medianVelOld;
        medianVelOld = medianVelNow;
        k = abs(dv/status.senseUp);
        if(abs(dv)>status.senseUp){
            k = status.bigK;
        }
        if(abs(dv)< status.senseDown){
            k = status.smallK;
        }

        velocityTrue = velocityTrue + k*(medianVelNow - velocityTrue);
    }
}
