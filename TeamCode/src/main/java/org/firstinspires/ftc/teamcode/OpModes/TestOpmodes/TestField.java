package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.Calendar;

@TeleOp

public class TestField extends LinearOpMode {

    TelemetryPacket packet = new TelemetryPacket();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(opModeIsActive()){
            packet = new TelemetryPacket();
            packet.fieldOverlay().setFill("blue").fillRect(-20,-20,40,40);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
