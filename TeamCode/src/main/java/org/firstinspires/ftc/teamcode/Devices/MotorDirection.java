package org.firstinspires.ftc.teamcode.Devices;

public enum MotorDirection {
    FORWARD(1),BACK(-1),NONE(0);
    int dir = 0;
    MotorDirection(int d){dir = d;}
}
