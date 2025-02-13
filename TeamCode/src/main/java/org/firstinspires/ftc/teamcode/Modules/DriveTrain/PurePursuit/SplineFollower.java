package org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit;

import static com.acmerobotics.roadrunner.Curves.project;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.CompositePositionPath;
import com.acmerobotics.roadrunner.Vector2dDual;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Config
public class SplineFollower {
    public static double localRadius = 0;
    public double angle = 0;
    public CompositePositionPath<Arclength> path;
    private double lastDisplacement = 0;


    public void setPath(CompositePositionPath<Arclength> p){
       path = p;
    }

    public Position getVirtualTarget(Position p){

        double displacement = project(path,p.toRRPosition().position,lastDisplacement);
        lastDisplacement = displacement;

        Robot.telemetryPacket.put("displsm",displacement);

        Vector2dDual<Arclength> target =  path.get(localRadius + displacement ,1);
        Vector2dDual<Arclength> project = path.get(displacement,1);

        double xT = target.x.get(0);
        double yT = target.y.get(0);

        double xP = project.x.get(0);
        double yP = project.y.get(0);


        Robot.telemetryPacket.put("dsplsmt X",xP);
        Robot.telemetryPacket.put("dsplsmt Y",yP);

        Robot.telemetryPacket.put("local rad X",xT);
        Robot.telemetryPacket.put("local rad Y",yT);


        Robot.getInstance().fieldView.circle = new Position(xP,yP,0);

        return new Position(xT,yT,angle);
    }

}
