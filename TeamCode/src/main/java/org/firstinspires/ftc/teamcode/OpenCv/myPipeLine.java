package org.firstinspires.ftc.teamcode.OpenCv;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;
import java.util.ArrayList;

@Config

public class myPipeLine extends OpenCvPipeline {


    public Mat frame      = new Mat();
    public Mat binaryMaskUnshaped = new Mat();
    public Mat binaryMask = new Mat();


    public Mat hvsMaskYellow    = new Mat();
    public Mat hvsMaskRed    = new Mat();
    public Mat hvsMaskBlue    = new Mat();

    public Mat binaryMaskYellow = new Mat();
    public Mat binaryMaskBlue = new Mat();
    public Mat binaryMaskRed = new Mat();

    public static RangeHvsConfig hvsMaxYellow = new RangeHvsConfig();
    public static RangeHvsConfig hvsMinYellow = new RangeHvsConfig();

    public static RangeHvsConfig hvsMaxRed = new RangeHvsConfig();
    public static RangeHvsConfig hvsMinRed = new RangeHvsConfig();

    public static RangeHvsConfig hvsMaxBlue = new RangeHvsConfig();
    public static RangeHvsConfig hvsMinBlue = new RangeHvsConfig();

    public static int idAprilTag = 1;
    public boolean isAprilTag = false;
    public boolean isObject = false;

    AprilTagProcessor aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
    ArrayList<AprilTagDetection> aprilTagDetections = new ArrayList<>();
    Point aprilTagCenters = new Point();
    Point objectCentre = new Point();
    @Override
    public Mat processFrame(Mat input) {
        frame = input;
        findAprilTag();
        doMask();
        findCentreObject();
        return input;
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
    public void findCentreObject(){
        Moments m =  Imgproc.moments(binaryMask,true);
        if(m.m00 >  30) {
            objectCentre.x = m.m10 / m.m00;
            objectCentre.y = m.m01 / m.m00;
            isObject = true;
        }else{
            isObject = false;
        }
    }
    public void doMask(){
        Imgproc.cvtColor(frame,hvsMaskBlue,  Imgproc.COLOR_RGBA2BGR);
        Imgproc.cvtColor(frame,hvsMaskRed,   Imgproc.COLOR_RGBA2BGR);
        Imgproc.cvtColor(frame,hvsMaskYellow,Imgproc.COLOR_RGBA2BGR);
        Imgproc.cvtColor(frame,hvsMaskBlue,  Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(frame,hvsMaskRed,   Imgproc.COLOR_BGR2HSV);
        Imgproc.cvtColor(frame,hvsMaskYellow,Imgproc.COLOR_BGR2HSV);

        Core.inRange(hvsMaskRed,hvsMinRed.toScalar(),
                                hvsMaxRed.toScalar(),
                    binaryMaskRed);

        Core.inRange(hvsMaskBlue,hvsMinBlue.toScalar(),
                                 hvsMaxBlue.toScalar(),
                    binaryMaskBlue);

        Core.inRange(hvsMaskYellow,hvsMinYellow.toScalar(),
                                   hvsMaxYellow.toScalar(),
                    binaryMaskYellow);
        if(Robot.myTEAM == Robot.TEAM.BLUE) {
            Core.bitwise_or(binaryMaskBlue,binaryMaskYellow, binaryMaskUnshaped);
        }else{
            Core.bitwise_or(binaryMaskRed,binaryMaskYellow, binaryMaskUnshaped);
        }
        binaryMask = binaryMaskUnshaped.rowRange(
                ((int) binaryMaskUnshaped.size().width/4)*2,((int) binaryMaskUnshaped.size().width/4)*3
        );
    }
}
