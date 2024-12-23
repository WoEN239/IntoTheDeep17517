package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer.PositionListener;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.IModule;
import org.firstinspires.ftc.teamcode.Robot.Robot;

import java.util.ArrayList;

/*
  Writing by EgorKhvostikov
*/
public class PurePursuit implements IModule {
    PositionController positionController;
    PositionListener positionListener;
    Robot robot;
    private final ArrayList<WayPoint>    wayPoints  = new ArrayList<>();
    private final ArrayList<LineSegment> trajectory = new ArrayList<>();

    public static boolean isOn = false;

    public void addWayPoints(WayPoint... p){
        isOn = true;
        for(WayPoint i : p) {
            wayPoints.add(wayPoints.size() - 1, i);
        }
        for(int i = 1; i< wayPoints.size(); i++){
            trajectory.add(new LineSegment().
            makeWithTwoPoint(wayPoints.get(i-1).position, wayPoints.get(i).position)
            );
        }
    }

    @Override
    public void init(Robot robot) {
        positionController = robot.positionController;
        positionListener = robot.positionListener    ;
        this.robot         = robot                   ;
        wayPoints.add(new WayPoint(positionListener.getPositionGlobal()));
    }

    private boolean isDone = true;
    public boolean isDone(){
        return isDone;
    }

    boolean isFirstLineTask = true;
    boolean isFirstPointTask = true;

    @Override
    public void update() {
        LineSegment nowLineSegment;
        if(!wayPoints.isEmpty()) {
            nowLineSegment = trajectory.get(0);
            LineSegmentFollower.targetLineSegment = nowLineSegment;
            LineSegmentFollower.targetLineAngle = nowLineSegment.end.h;
            LineSegmentFollower.targetEndAngle  = nowLineSegment.end.h;

            isDone = false;

            Position position = positionListener.getPositionGlobal();
            Position target = LineSegmentFollower.getVirtualTarget(position);
            positionController.move(target);

            if(isFirstLineTask) {
                robot.addPPTask(wayPoints.get(0).onLineTask);
                isFirstLineTask = false;
            }

            if(LineSegmentFollower.isEndNear && wayPoints.get(0).onLineTask.isDone()){

                if(isFirstPointTask) {
                    robot.addPPTask(wayPoints.get(0).onPointTask);
                    isFirstPointTask = false;
                }

                if(wayPoints.get(0).onPointTask.isDone()) {
                    isFirstPointTask = true;
                    isFirstLineTask  = true;
                    wayPoints.remove(0);
                }
            }
        }else{
            isDone = true;
        }
    }
}
