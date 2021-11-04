import java.io.*;
import java.util.*;

public class Radix_Merge {
    
       static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }
    static void radixsortMerge(int arr[], int n) //O(nlogn)
    {
        //Radix for merge
        String m = getMax(arr, n)+"";   //String value to count characters
        int mLength= m.toCharArray().length;    //convert m to array of char, then take the length
        int v=1;                            
        for (int j = 0; j < mLength; j++) { //O(d)      //since Radix concept depend in the large digit number, do the following 
          sort(arr, 0 , arr.length-1 , v);//O(nlogn)
          v=v*10;                                //Change the divider every time to *10
        }
    }
     static void sort(int arr[], int l, int r , int v) //Add another parameter to use it in merge()
    {   //Dividing part
        //Binary search concept
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;
 
            // Sort first and second halves
            sort(arr, l, m , v);
            sort(arr, m + 1, r, v);
 
            // Merge the sorted halves
            merge(arr, l, m, r ,v);
        }
    }
    static void merge(int arr[], int l, int m, int r , int v)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1 ; //Starting from the first point to the mid+1  
        int n2 = r - m; //Starting from the mid point to the end
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];//a[0]
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];//// 1, 2, 3, 4
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if ( (L[i]/v)%10 <= (R[j]/v)%10) {          //In every iteration, v and %10 will find units, tens and hundrads of a number
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    static void print(int a[]){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println("");
    }
     public static void main(String[] args)
        {     
            
               int a[]= new int[100];    //initally, with N= 100, TO BE CHANGED MANUALLY
              
              for (int j = 0; j < 100; j++) 
              {    //to be changed for every N                     
                   int range = (100 - 0) + 1;     
                   a[j]=(int)(Math.random() * range);   //generate any random number from 0 -N
                   
              }
                   long startTime = System.nanoTime();   //current value of the system timer
                   radixsortMerge(a, a.length);
                   long duration = System.nanoTime() - startTime;   //Method to calculate running time
                   //print the results
                   print(a);
                   System.out.println(duration);
                   
      
    }
}
