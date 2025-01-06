package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

public class LiftManager {
    LiftController liftController = new LiftController();
    LiftDeviceListener liftDeviceListener = new LiftDeviceListener();
    LiftDevices devices = new LiftDevices();
    LiftListener liftListener = new LiftListener();

    LiftPosition target = LiftPosition.DOWN;

    public void setTarget(LiftPosition target){
        this.target = target;
    }

    public void init(){
        liftDeviceListener.init();
    }
    public void computePosition(){
      liftDeviceListener.updateValuesMap();
      liftListener.computePosition();
    }
    public void computeVoltage(){
        computePosition();
        liftController.setErrSync(liftListener.errSync);
        liftController.setPos(liftListener.getPosition());
        liftController.setTargetPos(target.get());
        liftController.computeVoltage();
    }
}
