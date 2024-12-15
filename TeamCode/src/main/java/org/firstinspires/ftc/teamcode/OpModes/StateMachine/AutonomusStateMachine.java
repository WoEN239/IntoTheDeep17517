package org.firstinspires.ftc.teamcode.OpModes.StateMachine;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robot.Robot;
/*
  Writing by EgorKhvostikov
*/
public class AutonomusStateMachine {
    //Видимо бесполезно, учитываяя PP
    Robot robot;
    public static boolean isOn = false;
    public void init(Robot robot){
        this.robot = robot;
    }
    AutonomusState state ;
    AutonomusState target;
    ElapsedTime timer = new ElapsedTime();

    public void setTarget(AutonomusState target) {
        this.target = target;
    }

    public void updateState(){
        robot.intake.setTarget(state.data.intakeState);
        robot.positionController.move(state.data.position);
    }
    public void changeState(){
        robot.intake.setTarget(target.data.intakeState);
        if(timer.seconds()>3) {
            robot.positionController.move(target.data.position);
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
