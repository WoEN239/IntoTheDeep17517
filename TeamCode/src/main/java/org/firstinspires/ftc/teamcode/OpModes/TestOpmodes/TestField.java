package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import android.graphics.Canvas;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous

public class TestField extends LinearOpMode{



    @Override
    public void runOpMode() throws InterruptedException {
        while(opModeIsActive()){
            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().fillRect(-20,-20,40,40);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
