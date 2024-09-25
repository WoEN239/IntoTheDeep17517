package org.firstinspires.ftc.teamcode.OpenCv;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera implements IModule {
    OpenCvWebcam camera;

    public void init(Robot robot) {
        int cameraMonitorViewId = robot.hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id", robot.hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(
                robot.hardwareMap.get(WebcamName.class, "Webcam 1"),
                cameraMonitorViewId);
        camera.setPipeline(robot.pipeLine);
    }

}
