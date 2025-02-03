package org.firstinspires.ftc.teamcode.OpModes.Simulatiom;

import android.app.appsearch.PropertyPath;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.CompositePosePath;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.Internal;
import com.acmerobotics.roadrunner.PathBuilder;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PositionPath;
import com.acmerobotics.roadrunner.QuinticSpline1d;
import com.acmerobotics.roadrunner.QuinticSpline2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.DriveTrainManager;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;
import org.firstinspires.ftc.teamcode.Telemetry.FieldView;


@Config
@TeleOp
public class SplineSimulations extends BaseSimulation{
    public static Position start = new Position();
    public static Position end   = new Position();
    public static Position target = new Position();
    private Position position = new Position();

    @Override
    public void callRun() {
        QuinticSpline2d spline2d = new QuinticSpline2d(
                new QuinticSpline1d(new DualNum<>(new double[]{0, 0, 0, 0}),
                        new DualNum<>(new double[]{0, 0, 110, 100})),
                new QuinticSpline1d(new DualNum<>(new double[]{0, 0, 0, 0}),
                        new DualNum<>(new double[]{0, 0, 110, 100}))
        );
        for(double i =0; i<1; i+= 0.01){
           // FieldView.packet.fieldOverlay().fillCircle(spline2d.x.get(i,1).get(1),spline2d.y.get(i,1).get(1),1);
        }
        isNeedToCall = false;
    }

    @Override
    public void loopRun(){
//        position = DriveTrainSimulation.position;
//        FieldView.position = position;
//
//        robot.driveTrain.setState(DriveTrainManager.RobotState.POINT);
//        robot.driveTrain.setManualTarget(target);
//
//        FieldView.circle   = target;
//        FieldView.updateField();
//        QuinticSpline2d spline2d = new QuinticSpline2d(
//               new QuinticSpline1d(new DualNum<>(new double[]{0, 0, 0, 0}),
//                       new DualNum<>(new double[]{0, 0, 110, 100})),
//               new QuinticSpline1d(new DualNum<>(new double[]{0, 0, 0, 0}),
//                       new DualNum<>(new double[]{0, 0, 110, 100}))
//        );


    }

}

