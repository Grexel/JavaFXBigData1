/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

/**
 *
 * @author Jeff
 */
public class SimpleSorts {
    
    public static void selectionSort(int[] array){
        int minimumIndex;
        int holder;
        for(int i = 0; i < array.length -1; i++){
            minimumIndex = i;
            for(int j = i+1; j < array.length; j++){
                if(array[j] < array[minimumIndex]){
                    minimumIndex = j;
                }
            }
            holder = array[i];
            array[i] = array[minimumIndex];
            array[minimumIndex] = holder;
        }
    }
    public static void insertionSort(int[] array){
        for(int i = 0; i < array.length; i++){
            int holder;
            int index = i;
            while(index > 0){
                if(array[index] < array[index-1]){
                    holder = array[index -1];
                    array[index - 1] = array[index];
                    array[index] = holder;
                }
                else{
                    break;
                }
                index--;
            }
        }
    }
    public static void bubbleSort(int[] array, boolean ascending){
        boolean noSwaps = false;
        while(!noSwaps){
            //reset the no swap flag
            noSwaps = true;
            for(int i = 0; i < array.length - 1; i++){
                if(ascending){
                    if(array[i] > array[i+1]){
                        noSwaps = false;
                        swap(array,i,i+1);
                    }
                }
                else{
                    if(array[i] < array[i+1]){
                        noSwaps = false;
                        swap(array,i,i+1);
                    }
                }
            }
        }
    }
    public static void swap(int[] array, int indexI, int indexJ){
        int holder = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = holder;
    
    }
}
