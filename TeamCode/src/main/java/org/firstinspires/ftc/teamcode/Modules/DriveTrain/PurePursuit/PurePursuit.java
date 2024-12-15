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
    private  ArrayList<WayPoint> trajectory = new ArrayList<>();
    public static boolean isOn = false;

    public void addWayPoints(WayPoint... p){
        isOn = true;
        for(WayPoint i : p) {
            trajectory.add(trajectory.size() - 1, i);
        }
    }

    @Override
    public void init(Robot robot) {
        positionController = robot.positionController;
        positionListener = robot.positionListener    ;
        this.robot         = robot                   ;
        trajectory.add(new WayPoint(positionListener.getPositionGlobal()));
    }
    private boolean isDone = true;
    public boolean isDone(){
        return isDone;
    }
    @Override
    public void update() {
        LineSegment nowLineSegment;
        if(trajectory.size()>1) {
            nowLineSegment = new LineSegment().makeWithTwoPoint(trajectory.get(0).position,trajectory.get(1).position);
            LineSegmentFollower.targetLineSegment = nowLineSegment;
            isDone = false;

            Position position = positionListener.getPositionGlobal();
            Position target = LineSegmentFollower.getVirtualTarget(position);
            positionController.move(target);

            robot.addToQueue(trajectory.get(0).onLineTask);

            if(LineSegmentFollower.isEndNear){
                robot.addToQueue(trajectory.get(0).onPointTask);
                if(trajectory.get(0).onPointTask.isDone()) {
                    trajectory.remove(0);
                }
            }
        }else{
            isDone = true ;
        }
    }
}
