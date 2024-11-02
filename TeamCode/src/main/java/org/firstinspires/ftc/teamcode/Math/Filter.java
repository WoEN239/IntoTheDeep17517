package org.firstinspires.ftc.teamcode.Math;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

import java.util.Arrays;

public class Filter {
    FilterStatus status;
    double velocityTrue = 0;

    public String name;

    public void setName(String n) {
        name = n;
    }
    boolean isInit = false;
    double[] reads;
    public Filter init(FilterStatus status) {
        this.status = status;
        reads = new double[]{0.0,0.0,0.0,0.0,0.0,0.0};
        isInit = true;
        return this;
    }

    public void update() {
        if(isInit) {
            calcNewVel();
            updateReads();
            calcMedian();
            calcVel();
            if (status.isTelemetry) {
                updateTelemetry();
            }
        }
    }

    public double getVelocity() {
        return velocityTrue;
    }

    private double posOld = 0;
    private double posNew = 0;
    private double velMathNew = 0;
    private double medianVelNow = 0;
    private double medianVelOld = 0;
    ElapsedTime timer = new ElapsedTime();

    public void setPos(double posNew) {
        this.posNew = posNew;
    }

    private void calcNewVel() {
        double dt = timer.seconds();
        timer.reset();
        double dp = posNew - posOld;
        posOld = posNew;

        velMathNew = dp / dt;
    }

    private void updateReads() {
        ArrayExtra.updateLikeBuffer(velMathNew,reads);
    }

    private void updateTelemetry() {
        Robot.telemetry.addData("Median vel " + name, medianVelNow);
        Robot.telemetry.addData("Math vel " + name, reads[reads.length - 1]);
        Robot.telemetry.addData("Velocity " + name, velocityTrue);
        Robot.telemetry.addData("time " + name, System.nanoTime());
        Robot.telemetry.addData("medianSize " + name, reads.length);
        Robot.telemetry.addData("reads " + name, Arrays.toString(reads));
    }

    private void calcMedian() {
        double[] sortReads = reads.clone();
        for (int i = 0; i < sortReads.length; i++) {
            double abs = abs(sortReads[i]);
            sortReads[i] = abs;

        }
        Arrays.sort(sortReads);
        double[] filterReads = reads.clone();
        boolean isMin = false;
        boolean isMax = false;

        for (int i = 0; i < filterReads.length; i++) {
            if (abs(filterReads[i]) == sortReads[0] && !isMin) {
                filterReads[i] = Double.NaN;
                isMin = true;
            }
            if (abs(filterReads[i]) == sortReads[sortReads.length - 1] && !isMax) {
                filterReads[i] = Double.NaN;
                isMax = true;
            }
        }
        if (reads.length > 4) {
            double[] noZeroReads = new double[4];
            int count = 0;
            for (double i : filterReads) {
                if (!Double.isNaN(i)) {
                    noZeroReads[count] = i;
                    count++;
                }
            }
            this.medianVelNow = (noZeroReads[0] * 1.5 + noZeroReads[1] * 2.5 +
                    noZeroReads[2] * 3.5 + noZeroReads[3] * 4
            ) / 11.5;
        }
    }

    double[] dvBuffer = new double[5];

    private void updateDvBuffer(double val) {
        ArrayExtra.updateLikeBuffer(val,dvBuffer);
    }

    private void calcVel() {
        double k = 0;
        double dv = medianVelNow - medianVelOld;
        medianVelOld = velocityTrue;
        updateDvBuffer(dv);
        double[] sortBuffer = dvBuffer;
        Arrays.sort(sortBuffer);
        double detect = sortBuffer[sortBuffer.length - 1];

        k = status.smallK;
        if (abs(detect) > status.senseDown) {
            k = status.centreK;
        }
        if (abs(detect) > status.senseUp) {
            k = status.bigK;
        }
        velocityTrue = velocityTrue + k * (medianVelNow - velocityTrue);
    }
}
