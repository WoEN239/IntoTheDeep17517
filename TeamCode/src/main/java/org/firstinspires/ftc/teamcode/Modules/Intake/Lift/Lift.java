package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.Button;
import org.firstinspires.ftc.teamcode.Devices.Motor;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Modules.Listener;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by @MrFrosty1234
 */

public class Lift implements Controller {
    LiftPosition liftPosition;
    Robot robot;

    Motor liftLeftMotor;
    Motor liftRightMotor;

    public DigitalChannel buttonDown;

    private double voltage;

    public LiftPosition targetPosition = LiftPosition.DOWN;

    public void setTargetPosition(LiftPosition targetPosition){
        this.targetPosition = targetPosition;
    }

    private boolean liftAtTarget = true;


    public PidStatus pidStatus;
    Pid pid = new Pid(pidStatus);
    @Override
    public void init(Robot robot) {

    }

    public Lift() {
        this.robot = new Robot(robot.opMode);
        liftLeftMotor.dev = robot.devicePool.liftHangingMotors.liftLeftMotor;
        liftRightMotor.dev = robot.devicePool.liftHangingMotors.liftRightMotor;

        voltage = 12;
    }

    public void reset() {
        liftRightMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRightMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftLeftMotor.dev.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeftMotor.dev.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRightMotor.dev.setDirection(DcMotorSimple.Direction.FORWARD);

        liftLeftMotor.dev.setDirection(DcMotorSimple.Direction.REVERSE);
    }

   Button down = new Button();

    private boolean downSwitch = false;

    public boolean getDownSwitch(){
        return downSwitch;
    }

    void updateSwitch(){
        downSwitch = down.update(buttonDown.getState());
    }

    public void setPower(double power){
        liftLeftMotor.setPower(power);
        liftRightMotor.setPower(power);
    }

    public void moveUp(){
        setTargetPosition(LiftPosition.UP);
    }

    public void moveDown(){
        setTargetPosition(LiftPosition.DOWN);
    }

    public void move(LiftPosition position){
        setTargetPosition(position);
    }

    public double getEnc(){
        double leftM = liftLeftMotor.dev.getCurrentPosition();
        double rightM = liftRightMotor.dev.getCurrentPosition();
        return (leftM + rightM) / 2;
    }

    int liftLeftOffSet = 0;
    int liftRightOffSet = 0;

    double pos = 0;

    double encErr;

    private int getLeftPos(){
        return liftLeftMotor.dev.getCurrentPosition() - liftLeftOffSet;
    }

    private int getRightPos(){
        return liftRightMotor.dev.getCurrentPosition() - liftRightOffSet;
    }

    private int getPos(){
        return  (getLeftPos() + getRightPos()) / 2;
    }

    private double power = 0;


    public void updatePos(){
        updateSwitch();
        pos = getPos();
        if(getDownSwitch())
            encErr = getEnc() - LiftPosition.DOWN.get();
    }

    private boolean isManual = true;
    private double manPower = 0;

    public void manual(double power){
        isManual = true;
        manPower = power;
    }

    private void auto() {
        isManual = false;
    }

    public boolean isLiftAtTarget(){
        return liftAtTarget;
    }

    public void updateLift(){
        updatePos();
        pid = new Pid(pidStatus);

        if(pos > -10){
            if(targetPosition != LiftPosition.DOWN){
                power = pid.getU();
                liftAtTarget = abs(targetPosition.get() - getPos()) < 15;
            }
            else{
                liftAtTarget = buttonDown.getState();
                power = !liftAtTarget ? 1 : 0;
            }
        }
        else{
            power = 0.1;
        }
        if(isManual){
            setPower(manPower);
        }
        else{
            setPower(power);
        }
    }

}
