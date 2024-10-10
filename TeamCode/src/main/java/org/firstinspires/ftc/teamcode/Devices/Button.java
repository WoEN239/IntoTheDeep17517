package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Button {
    private boolean now = false;
    private boolean old = false;
    public Button(){
    }
    public boolean update(boolean button){
        now = button;
        boolean indicator = (now != old) && now;
        old = now;
        return indicator;
    }
}