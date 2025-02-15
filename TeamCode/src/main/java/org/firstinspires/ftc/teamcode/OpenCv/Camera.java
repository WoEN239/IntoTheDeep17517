package org.firstinspires.ftc.teamcode.OpenCv;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.OpModes.BaseMode;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

/*
  Writing by EgorKhvostikov
*/
public class Camera {
    public OpenCvWebcam camera;
    public static int WIDTH = 640;
    public static int HEIGHT = 480;

    public void init(Robot robot) {
        int cameraMonitorViewId = robot.opMode.hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id", robot.opMode.hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(
                robot.opMode.hardwareMap.get(WebcamName.class, "Webcam 1"),
                cameraMonitorViewId);
        camera.setPipeline(SimplesAndTagsDetectPipeline.getInstance());
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                Robot.telemetryPacket.addLine("camera successful init");
                startStream();
            }

            @Override
            public void onError(int errorCode) {
                Robot.telemetryPacket.put("camera init failed", errorCode);
            }
        });
    }

    private void startStream() {
        camera.startStreaming(WIDTH, HEIGHT, OpenCvCameraRotation.SIDEWAYS_LEFT);
        FtcDashboard.getInstance().startCameraStream(camera, 30);
    }

}
