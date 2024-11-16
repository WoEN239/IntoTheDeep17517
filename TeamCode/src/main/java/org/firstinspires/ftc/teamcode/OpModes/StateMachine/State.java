package org.firstinspires.ftc.teamcode.OpModes.StateMachine;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Intake.StateMachine.IntakeState;

public class State {
    IntakeState intakeState = IntakeState.WAIT_DOWN;
    Position    position    = new Position(0,0,0);

    public State(IntakeState intakeState, Position position) {
        this.intakeState = intakeState;
        this.position = position;
    }

    public void setIntakeState(IntakeState intakeState) {
        this.intakeState = intakeState;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public IntakeState getIntakeState() {
        return intakeState;
    }

    public Position getPosition() {
        return position;
    }



}
