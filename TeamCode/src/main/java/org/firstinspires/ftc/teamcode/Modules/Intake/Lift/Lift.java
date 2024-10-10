package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Devices.Button;
import org.firstinspires.ftc.teamcode.Math.Pid;
import org.firstinspires.ftc.teamcode.Math.PidStatus;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;
public class Lift implements IModule {
    LiftPosition liftPosition;
    Robot robot;

    DcMotor liftLeftMotor;
    DcMotor liftRightMotor;

    public DigitalChannel buttonDown;

    private double voltage;

    public LiftPosition targetPosition = LiftPosition.DOWN;

    public void setTargetPosition(LiftPosition targetPosition){
        this.targetPosition = targetPosition;
    }

    private boolean liftAtTarget = true;

    public static double kP = 0;
    public static double kI = 0;
    public static double kD = 0;
    public static double kF = 0;
    public static double maxI = 0;

    PidStatus pidStatus = new PidStatus(kP, kI, kD, kF, maxI, 0);
    Pid pid = new Pid(pidStatus);
    @Override
    public void init(Robot robot) {

    }

    public Lift() {
        this.robot = new Robot(robot.opMode);
        liftLeftMotor = robot.devicePool.liftHangingMotors.liftLeftMotor;
        liftRightMotor = robot.lift.liftRightMotor;

        voltage = 12;
    }

    public void reset() {
        liftRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        liftLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
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
        double leftM = liftLeftMotor.getCurrentPosition();
        double rightM = liftRightMotor.getCurrentPosition();
        return (leftM + rightM) / 2;
    }

    int liftLeftOffSet = 0;
    int liftRightOffSet = 0;

    double pos = 0;

    double encErr;

    private int getLeftPos(){
        return liftLeftMotor.getCurrentPosition() - liftLeftOffSet;
    }

    private int getRightPos(){
        return liftRightMotor.getCurrentPosition() - liftRightOffSet;
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
