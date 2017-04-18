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
public class ComplexSorts {
    
    public static void mergeSort(int[] array, boolean ascending){
        int[] temp = new int[array.length];
        recMergeSort(array, ascending, temp, 0,array.length-1);
    }
    public static void recMergeSort(int[] array, boolean ascending, int[] temp,
            int left, int right){
        if(left == right){
            return;
        }
        else{
            int mid = (left+right)/2;
            recMergeSort(array,ascending, temp,left,mid);
            recMergeSort(array,ascending, temp,mid+1,right);
            merge(array, ascending, temp, left, mid+1, right);
        }
    }
    //arrayi goes from left to mid exclusive,
    //arrayj goes from mid to right inclusive
    public static void merge(int[] array, boolean ascending, int[] temp,
            int left, int mid, int right){
        int i = left;
        int j = mid;
        int tempIndex = left;
        
        while(true){
            
            //Run out of leftside elements, place j in leftover places
            if(i >= mid){
                while(j <= right){
                    temp[tempIndex] = array[j];
                    tempIndex++;
                    j++;
                }
                break;
            }
            //Run out of rightside elements, place i in leftover places
            if(j > right){
                while(i < mid){
                    temp[tempIndex] = array[i];
                    tempIndex++;
                    i++;
                }
                break;
            }
            
            //merge i and j into temp
            if(ascending){
                if(array[i] <= array[j]){
                    temp[tempIndex] = array[i];
                    i++;
                }
                //j is smaller
                else{
                    temp[tempIndex] = array[j];
                    j++;
                }
                tempIndex++;
            }
            else{
                if(array[i] >= array[j]){
                    temp[tempIndex] = array[i];
                    i++;
                }
                //j is bigger
                else{
                    temp[tempIndex] = array[j];
                    j++;
                }
                tempIndex++;
            }
        }
        //done merging, place temp into array
        for(int x = left; x <= right; x++){
            array[x] = temp[x];
        }
    }
    
    public static void quickSort(int[] array, boolean ascending){
        quickSortHelper(array,0,array.length-1, ascending);
    }
    public static void quickSortHelper(int[] array, int L, int R, boolean ascending){
        if(L < R){
            int split = partition(array,L,R, ascending);
            quickSortHelper(array,L,split-1, ascending);
            quickSortHelper(array,split+1,R, ascending);
        }
    }
    public static int partition(int[] array, int L, int R, boolean ascending){
        //begin partitioning
        int pivot = L;
        int i = L;
        int j = R;
        
        while(i < j){
            if(ascending){
                //look for first value that is greater than the pivot 
                // starting from the left
                while(array[i] < array[pivot] && i < R){
                    i++;
                }
                // look for first value that is less than the pivot 
                // starting from the right
                while(array[j] > array[pivot] && j > L){
                    j--;
                }
            }
            else{
                //look for first value that is greater than the pivot 
                // starting from the left
                while(array[i] > array[pivot] && i < R){
                    i++;
                }
                // look for first value that is less than the pivot 
                // starting from the right
                while(array[j] < array[pivot] && j > L){
                    j--;
                }
            }
            //swap misplaced values
            swap(array,i,j);
        }
        //undo last swap that broke the loop
        swap(array,i,j);
        swap(array,L,j);// move pivot into the middle
        return j; //return position pivot is at
    }
    public static void swap(int[] array, int indexI, int indexJ){
        int holder = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = holder;
    
    }
}
