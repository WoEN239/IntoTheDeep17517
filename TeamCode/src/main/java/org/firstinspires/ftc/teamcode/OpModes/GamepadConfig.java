package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadConfig{
    Gamepad gamepad;

    public GamepadConfig (Gamepad  gamepad){
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
    protected double rightStickX;
    protected double rightStickY;
    protected double leftStickX;
    protected double leftStickY;

    protected boolean dropSamples;
    protected boolean grabSamples;

    protected boolean highBasket;
    protected boolean lowBasket;

    protected boolean toDown;

    protected boolean toLowAxis;
    protected boolean toHighAxis;

    protected boolean waitDown;
    protected boolean waitUp;
    protected boolean waitEat;
}
