package datastructure.sorting;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author kuangjunlin
 */
public class Sort {
    private void selectSorting(int[] array){
        int length = array.length -1;
        for (int i = 0; i < length; i++) {

            for (int j = i+1; j < array.length ; j++) {
                if (array[j] < array[i]){
                    int emp  = array[j];
                    array[j] = array[i];
                    array[i] = emp;
                }
            }
        }
    }

    private void insertSorting(int[] array){
        int temp,j;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i-1]){
                temp = array[i];
                for (j = i -1; j >= 0 && temp < array[j] ; j--) {
                    array[j+1] = array[j];
                }
                array[j+1] = temp;
            }
        }
    }

    /**
     * 希尔排序
     * @param array
     */
    private void shellSort(int array[]){
        int i, j, temp;
        int length = array.length;
        int gap = length;
        do {
            gap = gap/2;
            for (i = gap; i< length ; i++){
                if (array[i] < array[i-gap]){
                    temp = array[i];
                    for (j = i-gap; j>=0 && array[j]>temp; j-=gap){
                        array[j+gap] = array[j];
                    }
                    array[j+gap] = temp;
                }
            }
        }while (gap > 0 );
    }

    private void bubbleSort(int[] array){
        int j = array.length - 1;
        while (j >= 1){
            for (int i = 0; i < j; i++) {
                if (array[i] > array[i+1]){
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
            }
            j--;
        }
    }

    /**
     * 快速排序原理：选取一个轴值(比较的基准)，
     * 将待排序记录分为独立的两个部分，
     * 左侧记录都是小于或等于轴值，右侧记录都是大于或等于轴值，
     * 然后分别对左侧部分和右侧部分重复前面的过程，也就是左侧部分又选择一个轴值，
     * 又分为两个独立的部分，这就使用了递归了
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] array, int left, int right){
        int temp = array[left];
        while (left < right){
            while (temp <= array[right] && left < right){
                right --;
            }
            if (left < right){
                array[left] = array[right];
                left ++;
            }

            while (temp >= array[left] && left < right){
                left ++;
            }
            if (left < right){
                array[right] = array[left];
                right--;
            }
        }
        array[left] = temp;
        return left;
    }
    private void quickSort(int[] array, int left, int right){
        if (array == null || left >= right || array.length <= 1) {
            return;
        }
        int mid = partition(array, left, right);
        quickSort(array, left, mid);
        quickSort(array, mid+1, right);
    }


    public static void main(String[] args) {
        int[] array = {9,8,7,5,3,7,7,2,4,5,6};
//        new Sort().quickSort(array, 0 ,array.length - 1);
        new Sort().shellSort(array);
        System.out.println(Arrays.toString(array));
    }
}
