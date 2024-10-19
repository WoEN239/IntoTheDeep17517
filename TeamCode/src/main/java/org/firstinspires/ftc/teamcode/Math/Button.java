package org.firstinspires.ftc.teamcode.Math;

/**
 * Writing by EgorKhvostikov
 */

public class Button {
    private boolean old = false;

    public Button() {
    }

    public boolean update(boolean button) {
        boolean indicator = (button != old) && button;
        old = button;
        return indicator;
    }
}