package org.firstinspires.ftc.teamcode.Modules.Intake.Lift;

public class LiftManager {
    LiftController liftController = new LiftController();
    LiftDeviceListener liftDeviceListener = new LiftDeviceListener();

    LiftListener liftListener = new LiftListener();
    LiftVoltageController voltageController = new LiftVoltageController();

    LiftPosition target = LiftPosition.DOWN;

    public void setTarget(LiftPosition target){
        this.target = target;
    }

    public void init(){
        voltageController.init();
        liftDeviceListener.init();
    }

    private void computePosition(){
      liftDeviceListener.updateValuesMap();
      LiftDevicesValueMap valMap = liftDeviceListener.getValuesMap();

      liftListener.setDevicesValueMap(valMap);
      liftListener.computePosition();
    }

    private void computeVoltage(){
        liftController.setErrSync(liftListener.getErrSync());
        liftController.setPos(liftListener.getPosition());

        liftController.setTargetPos(target.get());
        liftController.computeVoltage();
    }

    private void update(){
        computePosition();
        computeVoltage();
        voltageController.setVoltage(liftController.getUSync(), liftController.getUMove());
    }
}
