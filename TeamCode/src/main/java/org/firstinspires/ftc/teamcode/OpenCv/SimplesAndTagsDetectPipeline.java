package org.firstinspires.ftc.teamcode.OpenCv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

/*
  Writing by EgorKhvostikov
*/


public class SimplesAndTagsDetectPipeline extends OpenCvPipeline {
    private static SimplesAndTagsDetectPipeline instance = new SimplesAndTagsDetectPipeline();
    public static SimplesAndTagsDetectPipeline getInstance() {
        return instance;
    }

    public Mat frame = new Mat();

    Point[] c1 ;
    Point[] c2 ;
    @Override
    public Mat processFrame(Mat input) {
        List<MatOfPoint> contr = new ArrayList<>();
        Mat h = new Mat();
        Mat bin = new Mat();
        Imgproc.cvtColor(input,bin,Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(bin,bin,new Size(PipeLineConfig.blurA,PipeLineConfig.blurB),0,0);
        Imgproc.adaptiveThreshold(bin,bin,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,
                PipeLineConfig.tresh, PipeLineConfig.c);


        Imgproc.morphologyEx(bin,bin,Imgproc.MORPH_OPEN,Mat.ones(PipeLineConfig.kernelOpenB,PipeLineConfig.kernelOpenA, CvType.CV_32F));
        Imgproc.morphologyEx(bin,bin,Imgproc.MORPH_HITMISS,Mat.ones(PipeLineConfig.kernelCloseB,PipeLineConfig.kernelCloseA, CvType.CV_32F));
        //        Imgproc.findContours(bin,contr,h,Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
//
//
//        if(contr.isEmpty()){
//            return input;
//        }
////
//        contr.sort((c1, c2) -> (int) (Imgproc.contourArea(c1)-Imgproc.contourArea(c2)));
//
//        c1 = contr.get(contr.size()-1).toArray();
//        c2 = contr.get(contr.size()-2).toArray();
//
//        Point[] c  = new Point[c1.length+c2.length];
//        System.arraycopy(c1,0,c,0,c1.length);
//        System.arraycopy(c2,0,c,c1.length,c2.length);
//
//        double eps = PipeLineConfig.epsK*Imgproc.arcLength(new MatOfPoint2f(c),false);
//        MatOfPoint2f approx = new MatOfPoint2f();
//        Robot.telemetry.addData("Contour elements",approx.toArray().length);
//        Robot.telemetry.update();//
//        Imgproc.approxPolyDP(new MatOfPoint2f(c),approx,eps,false);
//
//        List<MatOfPoint> appCont = new ArrayList<>();
//        appCont.add(new MatOfPoint(approx.toArray()));
//
//        RotatedRect rect =  Imgproc.minAreaRect(new MatOfPoint2f(c));
//
//        Point[] vert = new Point[4];
//        rect.points(vert);
//
//        List<MatOfPoint> boxCont = new ArrayList<>();
//        boxCont.add(new MatOfPoint(vert));
////
//        Imgproc.drawContours(input,boxCont, -1, new Scalar(0,255,0),5);
//        Imgproc.drawContours(input,contr, -1, new Scalar(255,255,255),5);
//        Imgproc.drawContours(input,appCont, -1, new Scalar(255,0,255),5);
//        //Imgproc.circle(input,rect.center,10,new Scalar(0,0,255),5);
//        Imgproc.putText(input,""+rect.angle,rect.center,Imgproc.FONT_HERSHEY_COMPLEX,1,new Scalar(255,0,0),1);

        return bin ;
    }


}
