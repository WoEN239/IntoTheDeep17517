package org.firstinspires.ftc.teamcode.Modules.DriveTrain;

import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.maxAccel;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.maxAngSpeed;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.maxLinSpeed;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.minAccel;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.trackWidth;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.wheelDiameter;
import static org.firstinspires.ftc.teamcode.Robot.RobotConstant.yMultiplier;

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
import com.acmerobotics.roadrunner.TimeTurn;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilderParams;
import com.acmerobotics.roadrunner.VelConstraint;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Math.Position;
import java.util.Arrays;
import java.util.List;

/**
 * Writing by EgorKhvostikov
 */

@Config
public class RoadRunner  {
    ElapsedTime timer = new ElapsedTime();

    private final MecanumKinematics kinematics = new MecanumKinematics(trackWidth, yMultiplier, wheelDiameter);
    public final VelConstraint velConstraint = new MinVelConstraint(Arrays.asList(
            kinematics.new WheelVelConstraint(maxLinSpeed),
            new AngularVelConstraint(maxAngSpeed)
    ));
    public static double kpSlide = 0;
    public static double kpTurn = 0;
    public static double kpForward = 0;
    public final AccelConstraint accelConstraint = new ProfileAccelConstraint(minAccel, maxAccel);
    public final HolonomicController holonomicController = new HolonomicController(kpForward, kpSlide, kpTurn);
    public List<Trajectory> trajectories;
    public List<TimeTurn>   turns;

    public void moveToTrajectory(List<Trajectory> trajectories) {
        this.trajectories = trajectories;
        timer.reset();
        move();
    }

    public TrajectoryBuilder builder() {
        return new TrajectoryBuilder(new TrajectoryBuilderParams(1e-6,
                new ProfileParams(1e-6, 1e-6, 1e-6)),
              new Pose2d(0,0,0),
                0, velConstraint, accelConstraint
        );
    }
    public void turn(List<TimeTurn> t){
        turns = t;
    }

    private void move() {

        if (!trajectories.isEmpty()) {
            Trajectory     trajectoryNow = trajectories.get(0);
            TimeTrajectory timeTrajectory = new TimeTrajectory(trajectoryNow);
            double duration = timeTrajectory.duration;
            Pose2dDual<Time> target = timeTrajectory.get(timer.seconds());
          //  PoseVelocity2dDual<Time> velTarget = holonomicController.compute(target, pose, velocity);

            if (timer.seconds() > duration) {
                timer.reset();
                trajectories.remove(0);
            }
        } else if (!turns.isEmpty()) {
            TimeTurn turnNow = turns.get(0);
            double duration = turnNow.duration;
            Pose2dDual<Time> target = turnNow.get(timer.seconds());
        //    PoseVelocity2dDual<Time> velTarget = holonomicController.compute(target, pose, velocity);
            if (timer.seconds() > duration) {
                timer.reset();
                trajectories.remove(0);
            }
        }
    }
}
