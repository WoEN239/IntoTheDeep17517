package org.firstinspires.ftc.teamcode.Devices;

public enum MOTOR_DIRECTION {
    FORWARD(1),BACK(-1),NONE(0);
    int dir = 0;
    MOTOR_DIRECTION(int d){dir = d;}
}
