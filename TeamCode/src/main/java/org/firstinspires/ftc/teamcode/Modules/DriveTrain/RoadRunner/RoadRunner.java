package org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner;

import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.*;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.HolonomicController;
import com.acmerobotics.roadrunner.MecanumKinematics;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.PoseVelocity2dDual;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.ProfileParams;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.TimeTrajectory;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilderParams;
import com.acmerobotics.roadrunner.VelConstraint;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Robot;

import java.util.Arrays;
import java.util.List;


@Config
public class RoadRunner {
    Robot robot;
    ElapsedTime timer = new ElapsedTime();

    public RoadRunner(Robot robot){
        this.robot = robot;
    }
    private final MecanumKinematics kinematics = new MecanumKinematics(trackWidth,xMultiplier,wheelDiameter);
    public final VelConstraint velConstraint  = new MinVelConstraint(Arrays.asList(
            kinematics. new WheelVelConstraint(maxLinSpeed),
                        new AngularVelConstraint(maxAngSpeed)
    ));
    public static double kpSlide = 0;
    public static double kpTurn = 0;
    public static double kpForward = 0;
    public final AccelConstraint accelConstraint = new ProfileAccelConstraint(minAccel,maxAccel);
    public final HolonomicController holonomicController = new HolonomicController(kpForward,kpSlide,kpTurn);
    public List<Trajectory> trajectories;
    public void moveToTrajectory(List<Trajectory> trajectories){
        this.trajectories = trajectories;
        timer.reset();
        move();
    }
    public TrajectoryBuilder builder(){
        return new TrajectoryBuilder(new TrajectoryBuilderParams(1e-6,
                new ProfileParams(1e-6,1e-6,1e-6)),
                robot.positionViewer.getPositionGlobal().toRRPose(),
      0,velConstraint,accelConstraint
                                    );
    }

    private void move(){
        Pose2d         pose     = robot.positionViewer.getPositionGlobal().toRRPose();
        PoseVelocity2d velocity = robot.velocityViewer.getLocalViewer().getVelocityLocal().toRRVelocity();
        if(!trajectories.isEmpty()){
            Trajectory trajectoryNow = trajectories.get(0);
            TimeTrajectory timeTrajectory = new TimeTrajectory(trajectoryNow);
            double duration = timeTrajectory.duration;
            Pose2dDual<Time> target = timeTrajectory.get(timer.seconds());
            PoseVelocity2dDual<Time> velTarget = holonomicController.compute(target,pose,velocity);
            robot.velocityController.move(Position.fromRRVelocity(velTarget));

            if(timer.seconds()>duration){
                timer.reset();
                trajectories.remove(0);
            }
        }
    }
}