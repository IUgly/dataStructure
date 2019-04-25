package datastructure.sorting;

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

    private void bubbleSort(int[] array){
        
    }

    public static void main(String[] args) {
        int[] array = {9,8,7,5,3,7,7,2,4,5,6};
        new Sort().insertSorting(array);
        System.out.println(Arrays.toString(array));
    }
}
