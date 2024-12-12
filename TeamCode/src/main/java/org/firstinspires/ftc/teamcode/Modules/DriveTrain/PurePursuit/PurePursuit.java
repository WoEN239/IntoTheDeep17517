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
    private final ArrayList<LineSegment> trajectory = new ArrayList<>();
    public static boolean isOn = false;

    public void addSegment(LineSegment l){
        isOn = true;
        trajectory.add(trajectory.size()-1, l);
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
        if(!trajectory.isEmpty()) {
            LineSegment nowLineSegment = trajectory.get(0);
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
