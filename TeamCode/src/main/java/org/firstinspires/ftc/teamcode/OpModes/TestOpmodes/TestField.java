package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot;

import java.util.Calendar;

@TeleOp

public class TestField extends BaseMode {

    @Override
    public void doing() {
        robot.fieldView.update();
    }
}
