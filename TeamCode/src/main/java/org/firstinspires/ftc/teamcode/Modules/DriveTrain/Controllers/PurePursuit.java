package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegment;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegmentFollower;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;

import java.util.ArrayList;
import java.util.Arrays;

/*
  Writing by EgorKhvostikov
*/
public class PurePursuit {
    private final ArrayList<WayPoint>    wayPoints  = new ArrayList<>();
    {
        wayPoints.add(new WayPoint(Robot.myTeam.startPos));
    }
    private final ArrayList<LineSegment> trajectory = new ArrayList<>();
    public boolean isEndOfTrajectory = false;

    public void addWayPoints(WayPoint... p){

        wayPoints.addAll(Arrays.asList(p));

        for(int i = 1; i < wayPoints.size(); i++){
            trajectory.add(new LineSegment().
            makeWithTwoPoint(wayPoints.get(i-1).position, wayPoints.get(i).position)
            );
        }
    }

    private final Position position = new Position();
    public void setPosition(Position p) {this.position.copyFrom(p);}

    private final Position target   = new Position();
    public Position getPidTarget() {return target;}

    private boolean onPoint = false;
    public  boolean onPoint(){return onPoint;}

    public PurePursuitTask getOnPointTask(){
        if(wayPoints.size()==1){
            return wayPoints.get(0).onPointTask;
        }
        return wayPoints.get(1).onPointTask;
    }

    public PurePursuitTask getOnLineTask(){
        if(wayPoints.size()==1){
            return wayPoints.get(0).onLineTask;
        }
        return wayPoints.get(1).onLineTask;
    }


    public void computeTarget() {
        LineSegment nowLineSegment;
        if(!trajectory.isEmpty()) {
            isEndOfTrajectory = false;
            nowLineSegment = trajectory.get(0);
            FieldView.packet.fieldOverlay().
                    strokeLine(nowLineSegment.start.x, nowLineSegment.start.y,
                            nowLineSegment.end.x, nowLineSegment.end.y);


            LineSegmentFollower.targetLineSegment = nowLineSegment;
            LineSegmentFollower.targetLineAngle = wayPoints.get(1).position.h;
            LineSegmentFollower.targetEndAngle  = wayPoints.get(1).position.h;

            Position target = LineSegmentFollower.getVirtualTarget(position);
            onPoint = LineSegmentFollower.isEndNear;
            this.target.copyFrom(target);
        }else {
            isEndOfTrajectory = true;
        }
    }

    public void changeTrajectorySegment(){
       if(!trajectory.isEmpty()) {
           wayPoints.remove(0);
           trajectory.remove(0);
       }
    }
}
