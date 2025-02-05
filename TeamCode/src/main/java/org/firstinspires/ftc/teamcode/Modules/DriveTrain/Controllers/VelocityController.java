package org.firstinspires.ftc.teamcode.Modules.DriveTrain.Controllers;


import static org.firstinspires.ftc.teamcode.Modules.DriveTrain.RoadRunner.RobotConstant.yMultiplier;

import org.firstinspires.ftc.teamcode.Math.Position;
import org.firstinspires.ftc.teamcode.Modules.Controller;
import org.firstinspires.ftc.teamcode.Modules.DriveTrain.Devices.DriveMotorsMap;
import org.firstinspires.ftc.teamcode.Robot;

/**
 * Writing by EgorKhvostikov
 */

public class VelocityController implements Controller {
    Robot robot;
    private final Position target = new Position(0, 0, 0);
    DriveMotorsMap motorsMap = new DriveMotorsMap();
    public static boolean isUpdate = false;
    @Override
    public void init(Robot robot) {
        this.robot = robot;
        motorsMap.init(robot);

    }

    public void move(Position target) {
        this.target.x = target.x;
        this.target.y = target.y;
        this.target.h = target.h;
        isUpdate = true;
    }

    @Override
    public void update() {
        motorsMap.update();
        if (isUpdate) {
            motorsMap.rightBackDrive.setVel   (target.x + target.y*yMultiplier - target.h);
            motorsMap.rightForwardDrive.setVel(target.x - target.y*yMultiplier - target.h);
            motorsMap.leftBackDrive.setVel    (target.x - target.y*yMultiplier + target.h);
            motorsMap.leftForwardDrive.setVel (target.x + target.y*yMultiplier + target.h);
        }
    }
}
