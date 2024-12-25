package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Config

public class FieldView {


    //static TelemetryPacket packet = new TelemetryPacket();
    private static double smPerInch = 1/2.54;

    public static Position target = new Position();
    public static double height = 36.5 / 2.0;
    public static double width = 20 / 2.0;

    static void rotatePoints(double[] xPoints, double[] yPoints, double angle) {
        for (int i = 0; i < xPoints.length; i++) {
            double x = xPoints[i];
            double y = yPoints[i];
            xPoints[i] = x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle));
            yPoints[i] = x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle));
        }
    }
    static int i = 0;
    public static void updateField(Position p) {
        //packet = new TelemetryPacket();
        double[] xPoints;
        double[] yPoints;
        double xPos = p.x;
        double yPos = p.y;

        xPoints = new double[]{
                xPos - height,
                xPos - height,
                xPos + height,
                xPos + height};
        yPoints = new double[]{
                yPos - width,
                yPos + width,
                yPos + width,
                yPos - width};

        rotatePoints(xPoints, yPoints, p.h);
        //packet.fieldOverlay().setScale(smPerInch, smPerInch);
//
        //packet.fieldOverlay().setFill("red");
        //packet.fieldOverlay().fillCircle(target.y,target.x,10);
//
        //packet.fieldOverlay().setFill("blue");
        //packet.fieldOverlay().fillPolygon(xPoints, yPoints);

        //FtcDashboard.getInstance().sendTelemetryPacket(packet);
        //Robot.telemetry.addData("send packet №",++i);
    }

}

