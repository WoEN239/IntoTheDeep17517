package org.firstinspires.ftc.teamcode.Modules.Intake;

/*
 Writing by EgorKhvostikov
*/

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Modules.Intake.BrushChain.BrushChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.GripChain.GripChainManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftController;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftDeviceListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftListener;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftManager;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftPosition;
import org.firstinspires.ftc.teamcode.Modules.Intake.Lift.LiftVoltageController;
import org.firstinspires.ftc.teamcode.Robot.Robot;

public class IntakeManager {
    private BrushChainManager brushChainManager = new BrushChainManager();
    private GripChainManager  gripChainManager  = new GripChainManager();
    private IntakeModules modules = new IntakeModules();

    private IntakeState state = IntakeState.MOVE;
    private IntakeState target = IntakeState.MOVE;




    public enum IntakeState{
        MOVE, BRUSH,GRIP
    }

    public void init(){
        modules.init();

        brushChainManager.setModules(modules);
        gripChainManager.setModules(modules);

        brushChainManager.initTasks();
        gripChainManager .initTasks();
    }

    public void startBrushEat(){
        brushChainManager.startEat();
        target = IntakeState.BRUSH;
        state = IntakeState.BRUSH;
    }

    public void endBrushEat(){
        brushChainManager.endEat();
    }

    public void endScoreBrush(){
        brushChainManager.score();
    }

    LiftPosition liftTarget = LiftPosition.DOWN;
    public void updateState(){
        switch (state){
            case MOVE:
                modules.transfer.normal();
                modules.brush.up();
                modules.brush.off();
                modules.brush.openWall();
                modules.grip.in();
                modules.grip.close();
                modules.innerTransfer.in();
                break;
            case BRUSH:
                brushChainManager.update();
                break;
            case GRIP:
                modules.transfer.normal();
                modules.brush.off();
                modules.brush.openWall();

                gripChainManager.update();
                break;
        }
    }

    public void changeState(){
        switch (target){
            case MOVE:
                state = target;
                break;
            case GRIP:
                brushChainManager.endEat();
                brushChainManager.update();
                if(brushChainManager.isDone()){
                    state = IntakeState.GRIP;
                }
                break;
            case BRUSH:
                gripChainManager.endEat();
                gripChainManager.update();
                if(gripChainManager.isDone()){
                    state = IntakeState.BRUSH;
                }
                break;
        }
    }
    ElapsedTime timer = new ElapsedTime();
    boolean isDone = false;
    private boolean f = true;
    public void update(){
        Robot.telemetryPacket.put("State :", state.toString());
        Robot.telemetryPacket.put("Target state :", target.toString());
        if(target!=state){
            if(f){
                timer.reset();
            }
            changeState();
            f = false;
            isDone = false;
        }else {
            updateState();
            f = true;
            isDone = true;
        }
    }

}

