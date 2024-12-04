package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Math.Filter;
import org.firstinspires.ftc.teamcode.Math.FilterStatus;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class Motor {
    public DcMotorEx dev;
    private double velocity = 0;
    private double position = 0;

    private MotorDirection dir = MotorDirection.FORWARD;

    private String name;

    public void init(String name, HardwareMap map) {
        this.dev = map.get(DcMotorEx.class, name);
        dev.setDirection(DcMotorSimple.Direction.FORWARD);
        dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.name = name;
        filter.setName(name);
        pidF.setName(name + " F");
        pidB.setName(name + " B");
    }

    public void update() {
        updatePos();
        updateVel();
    }

    public void setDir(MotorDirection d) {
        dir = d;
    }

    public PidStatus pidStatusF = new PidStatus(0, 0, 0, 0,0,0,0, 0, 0);
    Pid pidF = new Pid(pidStatusF);

    public PidStatus pidStatusB = new PidStatus(0, 0, 0, 0,0,0,0, 0, 0);
    Pid pidB = new Pid(pidStatusB);

    public FilterStatus filterStatus = new FilterStatus(6, 150, 30, 0.5, 0.1, 0.3);
    Filter filter = new Filter().init(filterStatus);

    public void setVel(double velTar) {
        pidF.setPos(velocity);
        pidF.setTarget(velTar);
        pidF.update();
        double uF = pidF.getU();
        pidB.setPos(velocity);
        pidB.setTarget(velTar);
        pidB.update();
        double uB = pidB.getU();

        double u;
        if (velTar * dir.dir >= 0) {
            u = uF;
        } else {
            u = uB;
        }
        setVoltage(u);
    }

    public void setPower(double power) {
        dev.setPower(power * dir.dir);
    }

    public void setVoltage(double voltage) {
        double u = 0;
        if(Robot.voltage!=0) {
            u = ((voltage) / (Robot.voltage));
        }
        Robot.telemetry.addData("Voltage "+name+" ", voltage);
        setPower(u);
    }

    private void updatePos() {
        position = dir.dir * dev.getCurrentPosition();
    }

    private void updateVel() {
        filter.setPos(position);
        filter.update();
        velocity = filter.getVelocity();
    }

    public double getVelocity() {
        return velocity;
    }

    public double getPosition() {
        return position;
    }
}
