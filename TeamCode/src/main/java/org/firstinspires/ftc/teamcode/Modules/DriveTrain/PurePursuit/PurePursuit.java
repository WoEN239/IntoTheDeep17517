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
    private ArrayList<Line> trajectory = new ArrayList<>();
    public static boolean isOn = false;

    public void addSegment(Line l){
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
            Line nowLine = trajectory.get(0);
            isDone = false;

            LineFollower.targetLine = nowLine;
            Position target = LineFollower.getVirtualTarget(positionViewer.getPositionRealGlobal());

            positionController.move(target);
            if(LineFollower.isEndNear){
                trajectory.remove(0);
            }
        }else{
            isDone = true ;
        }

    }
}
