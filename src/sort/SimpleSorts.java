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
    
    public static void selectionSort(int[] array, boolean ascending){
        int minimumIndex;
        int holder;
        for(int i = 0; i < array.length -1; i++){
            
            //For ascending sort, this will be the smallest value,
            //For descending sort, this will be the largest value.
            minimumIndex = i;
            
            for(int j = i+1; j < array.length; j++){
                if(ascending){
                    if(array[j] < array[minimumIndex]){
                        minimumIndex = j;
                    }
                }
                else{
                    if(array[j] > array[minimumIndex]){
                        minimumIndex = j;
                    }
                }
            }
            swap(array,i,minimumIndex);
        }
    }
    public static void insertionSort(int[] array, boolean ascending){
        for(int i = 0; i < array.length; i++){
            int holder;
            int index = i;
            while(index > 0){
                if(ascending){
                    if(array[index] < array[index-1]){
                        swap(array,index, index-1);
                    }
                    else{
                        break;
                    }
                }
                else{
                    if(array[index] > array[index-1]){
                        swap(array,index, index-1);
                    }
                    else{
                        break;
                    }
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
