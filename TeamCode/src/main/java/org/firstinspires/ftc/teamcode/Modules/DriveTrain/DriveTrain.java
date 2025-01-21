package org.firstinspires.ftc.teamcode.Modules.DriveTrain;
import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.DriveTrainVoltageController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PositionPidController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.PurePursuit;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers.VelocityPidController;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.DeviceValueMap;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener.DevicePositionListener;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener.LocalPositionListener;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.PositionListener.PositionListener;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.VelocityListener.DeviceVelocityListener;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.VelocityListener.LocalVelocityListener;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Listeners.VelocityListener.VelocityListener;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegment;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.LineSegmentFollower;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.PurePursuit.WayPoint;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.RobotSimulation.DriveTrainSimulation;

abstract class DriveTrain {

    private final Position pidTarget = new Position();
    public Position getPidTarget() {return pidTarget;}
    //TODO private
    public final Position pidPositionResult = new Position();
    public final Position position      = new Position().copyFrom(Robot.myTeam.startPos);  //actual robot position
    public final Position localVelocity = new Position();
    public final Position localPosition = new Position();

    protected enum DriveTrainState {PURE_PURSUIT,PID_CONTROL,TELE_OP}
    private DriveTrainState driveTrainState = DriveTrainState.PID_CONTROL;
    protected void setDriveTrainState(DriveTrainState driveTrainState) {this.driveTrainState = driveTrainState;}

    public void init(){
        devicePositionListener.init();
        deviceVelocityListener.init();
        driveTrainVoltageController.init();
        positionListener.init();
        purePursuit.resetPoints();

        pidPositionResult.copyFrom(new Position());
        pidTarget     .copyFrom(new Position());
        position      .copyFrom(Robot.myTeam.startPos);
        localVelocity .copyFrom(new Position());
        localPosition .copyFrom(new Position());

        localPositionListener.reset();
        LineSegmentFollower.targetLineSegment = new LineSegment();
    }

    protected void moveUpdate() {
        if(!Robot.isDebug) {
            computePosition();
            computeVelocity();
        }

        switch (driveTrainState){
            case PID_CONTROL:
                pidTarget.copyFrom(manualTarget);
                setVoltages();
                break;
            case PURE_PURSUIT:
                purePursuit.setPosition(position);
                purePursuit.computeTarget();
                pidTarget.copyFrom(purePursuit.getPidTarget());
                setVoltages();
                break;
            case TELE_OP:
                setVoltagesFromVelocity();
                break;
        }

        if(Robot.isDebug) {
            DriveTrainSimulation.velocity = pidPositionResult;
            DriveTrainSimulation.updatePosition();
        }
    }


    private final Position manualTarget = new Position();
    public void setManualPosition(Position p) {this.manualTarget .copyFrom(p);}
    public void addWayPoints(WayPoint... t){purePursuit.addWayPoints(t);}

    DriveTrainVoltageController driveTrainVoltageController = new DriveTrainVoltageController();

    DevicePositionListener devicePositionListener   = new DevicePositionListener ();
    LocalPositionListener  localPositionListener    = new LocalPositionListener  ();
    PositionListener       positionListener         = new PositionListener       ();

    DeviceVelocityListener deviceVelocityListener   = new DeviceVelocityListener();
    LocalVelocityListener  localVelocityListener    = new LocalVelocityListener ();
    VelocityListener       velocityListener         = new VelocityListener      ();

    private void computePosition(){
        driveTrainVoltageController.updateData();
        devicePositionListener.updateValuesMap();

        DeviceValueMap deviceValue = devicePositionListener.getValuesMap();
        localPositionListener.setDeviceValue(deviceValue);
        localPositionListener.computePosition();

        Position delta = localPositionListener.getDeltaPos();
        positionListener.setDeltaPos(delta);
        positionListener.computePosition();

        localPosition.copyFrom(localPositionListener.getLocalPositions());
        position.copyFrom(positionListener.getPosition());
    }

    private void computeVelocity(){
        driveTrainVoltageController.updateData();
        deviceVelocityListener.updateValuesMap();

        DeviceValueMap deviceValue = deviceVelocityListener.getValuesMap();
        localVelocityListener.setDeviceValue(deviceValue);
        localVelocityListener.computeVelocity();

        Position localVelocity = localVelocityListener.getVelocity();
        velocityListener.setLocalVelocity(localVelocity);
        velocityListener.setH(position.h);
        velocityListener.computeVelocity();

        this.localVelocity.copyFrom(localVelocity);
    }

    protected PurePursuit purePursuit = new PurePursuit();
    PositionPidController  positionPidController    = new PositionPidController();
    VelocityPidController  velocityPidController    = new VelocityPidController();
    protected void setVoltages(){
        if(Robot.isDebug){
            localPosition.copyFrom(DriveTrainSimulation.localPosition);
            position.copyFrom(DriveTrainSimulation.position);
        }
        positionPidController.setLocalPosition(localPosition);
        positionPidController.setGlobalPosition(position);
        positionPidController.setTarget(pidTarget);
        positionPidController.computePidResult();

        Position pidResult = positionPidController.getPidResult();
        pidPositionResult.copyFrom(pidResult);

        velocityPidController.setVelocity(localVelocity);
        velocityPidController.setTarget(pidResult);
        velocityPidController.computePidResult();
        if(!Robot.isDebug){
            Position voltageMap = velocityPidController.getPidResult();
            driveTrainVoltageController.setVoltage(voltageMap);
        }
    }

    private Position velocityTarget = new Position();
    public void setVelocityTarget(Position velocityTarget) {
        this.velocityTarget = velocityTarget;
    }

    protected void setVoltagesFromVelocity(){
        velocityPidController.setVelocity(localVelocity);
        velocityPidController.setTarget(velocityTarget);
        velocityPidController.computePidResult();
        if(!Robot.isDebug){
            Position voltageMap = velocityPidController.getPidResult();
            driveTrainVoltageController.setVoltage(voltageMap);
        }
    }

}
