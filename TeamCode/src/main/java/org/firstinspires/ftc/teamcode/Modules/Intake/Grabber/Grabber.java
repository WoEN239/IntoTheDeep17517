package org.firstinspires.ftc.teamcode.Modules.Intake.Grabber;

import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Intake.Intake;
import org.firstinspires.ftc.teamcode.Modules.Intake.Grabber.Transfer.Transfer;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Writing by @MrFrosty1234
 */

public class Grabber {
   Robot robot;
   Intake intake = new Intake();
   Transfer transfer = new Transfer();
   public void init(Robot robot){
       this.robot = robot;
       intake.init(robot);
       transfer.init(robot);
   }
   public void update(){
       intake.update();
       transfer.update();
   }
}
