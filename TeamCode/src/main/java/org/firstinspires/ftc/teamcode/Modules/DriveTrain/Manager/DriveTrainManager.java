package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Manager;

import org.firstinspires.ftc.teamcode.Robot.TaskManager.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.TaskManager;

public class DriveTrainManager extends DriveTrain{
    public enum RobotState {
        POINT,TRAVELING,PEDRO_PEDRO ,TELE_OP
    }
    private RobotState state = RobotState.TRAVELING;
    public void setState(RobotState state) {this.state = state;}

    private void changeState(RobotState state){ this.state = state; isNeedToAddTask = true;}
    private boolean isNeedToAddTask = true;

    public void update(){
        Robot.telemetryPacket.put("Robot move state",state.toString());
        switch (state){
            case POINT:
                setDriveTrainState(DriveTrain.DriveTrainState.PID_CONTROL);
                PurePursuitTask task1 = trajectoryFollowController.getOnPointTask();

                if (isNeedToAddTask) {
                    isNeedToAddTask = false;
                    TaskManager.getInstance().addTask(task1);
                }

                if(task1.isDone() && task1.isRunOnce && !trajectoryFollowController.isEndOfTrajectory){
                    trajectoryFollowController.changeTrajectorySegment();
                    trajectoryFollowController.computeTarget();
                    changeState(RobotState.TRAVELING);
                }
                break;

            case TRAVELING:
                setDriveTrainState(DriveTrain.DriveTrainState.PURE_PURSUIT);
                PurePursuitTask task2 = trajectoryFollowController.getOnLineTask();

                if(isNeedToAddTask) {
                    isNeedToAddTask = false;
                    TaskManager.getInstance().addTask(task2);
                }

                if((task2.isRunOnce && task2.isDone() && trajectoryFollowController.onPoint()) || trajectoryFollowController.isEndOfTrajectory){
                    changeState(RobotState.POINT);
                }
                break;

            case TELE_OP:
                setDriveTrainState(DriveTrainState.TELE_OP);
                break;
        }
        moveUpdate();
    }

}
