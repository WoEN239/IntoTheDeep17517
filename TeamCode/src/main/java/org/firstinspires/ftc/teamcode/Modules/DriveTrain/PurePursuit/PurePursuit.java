package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer.PositionListener;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.IModule;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

import java.util.ArrayList;
import java.util.Arrays;

/*
  Writing by EgorKhvostikov
*/
public class PurePursuit implements IModule {
    public static Position startPosition = new Position();

    PositionController positionController;
    PositionListener positionListener;
    Robot robot;
    private final ArrayList<WayPoint>    wayPoints  = new ArrayList<>();
    private final ArrayList<LineSegment> trajectory = new ArrayList<>();

    public static boolean isOn = false;

    public void addWayPoints(WayPoint... p){
        isOn = true;
        wayPoints.addAll(Arrays.asList(p));

        for(int i = 1; i < wayPoints.size(); i++){
            trajectory.add(new LineSegment().
            makeWithTwoPoint(wayPoints.get(i-1).position, wayPoints.get(i).position)
            );
        }
    }

    @Override
    public void init(Robot robot) {
        positionController = robot.positionController;
        positionListener   = robot.positionListener  ;
        this.robot         = robot                   ;
        wayPoints.add(new WayPoint(startPosition))   ;
    }

    private boolean isDone = true;
    public boolean isDone(){
        return isDone;
    }

    boolean isFirstLineTask = true;
    boolean isFirstPointTask = true;
    boolean lineTask = false;

    @Override
    public void update() {
        if(isOn){
        LineSegment nowLineSegment;
        Robot.telemetry.addData("Line Task",lineTask);
        if(!trajectory.isEmpty()) {
            nowLineSegment = trajectory.get(0);
            FieldView.packet.fieldOverlay().
                    strokeLine(nowLineSegment.start.x, nowLineSegment.start.y,
                            nowLineSegment.end.x, nowLineSegment.end.y);


            LineSegmentFollower.targetLineSegment = nowLineSegment;
            LineSegmentFollower.targetLineAngle = wayPoints.get(1).position.h;
            LineSegmentFollower.targetEndAngle  = wayPoints.get(1).position.h;

            isDone = false;

            Position position = positionListener.getPositionGlobal();
            Position target = LineSegmentFollower.getVirtualTarget(position);
            positionController.move(target);

            if(isFirstLineTask) {
                robot.updatePPTasks();
                robot.addPPTask(wayPoints.get(1).onLineTask);
                robot.updatePPTasks();
                isFirstLineTask = false;
            }

            if(wayPoints.get(1).onLineTask.isDone()){
                wayPoints.get(1).onLineTask.setDoneChecker(()->true);
            }

            if(LineSegmentFollower.isEndNear && wayPoints.get(1).onLineTask.isDone()){
                if(isFirstPointTask) {
                   robot.updatePPTasks();
                   robot.addPPTask(wayPoints.get(1).onPointTask);
                   robot.updatePPTasks();
                   isFirstPointTask = false;
                }

                if(wayPoints.get(1).onPointTask.isDone()){
                    wayPoints.get(1).onPointTask.setDoneChecker(()->true);
                }

                if(wayPoints.get(1).onPointTask.isDone()) {
                    isFirstPointTask = true;
                    isFirstLineTask  = true;
                    wayPoints .remove(0);
                    trajectory.remove(0);
                }
            } 
        }else{
            isDone = true;
        }
    }
    }
}
