package org.firstinspires.ftc.teamcode.OpenCv;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import java.util.ArrayList;

public class myPipeLine extends OpenCvPipeline {
    public Mat frame      = new Mat();
    public Mat hvsMask    = new Mat();
    public Mat binaryMask = new Mat();
    public static int idAprilTag = 1;
    public static RangeHvsConfig hvsMax = new RangeHvsConfig();
    public static RangeHvsConfig hvsMin = new RangeHvsConfig();
    public boolean isAprilTag = false;
    AprilTagProcessor aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
    ArrayList<AprilTagDetection> aprilTagDetections = new ArrayList<>();
    Point aprilTagCenters = new Point();
    @Override
    public Mat processFrame(Mat input) {
        findAprilTag();
        doMask();
        return binaryMask;
    }
    public void findAprilTag(){
        aprilTagDetections =  aprilTagProcessor.getDetections();
        for (AprilTagDetection d: aprilTagDetections) {
            if(d.id == idAprilTag){
                isAprilTag = true;
                aprilTagCenters = d.center;
            }else {
                isAprilTag = false;
            }
        }
    }
    public void doMask(){
        Imgproc.cvtColor(frame,hvsMask,Imgproc.COLOR_BGR2HSV);
        Core.inRange(hvsMask,hvsMin.toScalar(),
                             hvsMax.toScalar(),binaryMask);
    }
}
