package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

import org.firstinspires.ftc.teamcode.Devices.LiftHangingMotors;

public class LiftManager {
    LiftController liftController = new LiftController();
    LiftDeviceListener liftDeviceListener = new LiftDeviceListener();

    //TODO
    public LiftListener liftListener = new LiftListener();
    LiftVoltageController voltageController = new LiftVoltageController();

    public LiftPosition target = LiftPosition.DOWN;
    public double position = 0;
    public double errSync = 0;

    public boolean isDone(){
        return Math.abs(position - target.get())<10.0;
    }

    public void setTarget(LiftPosition target){
        this.target = target;
    }

    public void init(){
        voltageController.init();
        liftDeviceListener.init();
    }

    public void computePosition(){
      liftDeviceListener.updateValuesMap();
      LiftDevicesValueMap valMap = liftDeviceListener.getValuesMap();

      liftListener.setDevicesValueMap(valMap);
      liftListener.computePosition();

      position = liftListener.getPosition();
      errSync  = liftListener.getErrSync() ;
    }

    private void computeVoltage(){
        liftController.setErrSync(errSync);
        liftController.setPos(position);

        liftController.setTargetPos(target.get());
        liftController.computeVoltage();
    }



    public void update(){
        computePosition();
        computeVoltage();

        voltageController.setVoltage(liftController.getUSync(), liftController.getUMove());

    }
}
