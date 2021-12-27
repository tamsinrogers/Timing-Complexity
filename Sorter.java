/**
 * File: Sorter.java
 * Author: Tamsin Rogers
 * Date: 3/3/20
 */
 
import java.util.Random;
import java.util.ArrayList;
import java.util.*; 
import java.util.Scanner; 

/* implements insert sort for an array of type long 
sorts the values in the data array in ascending order using insert sort */
public class Sorter
{
    public static void insert_sort( long[] data )
    {
		for(int i= 1; i < data.length; i++)								//for i from the second element to the end
		{
			int j = i;													//let j be i
			while(j> 0 && data[j-1] > data[j])							//while j is greater than 0 and element j-1 is greater than element j
			{
				long temp= data[j];										//swap the element j-1 and the element j
				data[j]= data[j-1];
				data[j-1] = temp;
				j--;													//decrement j
			}
		}
    }

   /* TASK 1: MERGE SORT: sorts the data in ascending order using merge sort */
    public static void merge_sort( long[] data )
    {
        if(data.length<2)
        {
            return;
        }
        long[] right= new long[data.length/2];							//initialize the right half of the data 
        long[] left= new long[data.length-(data.length/2)];				//initialize

        for(int i=0; i<left.length; i++)
        {
            left[i]= data[i];
        }
        for(int i=0; i<right.length; i++)
        {
            right[i]= data[i+left.length]; 
        }
        merge_sort(right);												// recursively sort the right half of the data
        merge_sort(left);												// recursively sort the left half of the data
        int i=0; 
        int j=0;
        int k=0;
        while(i<left.length && j<right.length)
        {
            if(left[i]<= right[j])
            {
                data[k]= left[i];
                k+=1; 
                i+=1;
            }
            else
            {
                data[k]= right[j];
                k+=1; j+=1;
            }
        }
        while(i< left.length)
        {
            data[k]= left[i]; 
            k+=1; i+=1;
        }
        while(j<right.length)
        {
            data[k]= right[j];
            k+=1; j+=1;
        }
    }

	/* TASK 2: call merge_sort_me */
    public static void merge_sort_me( long[] data )
    {
        if(data.length<2)												//base case 
        {
            return;
        }
        long[] newdata= new long[data.length];
        merge_sort_me(data, 0, data.length, newdata);
    }
    
    /* TASK 2: MEMORY EFFICIENT MERGE SORT: implement merge sort using recursion with efficient memory use */
    public static void merge_sort_me( long[] data, int start, int end, long[] storage ){
        if(start>= end-1)												//base case
        {
            return;
        }
        int middle= (end+start)/2;
        merge_sort_me(data, start, middle, storage);
        merge_sort_me(data, middle, end, storage);
        
        int i= start; 
        int j= middle;
        int k= start;

        while(i<middle && j<end)
        {
            if(data[i]<= data[j])
            {
                storage[k]= data[i];
                k++; 
                i++; 
            }
            else
            {
                storage[k]= data[j]; 
                k++; 
                j++;
            }
        }
        while(i<middle)
        {
            storage[k]= data[i]; 
            k++; 
            i++;
        }
        while(j<end)
        {
            storage[k]= data[j];
            k++; 
            j++;
        }
        for(int m=start; m< end; m++)									//merge the subarrays
        {
            data[m]= storage[m];
        }

    }

    /* TASK 3: QUICK SORT: sorts the data in ascending order using quicksort */
    public static void quick_sort( long[] data, int start, int end )
    {
        if(start<end)
        {
            int splitter = split(data, start, end);
            quick_sort(data, start, splitter-1);
            quick_sort(data, splitter+1, end);
        }

    }
    public static int split(long[] data, int start, int end)
    {
		int middle = (start+(end-start)/2);
		long pivot = data[middle];

        int i=start;
        for(int j=start; j<end; j++)								// for every element in the array
        {
            if(data[j]< pivot)										// if the element is less than the pivot value
            {
                long temp= data[j];
                data[j]=data[i];									// swap the elements located at i and j
                data[i]= temp;
                i++;												// increment the index of the smaller element
            }
        }
        long temp2= data[i];
        data[i]= data[end];
        data[end]=temp2;
        return i;
    }

    /* TASK 4: HEAP SORT: O(nlogn) call the runHeap method to heapsort the data */
    public static void heap_sort(long[] data) 
    { 
        int N = data.length; 											//initialize size
        for (int i = (N/2)-1; i>=0; i--) 								//call to the runHeap method
            runHeap(data, N, i); 										//use the current values for data, size, and the i counter as parameters
        for (int i=N-1; i>=0; i--) 										//run through the heap list and remove elements from it
        { 		
            long temp = data[0]; 										//set the temp variable to the last element
            data[0] = data[i]; 											//set the last element to the element being removed
            data[i] = temp; 											//set the element at the current location to the temp value
            runHeap(data, i, 0); 										//recursively call the runHeap method to sort the newly arranged heap list
        } 
    } 
    
    /* TASK 4: HEAP SORT: O(nlogn) 
    	sort the data by recursively placing the max value at the end of the list of data */
    public static void runHeap(long data[], int N, int i) 
    { 
        int max = i;  								 					//the max value is the current iteration value
        int left = 2*i + 1;  											//initialize the counter value for the left side
        int right = 2*i + 2; 											//initialize the counter value for the right side
        if (left < N && data[left] > data[max]) 		
            max = left; 
        if (right < N && data[right] > data[max]) 
            max = right; 
        if (max != i) 
        { 
            long swap = data[i]; 
            data[i] = data[max]; 
            data[max] = swap; 
            runHeap(data, N, max); 										//recursively heap sort the newly sorted list
        } 
    } 
    
    public static void main (String[] argv)
    {
    	System.out.println("Enter the initial value for  N (number of values in the array):");
    	Scanner scan1 = new Scanner(System.in);
		int N = scan1.nextInt();
    
    	System.out.println("Would you like to run the program with random (1), in-order (2), or reverse-order (3) arrays?");
    	Scanner scan2 = new Scanner(System.in);
		int answer = scan2.nextInt();
    	
		int randomValue;
		double time;
		long[] array = new long[N];										// create an array of size
		Random ran = new Random();										// generate a new random value
		long start;													
		long end;
		int count = 0;
		Timing timing = new Timing();
		
		ArrayList <Double> insertTime = new ArrayList<Double>();		// initialize arrays to hold the runtimes of each method
		ArrayList <Double> mergeTime = new ArrayList<Double>();
		ArrayList <Double> mergemeTime = new ArrayList<Double>();
		ArrayList <Double> quickTime = new ArrayList<Double>();
		ArrayList <Double> heapTime = new ArrayList<Double>();
		
		while(count<5)
		{
			long[] insertSort=new long[N];	
			for(int i=0; i<N; i++)
			{
				randomValue = ran.nextInt(2*(N));	
				if(answer==1)					
				{
					insertSort[i] = randomValue;						// initialize the array to random values			(1)
				}
				else if(answer==2)
				{
					insertSort[i] = (i);								// initialize the array with ascending values		(2)
				}
				else if(answer==3)
				{
					insertSort[i] = (N-i);								// initialize the array with descending values		(3)
				}
				else
				{
					System.out.println("ERROR: enter 1, 2, or 3");
				}
			}	
			
			long[] mergeSort=new long[N];	
			for(int i=0; i<N; i++)
			{
				randomValue = ran.nextInt(2*(N));						
				if(answer==1)					
				{
					mergeSort[i] = randomValue;							// initialize the array to random values			(1)
				}	
				else if(answer==2)
				{
					mergeSort[i] = (i);									// initialize the array with ascending values		(2)
				}
				else if(answer==3)
				{
					mergeSort[i] = (N-i);								// initialize the array with descending values		(3)
				}
				else
				{
					System.out.println("ERROR: enter 1, 2, or 3");
				}
			}	
			
			long[] mergemeSort=new long[N];	
			for(int i=0; i<N; i++)
			{
				randomValue = ran.nextInt(2*(N));						
				if(answer==1)					
				{
					mergemeSort[i] = randomValue;						// initialize the array to random values			(1)
				}
				else if(answer==2)
				{
					mergemeSort[i] = (i);								// initialize the array with ascending values		(2)
				}
				else if(answer==3)
				{
					mergemeSort[i] = (N-i);								// initialize the array with descending values		(3)
				}
				else
				{
					System.out.println("ERROR: enter 1, 2, or 3");
				}
			}	
			
			long[] quickSort=new long[N];	
			for(int i=0; i<N; i++)
			{
				randomValue = ran.nextInt(2*(N));						
				if(answer==1)					
				{
					quickSort[i] = randomValue;							// initialize the array to random values			(1)
				}
				else if(answer==2)
				{
					quickSort[i] = (i);									// initialize the array with ascending values		(2)
				}
				else if(answer==3)
				{
					quickSort[i] = (N-i);								// initialize the array with descending values		(3)
				}
				else
				{
					System.out.println("ERROR: enter 1, 2, or 3");
				}
			}	
			
			long[] heapSort=new long[N];	
			for(int i=0; i<N; i++)
			{
				randomValue = ran.nextInt(2*(N));					
				if(answer==1)					
				{
					heapSort[i] = randomValue;							// initialize the array to random values			(1)
				}
				else if(answer==2)
				{
					heapSort[i] = (i);									// initialize the array with ascending values		(2)
				}
				else if(answer==3)
				{
					heapSort[i] = (N-i);								// initialize the array with descending values		(3)
				}
				else
				{
					System.out.println("ERROR: enter 1, 2, or 3");
				}
			}	
			
			System.out.println("RUN #" + (count+1) + ":");
			System.out.println("(Iterating " + N + " times)");
			
			start = System.nanoTime();									// time the insert_sort method and add its runtime to the insertTime array
			insert_sort(insertSort);
			end = System.nanoTime();
			time = (end-start)/1000000;
			insertTime.add(time);
			System.out.println("Time to run insert_sort: "+ time);
		
			start = System.nanoTime();									// time the merge_sort method and add its runtime to the mergeTime array
			merge_sort(mergeSort);
			end = System.nanoTime();
			time = (end-start)/1000000;
			mergeTime.add(time);
			System.out.println("Time to run merge_sort: "+ time);
			
			start = System.nanoTime();									// time the merge_sort_me method and add its runtime to the mergemeTime array
			merge_sort_me(mergemeSort);
			end = System.nanoTime();
			time = (end-start)/1000000;
			mergemeTime.add(time);
			System.out.println("Time to run merge_sort_me: "+ time);

			start = System.nanoTime();									// time the quick_sort method and add its runtime to the quickTime array
			quick_sort(quickSort,0,quickSort.length-1);
			end = System.nanoTime();
			time = (end-start)/1000000;
			quickTime.add(time);
			System.out.println("Time to run quick_sort: "+ time);
		
			start = System.nanoTime();									// time the heap_sort method and add its runtime to the heapTime array
			heap_sort(heapSort);
			end = System.nanoTime();
			time = (end-start)/1000000;	
			heapTime.add(time);
			System.out.println("Time to run heap_sort: "+ time);
			
			count++;													// run again
			N = N*2;													// multiply N by 2 each iteration
			System.out.print("\n");
		}
		
		double avgInsertTime = timing.robust_avg(insertTime);			// initialize arrays to hold the avg runtimes of each method
		double avgMergeTime = timing.robust_avg(mergeTime);
		double avgMergemeTime = timing.robust_avg(mergemeTime);
		double avgQuickTime = timing.robust_avg(quickTime);
		double avgHeapTime = timing.robust_avg(heapTime);
		
		System.out.println("Average time to run insert_sort: " + avgInsertTime + " ms");
		System.out.println("Average time to run merge_sort: " + avgMergeTime + " ms");
		System.out.println("Average time to run merge_sort_me: " + avgMergemeTime + " ms");
		System.out.println("Average time to run quick_sort: " + avgQuickTime + " ms");
		System.out.println("Average time to run heap_sort: " + avgHeapTime + " ms");
	}
}