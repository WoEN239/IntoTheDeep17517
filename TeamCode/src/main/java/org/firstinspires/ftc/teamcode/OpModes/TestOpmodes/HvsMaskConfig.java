package org.firstinspires.ftc.teamcode.OpModes.TestOpmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.OpModes.Simulatiom.BaseSimulation;

@Autonomous(group = "Test")
public class HvsMaskConfig extends BaseSimulation {
    boolean isFirst = true;
    @Override
    public void loopRun() {
        if(isFirst){
            isFirst = false;
            //robot.camera.init(robot);
        }
    }
}
