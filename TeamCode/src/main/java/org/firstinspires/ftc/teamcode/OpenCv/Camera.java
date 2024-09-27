package org.firstinspires.ftc.teamcode.OpenCv;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Modules.IModule;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class Camera implements IModule {
    public OpenCvWebcam camera;
    AprilTagProcessor aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
    VisionPortal visionPortal;
    public static int WIDTH = 480;
    public static int HEIGHT = 480;
    public void init(Robot robot) {
        int cameraMonitorViewId = robot.hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id", robot.hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(
                robot.hardwareMap.get(WebcamName.class, "Webcam 1"),
                cameraMonitorViewId);
        camera.setPipeline(robot.pipeLine);
        visionPortal = VisionPortal.easyCreateWithDefaults(robot.hardwareMap.get(WebcamName.class, "Webcam 1"),
                                                            aprilTagProcessor);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                Robot.telemetry.addLine("camera successful init");
            }

            @Override
            public void onError(int errorCode) {
                Robot.telemetry.addData("camera init failed", errorCode);
            }
        });
    }
    public void startStream(){
        camera.startStreaming(WIDTH,HEIGHT, OpenCvCameraRotation.SIDEWAYS_LEFT);
        FtcDashboard.getInstance().startCameraStream(camera,60);
    }

}
