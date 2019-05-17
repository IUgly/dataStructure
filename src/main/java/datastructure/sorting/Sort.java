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


    /**
     *
     * @param array 原数组
     * @param newArray  新数组（归并后的数组
     * @param firstSequenceHeadRow  第一个有序序列的第一个元素下标
     * @param firstSequenceLastRow  第一个有序序列最后一个元素下标
     * @param secondSequenceLastRow 第二个有序序列最后一个下标
     */
    public void merge(int[] array, int[] newArray, int firstSequenceHeadRow,
                      int firstSequenceLastRow, int secondSequenceLastRow){
        int i = firstSequenceHeadRow;
        int j = firstSequenceLastRow + 1;
        int k = firstSequenceHeadRow;
        while (i <= firstSequenceHeadRow && j <= secondSequenceLastRow){
            newArray[k++] = (array[i] < array[j]) ? array[i++] : array[j++];
        }
        while (i < firstSequenceLastRow){
            newArray[k++] = array[i++];
        }
        while (j <= secondSequenceLastRow){
            newArray[k++] = array[j++];
        }
    }
    /**
     * 一趟归并排序
     * @param r    原数组，需要归并操作的数组
     * @param r1 新数组，归并好的数组
     * @param h 步长多少(有序序列中含有的元素个数)
     * @param n    数组长度(数组下标的最大值)
     */
    public void mergePass(int[] r, int[] r1, int h, int n){
        //判断根据步长能分成多少个有序序列
        int i = 0;
        //待归并的两个相邻有序序列的长度均为h
        while ((i+ 2*h -1) <= n){
            merge(r, r1, i, i+h-1, i+ 2*h -1);
            i += 2 *h;
        }
        //说明最后还有两个序列，第一个序列长度为h，第二个序列长度小于h
        if (i +h -1 < n){
            merge(r, r1, i, i+h-1, n);
            i += 2*h;
        }else {
            for (; i<=n; i++){
                r1[i] = r[i];
            }
        }
    }

    /**
     * 二分归并排序非递归算法
     * @param r
     * @param r1
     * @param n
     */
    public void mergeSort(int[] r, int[] r1, int n){
        int h = 1;
        //直到h>=n才结束，也就步长小于n时都要进行归并
        while (h < n){
            //一趟归并
            mergePass(r, r1, h, n);
            h = 2 * h;
            //因为经过一趟归并后，r1就变为了那个需要归并的数组，
            // 那么r就充当新数组，并且这也能够让排序好的数组放回到r数组中
            mergePass(r1, r, h, n);
            h = 2 * h;
        }
    }

    public static void main(String[] args) {
        int[] array = {9,8,7,5,3,7,7,2,4,5,6};
//        new Sort().quickSort(array, 0 ,array.length - 1);
//        new Sort().shellSort(array);
        int[] newArray = new int[12];
        new Sort().mergeSort(array, newArray, 1);
        System.out.println(Arrays.toString(array));
    }
}
