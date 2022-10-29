package com.java.ds;

public class BinSearchTests {

    static int binarySearch(int arr[], int low, int high, int key)
    {
        if (high < low)
            return -1;

        /* low + (high - low)/2; */
        int mid = (low + high)/2;
        if (key == arr[mid])
            return mid;
        if (key > arr[mid])
            return binarySearch(arr, (mid + 1), high, key);
        return binarySearch(arr, low, (mid -1), key);
    }

    public static void main(String[] args) {
        //有序数组
        int[]array={1,2,3,4,5,6,7,8,9};
        int i = binarySearch(array, 0, array.length - 1, 8);
        System.out.println(i);
    }
}
