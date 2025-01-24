package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadConfig{
    Gamepad gamepad;

    public void update(Gamepad  gamepad){
        this.gamepad = gamepad;
    }
    public void read(){

        rightStickX = gamepad.right_stick_x;
        rightStickY = gamepad.right_stick_y;
        leftStickX = gamepad.left_stick_x;
        leftStickY = gamepad.left_stick_y;

        dropSamples = gamepad.left_bumper;
        grabSamples = gamepad.right_bumper;

        highBasket = gamepad.dpad_up;
        lowBasket = gamepad.dpad_down;

        toDown = gamepad.square;

        toLowAxis = gamepad.cross;
        toHighAxis = gamepad.triangle;

        waitDown = gamepad.triangle;
        waitUp   = gamepad.cross;
        waitEat  = gamepad.circle;
    }
    protected double rightStickX = 0;
    protected double rightStickY = 0;
    protected double leftStickX = 0;
    protected double leftStickY = 0;

    protected boolean dropSamples = false;
    protected boolean grabSamples = false;

    protected boolean highBasket = false;
    protected boolean lowBasket = false;

    protected boolean toDown = false;

    protected boolean toLowAxis = false;
    protected boolean toHighAxis = false;

    protected boolean waitDown = false;
    protected boolean waitUp = false;
    protected boolean waitEat = false;
}
