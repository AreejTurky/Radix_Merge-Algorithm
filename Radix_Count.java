import java.io.*;
import java.util.*;

public class Radix_Count {
    
    static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }
    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(int arr[], int n, int exp)    //O(n)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);      //initally, all the element with zero 
 
        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;
        
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
 
        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }
    
    static void RadixCount(int arr[], int n){ //Radix for counting
        int k= getMax(arr, n);
        for (int exp = 1; k / exp > 0; exp=exp*10)//O(n)
            countSort(arr, n, exp);
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
                   long startTime = System.nanoTime();   //Method to calculate running time
                   RadixCount(a, a.length);
                   long duration = System.nanoTime() - startTime;
                   //print the results
                   print(a);
                   System.out.println(duration);
                   
      
    }
}

