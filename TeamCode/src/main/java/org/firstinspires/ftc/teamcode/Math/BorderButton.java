package org.firstinspires.ftc.teamcode.Math;

/**
 * Writing by EgorKhvostikov
 */

public class BorderButton {
    private boolean old = false;

    public BorderButton() {
    }

    public boolean get(boolean button) {
        boolean indicator = (button != old) && button;
        old = button;
        return indicator;
    }
}