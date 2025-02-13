package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;

import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.CompositePositionPath;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.SplineFollower;

import java.util.ArrayList;
import java.util.List;

public class PedroPedroController {
    List<CompositePositionPath<Arclength>> trajectory = new ArrayList<>();
    SplineFollower splineFollower = new SplineFollower();

    public void addTrajectory(List<CompositePositionPath<Arclength>> t){
        trajectory.addAll(t);
    }

    private final Position position = new Position();
    public void setPosition(Position p) {this.position.copyFrom(p);}

    private final Position target   = new Position();
    public Position getPidTarget() {return target;}

    public void computeTarget(){
        if(trajectory.isEmpty()){
            return;
        }

        splineFollower.setPath(trajectory.get(0));
        target.copyFrom(splineFollower.getVirtualTarget(position));
    }
}
