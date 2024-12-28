package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Config

public class FieldView {


    static TelemetryPacket packet = new TelemetryPacket();
    private static double smPerInch = 1/2.54;

    public static Position target = new Position();
    public static double height = 36.5 / 2.0;
    public static double width = 20 / 2.0;

    static void rotatePoints(double[] xPoints, double[] yPoints, double angle) {
        for (int i = 0; i < xPoints.length; i++) {
            double x = xPoints[i];
            double y = yPoints[i];
            Position p = new Position(x,y,0);
            p.rotateVector(angle);
            xPoints[i]= p.x;
            yPoints[i]= p.y;
        }
    }
    static void plusVector(double [] x, double [] y, Position p){
        for (int j = 0; j < x.length; j++) {
            x[j] += p.x;
            y[j] += p.y;
        }
    }
    static int i = 0;
    public static void updateField(Position p) {
        packet = new TelemetryPacket();
        double[] xPoints;
        double[] yPoints;
        double xPos = p.x;
        double yPos = p.y;

        Position rect = new Position(height,width,0);
        rect.rotateVector(p.h);

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

        rotatePoints(xPoints,yPoints,p.h);
        plusVector(xPoints,yPoints,p);

        packet.fieldOverlay().setScale(smPerInch, smPerInch);

        packet.fieldOverlay().setFill("red");
        packet.fieldOverlay().fillCircle(target.x,target.y,10);

        packet.fieldOverlay().setFill("blue");
        packet.fieldOverlay().fillPolygon(xPoints, yPoints);

        packet.fieldOverlay().setFill("green");
        packet.fieldOverlay().strokeLine(p.x,p.y, p.x + rect.x, p.y + rect.y);

        FtcDashboard.getInstance().sendTelemetryPacket(packet);
        Robot.telemetry.addData("send packet №",++i);
    }

}

