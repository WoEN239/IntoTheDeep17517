package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PositionViewer.PositionViewer;
import org.firstinspires.ftc.teamcode.Modules.TypesOfModules.IModule;
import org.firstinspires.ftc.teamcode.Robot.Robot;

import java.util.ArrayList;

/*
  Writing by EgorKhvostikov
*/
public class PurePursuit implements IModule {
    PositionController positionController;
    PositionViewer     positionViewer    ;
    private final ArrayList<Position> trajectory = new ArrayList<>();
    public static boolean isOn = false;

    public void addWayPoint(Position p){
        isOn = true;
        trajectory.add(trajectory.size()-1, p);
    }

    @Override
    public void init(Robot robot) {
        positionController = robot.positionController;
        positionViewer     = robot.positionViewer    ;
    }
    private boolean isDone = true;
    public boolean isDone(){
        return isDone;
    }
    @Override
    public void update() {
        if(trajectory.size()>1) {
            LineSegment nowLineSegment = new LineSegment().makeWithTwoPoint(trajectory.get(0),trajectory.get(1));
            isDone = false;

            LineSegmentFollower.targetLineSegment = nowLineSegment;
            Position target = LineSegmentFollower.getVirtualTarget(positionViewer.getPositionRealGlobal());

            positionController.move(target);
            if(LineSegmentFollower.isEndNear){
                trajectory.remove(0);
            }
        }else{
            isDone = true ;
        }

    }
}
