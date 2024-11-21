package org.firstinspires.ftc.teamcode.OpModes.StateMachine;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;

public enum AutonomusState {
    START         (new State(IntakeState.WAIT_DOWN,new Position())),
    SCORING_AXE   (new State(IntakeState.WAIT_DOWN,new Position())),
    SCORING_BASKET(new State(IntakeState.WAIT_DOWN,new Position()));
    final State data;
    AutonomusState(State data){
        this.data = data;
    }
}