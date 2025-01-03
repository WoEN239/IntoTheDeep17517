package org.firstinspires.ftc.teamcode.Modules.DriveTrain;

import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.TaskManager;

public class DriveTrainManager extends DriveTrain{
    public enum RobotState {
        POINT,TRAVELING
    }
    private RobotState state = RobotState.TRAVELING;
    public void setState(RobotState state) {this.state = state;}

    private boolean manualPidMode = false;
    public void setManualPidMode(boolean manualPidMode) {this.manualPidMode = manualPidMode;}

    private void changeState(RobotState state){ this.state = state; isNeedToAddTask = true;}
    private boolean isNeedToAddTask = true;

    public void update(){
        Robot.telemetry.addData("Robot move state",state.toString());
        switch (state){
            case POINT:
                if(manualPidMode) {
                    setDriveTrainState(DriveTrain.DriveTrainState.PID_CONTROL);
                }
                PurePursuitTask task1 = purePursuit.getOnPointTask();
                if (isNeedToAddTask) {
                    isNeedToAddTask = false;
                    TaskManager.getInstance().addTask(task1);
                }

                if(task1.isDone() && task1.isRunOnce){
                    purePursuit.changeTrajectorySegment();
                    changeState(RobotState.TRAVELING);
                }

                break;

            case TRAVELING:
                setDriveTrainState(DriveTrain.DriveTrainState.PURE_PURSUIT);
                PurePursuitTask task2 = purePursuit.getOnLineTask();

                if(isNeedToAddTask) {
                    isNeedToAddTask = false;
                    TaskManager.getInstance().addTask(task2);
                }
                if(task2.isRunOnce && task2.isDone() && purePursuit.onPoint()){
                    changeState(RobotState.POINT);
                }
                break;
        }

        moveUpdate();
    }

}
