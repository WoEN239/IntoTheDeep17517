package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class FieldView {

    public TelemetryPacket packet = new TelemetryPacket();
    private final double smPerInch = 1.0/2.54;

    public double height = 36.5 / 2.0;
    public double width = 20.0 / 2.0;

    public Position circle = new Position();
    public Position position = new Position();

    private void rotatePoints(double[] xPoints, double[] yPoints, double angle) {
        for (int i = 0; i < xPoints.length; i++) {
            double x = xPoints[i];
            double y = yPoints[i];
            Position p = new Position(x,y,0);
            p.rotateVector(angle);
            xPoints[i]= p.x;
            yPoints[i]= p.y;
        }
    }
    private void plusVector(double [] x, double [] y, Position p){
        for (int j = 0; j < x.length; j++) {
            x[j] += p.x;
            y[j] += p.y;
        }
    }

    public  void updateField() {
        double[] xPoints;
        double[] yPoints;

        Position rect = new Position(height,width,0);
        rect.rotateVector(position.h);

        xPoints = new double[]{
                 + height,
                 + height,
                 - height,
                 - height};
        yPoints = new double[]{
                (+ width),
                (- width),
                (- width),
                (+ width)};

        rotatePoints(xPoints,yPoints,position.h);
        plusVector(xPoints,yPoints,position);

        Robot.telemetryPacket.fieldOverlay().setScale(smPerInch, smPerInch);
        Robot.telemetryPacket.fieldOverlay().setFill("green");

        Robot.telemetryPacket.fieldOverlay().fillCircle(circle.x, circle.y,5);

        Robot.telemetryPacket.fieldOverlay().setFill("blue");
        Robot.telemetryPacket.fieldOverlay().fillPolygon(xPoints, yPoints);

        Robot.telemetryPacket.field().fillCircle(100,100,5);
        Robot.telemetryPacket.fieldOverlay().setFill("green");
        Robot.telemetryPacket.fieldOverlay().strokeLine(position.x,position.y, position.x + rect.x, position.y + rect.y);
    }

}

