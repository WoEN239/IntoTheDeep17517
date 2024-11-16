package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.Controller;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@Config

public class FieldView implements Controller {

    Robot robot;

    TelemetryPacket packet = new TelemetryPacket();
    Canvas rect = new Canvas();
    private double inchPerSm = 1.0 / 2.54;

    public static double height = 36.5 / 2.0;
    public static double width = 20 / 2.0;

    double[] xPoints;
    double[] yPoints;


    @Override
    public void init(Robot robot) {
        this.robot = robot;
        if(BaseMode.isField)
            fieldDraw();
    }

    void rotatePoints(double[] xPoints, double[] yPoints, double angle) {
        for (int i = 0; i < xPoints.length; i++) {
            double x = xPoints[i];
            double y = yPoints[i];
            xPoints[i] = x * Math.cos(angle) - y * Math.sin(angle);
            yPoints[i] = x * Math.sin(angle) + y * Math.cos(angle);
        }
    }

    public void fieldDraw(){
        double xPos = robot.positionViewer.getPositionRealGlobal().x;
        double yPos = robot.positionViewer.getPositionRealGlobal().y;

        xPoints = new double[]{xPos - height,
                xPos + height,
                xPos - height,
                xPos + height};
        yPoints = new double[]{yPos - width,
                yPos + width,
                yPos - width,
                yPos + width};
        rotatePoints(xPoints, yPoints, robot.positionViewer.getPositionRealGlobal().h);
        packet.fieldOverlay().setScale(1.0 / inchPerSm, 1.0 / inchPerSm);
        packet.fieldOverlay().fillPolygon(xPoints, yPoints);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);
    }
}
