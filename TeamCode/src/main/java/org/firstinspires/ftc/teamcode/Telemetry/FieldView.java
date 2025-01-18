package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegment;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegmentFollower;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class FieldView {

    public static TelemetryPacket packet = new TelemetryPacket();
    private static double smPerInch = 1/2.54;

    public static double height = 36.5 / 2.0;
    public static double width = 20 / 2.0;

    public static Position circle = new Position();
    public static Position position = new Position();

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
    public static void updateField() {
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

        LineSegment trajectory = LineSegmentFollower.targetLineSegment;
        Robot.telemetryPacket.fieldOverlay().strokeLine(trajectory.start.x,trajectory.start.y,
                                               trajectory.end.x,  trajectory.end.y );

    }

}

