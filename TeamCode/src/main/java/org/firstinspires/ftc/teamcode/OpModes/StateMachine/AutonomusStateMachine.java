package org.firstinspires.ftc.teamcode.OpModes.StateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot;

public class AutonomusStateMachine {
    Robot robot;
    public static boolean isOn = false;
    public void init(Robot robot){
        this.robot = robot;
    }
    State state ;
    State target;
    ElapsedTime timer = new ElapsedTime();

    public void setTarget(State target) {
        this.target = target;
    }

    public void updateState(){
        robot.intake.setTarget(state.getIntakeState());
        robot.positionController.move(state.position);
    }
    public void changeState(){
        robot.intake.setTarget(target.getIntakeState());
        if(timer.seconds()>3) {
            robot.positionController.move(target.position);
            if(robot.positionController.isAtTarget()){
                state = target;
            }
        }

    }
    boolean f = true;

    public void update(){
        if(isOn){
        if(target!=state){
            if(f){
                timer.reset();
            }
            changeState();
            f = false;
        }else {
            updateState();
            f = true;
        }
    }
    }

}
