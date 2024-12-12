package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Config

public class FieldView implements Controller {

    Robot robot;

    TelemetryPacket packet = new TelemetryPacket();
    Canvas rect = new Canvas();
    private double inchPerSm = 1.0 / 2.54;

    public static double height = 36.5 / 2.0;
    public static double width = 20 / 2.0;

    @Override
    public void init(Robot robot) {
        this.robot = robot;
        updateField();
    }

    void rotatePoints(double[] xPoints, double[] yPoints, double angle) {
        for (int i = 0; i < xPoints.length; i++) {
            double x = xPoints[i];
            double y = yPoints[i];
            xPoints[i] = x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle));
            yPoints[i] = x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle));
        }
    }

    public void updateField() {
        packet = new TelemetryPacket();
        double[] xPoints;
        double[] yPoints;
        double xPos = robot.positionViewer.getPositionRealGlobal().x;
        double yPos = robot.positionViewer.getPositionRealGlobal().y;

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
        rotatePoints(xPoints, yPoints, robot.positionViewer.getPositionRealGlobal().h);
        packet.fieldOverlay().setScale(inchPerSm, inchPerSm);
        packet.fieldOverlay().fillPolygon(xPoints, yPoints);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);
    }

    public void update() {
        updateField();
    }
}

