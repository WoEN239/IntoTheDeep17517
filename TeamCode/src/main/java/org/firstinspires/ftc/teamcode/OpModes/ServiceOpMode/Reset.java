package org.firstinspires.ftc.teamcode.OpModes.ServiceOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OpModes.BaseMode;

@TeleOp

public class Reset extends BaseMode {

    @Override
    public void loopRun() {
        System.exit(0);
    }
}
