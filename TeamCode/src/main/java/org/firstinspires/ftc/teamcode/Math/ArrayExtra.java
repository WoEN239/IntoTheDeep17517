package org.firstinspires.ftc.teamcode.Math;

import java.util.Arrays;

public class ArrayExtra<T> {
    T[] arr;
    public ArrayExtra(T[] arr){
        this.arr = arr;
    }
    public void updateLikeBuffer(T val){
        arr[arr.length - 1] = val;
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[(i + 1)];
        }
    }
    public T findMedian(){
        T [] sortArr = arr.clone();
        Arrays.sort(sortArr);
        return sortArr[sortArr.length/2];
    }
}
