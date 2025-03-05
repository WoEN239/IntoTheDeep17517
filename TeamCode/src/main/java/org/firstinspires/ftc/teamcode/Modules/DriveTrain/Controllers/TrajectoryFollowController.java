package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;


import com.acmerobotics.roadrunner.PositionPathSeqBuilder;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.LineFollower.LineTrajectorySegment;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.LineFollower.LineSegmentFollower;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.SplineFollower.SplineFollower;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.SplineFollower.SplineTrajectorySegment;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.TrajectorySegment;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Trajectory.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.TaskManager.Task;
import org.firstinspires.ftc.teamcode.Robot.Robot;

import java.util.ArrayList;
import java.util.Arrays;


/*
  Writing by EgorKhvostikov
*/
public class TrajectoryFollowController {

    private  ArrayList<WayPoint>    wayPoints  = new ArrayList<>();
    {
        wayPoints.add(new WayPoint(Robot.myTeam.startPos));
    }

    LineSegmentFollower lineSegmentFollower = new LineSegmentFollower(new LineTrajectorySegment());
    SplineFollower      splineFollower      = new SplineFollower();

    private final ArrayList<TrajectorySegment> trajectory = new ArrayList<>();
    public boolean isEndOfTrajectory = false;

    public void addWayPoints(WayPoint... p) {
        wayPoints.addAll(Arrays.asList(p));

        for (int i = 1; i < wayPoints.size(); i++) {
            if(wayPoints.get(i).isSpline()){
                trajectory.add(
                        new SplineTrajectorySegment(
                        new PositionPathSeqBuilder(
                                wayPoints.get(i-1).position.toRRPosition().position,
                                wayPoints.get(i).beginTangent,1e-6)
                                .splineTo(
                                wayPoints.get(i).position.toRRPosition().position,
                                wayPoints.get(i).endTangent)
                                .build().get(0)
                        )

                );
            }else {
                trajectory.add(new LineTrajectorySegment().
                        makeWithTwoPoint(wayPoints.get(i - 1).position, wayPoints.get(i).position)
                );
            }
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

    public Task getOnPointTask(){
        if(wayPoints.size()==1){
            return wayPoints.get(0).onPointTask;
        }
        return wayPoints.get(1).onPointTask;
    }

    public Task getOnLineTask(){
        if(wayPoints.size()==1){
            return wayPoints.get(0).onLineTask;
        }

        return wayPoints.get(1).onLineTask;
    }


    public void computeTarget() {
        Robot.telemetryPacket.put("way points",wayPoints.toString());
        if(!trajectory.isEmpty()) {
            isEndOfTrajectory = false;
            TrajectorySegment trajectorySegment = trajectory.get(0);
            Position target;

            if(trajectorySegment instanceof LineTrajectorySegment){
                lineSegmentFollower.setTrajectorySegment((LineTrajectorySegment) trajectorySegment);
                lineSegmentFollower.targetAngle = wayPoints.get(1).position.h;

                target = lineSegmentFollower.getVirtualTarget(position);

                onPoint = lineSegmentFollower.isEndNear;

                Robot.getInstance().fieldView.line = ((LineTrajectorySegment) trajectorySegment);
            } else {
                splineFollower.setTrajectorySegment((SplineTrajectorySegment) trajectorySegment);
                splineFollower.targetAngle = wayPoints.get(1).position.h;

                target = splineFollower.getVirtualTarget(position);

                onPoint = splineFollower.isEndNear;

                Robot.getInstance().fieldView.path =  ((SplineTrajectorySegment) trajectorySegment).spline;
            }

            Robot.getInstance().fieldView.circle = target;
            this.target.copyFrom(target);
        }else {
            isEndOfTrajectory = true;
        }
    }

    public void changeTrajectorySegment(){
       if(!trajectory.isEmpty()) {
           wayPoints .remove(0);
           trajectory.remove(0);
       }
    }
}
