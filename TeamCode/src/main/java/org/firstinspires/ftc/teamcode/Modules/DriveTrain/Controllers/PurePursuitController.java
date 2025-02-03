package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;


import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegment;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegmentFollower;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.PurePursuitTask;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.Team;

import java.util.ArrayList;
import java.util.Arrays;


/*
  Writing by EgorKhvostikov
*/
public class PurePursuitController {

    private  ArrayList<WayPoint>    wayPoints  = new ArrayList<>();
    {
        wayPoints.add(new WayPoint(Robot.myTeam.startPos));
    }

    LineSegmentFollower lineSegmentFollower = new LineSegmentFollower(new LineSegment());

    private final ArrayList<LineSegment> trajectory = new ArrayList<>();
    public boolean isEndOfTrajectory = false;

    public void addWayPoints(WayPoint... p) {
        wayPoints.addAll(Arrays.asList(p));

        for (int i = 1; i < wayPoints.size(); i++) {
            trajectory.add(new LineSegment().
                    makeWithTwoPoint(wayPoints.get(i - 1).position, wayPoints.get(i).position)
            );
        }
    }
    public void resetPoints(){
        isEndOfTrajectory = false;
        wayPoints = new ArrayList<>();
        wayPoints.add(new WayPoint(Robot.myTeam.startPos));
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
        Robot.telemetryPacket.put("way points",wayPoints.toString());
        LineSegment nowLineSegment;
        if(!trajectory.isEmpty()) {
            isEndOfTrajectory = false;
            nowLineSegment = trajectory.get(0);


            lineSegmentFollower.targetLineSegment = nowLineSegment;
            lineSegmentFollower.targetLineAngle = wayPoints.get(1).position.h;
            lineSegmentFollower.targetEndAngle  = wayPoints.get(1).position.h;

            Position target = lineSegmentFollower.getVirtualTarget(position);

            onPoint = lineSegmentFollower.isEndNear;
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
