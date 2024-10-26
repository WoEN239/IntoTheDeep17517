package org.firstinspires.ftc.teamcode.Math;

import java.util.Arrays;

public class ArrayExtra {

    public static double[] updateLikeBuffer(double val,double [] arr){
        arr[arr.length - 1] = val;
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[(i + 1)];
        }
        return arr;
    }
    public static double findMedian(double [] arr){
        double [] sortArr = arr.clone();
        Arrays.sort(sortArr);
        return sortArr[sortArr.length/2];
    }
}
