/**
 * File: Timing.java
 * Author: Tamsin Rogers
 * Date: 3/3/20
 */
 
import java.io.*; 
import java.util.*; 
 
public class Timing
{

	/* returns the time taken in milliseconds */
	public static double just_adding(int N)
	{
		int i;								//initialize i
		double sum = 0.0;					//initialize sum
		double t;							//initialize t 
		long start, end;					//initialize start and end for timing
		
		start = System.nanoTime();			//implement nanoTime
		for(i=0; i<N; i++)					//loop through the length of the array
		{
			t =  (double)i;					
			sum += t;						//add t to the current sum
		}
		end = System.nanoTime();
		return (end-start) / 1000000.0;		//return the time in ms
	}
	
	/* sums the squares of the values from 0 to N-1 using regular multiplication to generate the squares */
	public static double square_mult(int N)
	{
		int i;
		double sum = 0.0;
		double t;
		long start, end;
		
		start = System.nanoTime();
		for(i=0; i<(N-1); i++)
		{
			t =  (double)i;
			sum += t*t;
		}
		end = System.nanoTime();
		return (end-start) / 1000000.0;		
	}
	
	/* sums the squares of the values from - to N-1 using the Math.pow() function to generate the squares */
	public static double square_pow(int N)
	{
		int i;
		double sum = 0.0;
		double t;
		long start, end;
		
		start = System.nanoTime();
		for(i=0; i<(N-1); i++)
		{
			t = (double)i;
			sum += Math.pow(t, (double)i);
		}
		end = System.nanoTime();
		return (end-start) / 1000000.0;		
	}
	
	/* computes a robust average of the values in data */
	public static double robust_avg(ArrayList<Double> data)
	{
		Collections.sort(data);
		
		double theSum = 0.0;
		int counter = 0;
		
		for (int i = 1; i < ((data.size())-1); i++) 
		{
			double value = data.get(i);
			theSum = theSum + value;
			counter = counter+1;
		}
		
		double avg = theSum/counter;
		return avg;
	} 
	
	public static void square_test(int N)
		{
		
		ArrayList<Double> addResults = new ArrayList<Double>();
		ArrayList<Double> multResults = new ArrayList<Double>();
		ArrayList<Double> powResults = new ArrayList<Double>();
		double add;
		double mult;
		double pow;
		double addAvg;
		double multAvg;
		double powAvg;
		
			for(int i=0; i<5; i++)
			{
				add = Timing.just_adding(N);
				System.out.println("just adding: " + add);
				addResults.add(0,add);
				
				mult = Timing.square_mult(N);
				System.out.println("square multiplication: " + mult);
				multResults.add(0,mult);
				
				pow = Timing.square_pow(N);
				System.out.println("power multiplication: " + pow);
				powResults.add(0,pow);
				System.out.println("\n");
			}
			
			System.out.println("add result: " + addResults);
			System.out.println("multiplication result: " + multResults);
			System.out.println("power result: " + powResults);
			System.out.println("\n");
			
			addAvg = Timing.robust_avg(addResults);
			multAvg = Timing.robust_avg(multResults);
			powAvg = Timing.robust_avg(powResults);
			
			System.out.println("add time estimate: " + addAvg);
			System.out.println("mult time estimate: " + multAvg);
			System.out.println("pow time estimate: " + powAvg);
			System.out.println("\n");
		
			System.out.println("powResults/multResults: " + (powAvg/multAvg));
			System.out.println("(powResults-addResults)/(multResults-addResults): " + ((powAvg-addAvg)/(multAvg-addAvg)));
			System.out.println("\n");
		}
	
	public static void main(String[] argv)
	{
		double ja;
		int i;
		
		/*System.out.println("just adding:");
		for(i=100000; i<500000; i+= 100000)
		{
			ja = Timing.just_adding(i);
			System.out.println("just adding: " + ja);
		}
		
		System.out.println("\nmultiplication:");
		for(i=100000; i<500000; i+= 100000)
		{
			ja = Timing.square_mult(i);
			System.out.println("square multiplication: " + ja);
		}
		
		System.out.println("\npow:");
		for(i=100000; i<500000; i+= 100000)
		{
			ja = Timing.square_pow(i);
			System.out.println("pow: " + ja);
		}*/
		
		/*System.out.println("\n");
		ja = Timing.just_adding(1000);
		System.out.println("just adding (1000): " + ja);
		ja = Timing.just_adding(10000);
		System.out.println("just adding (10000): " + ja);
		ja = Timing.just_adding(100000);
		System.out.println("just adding (100000): " + ja);
		
		System.out.println("\n");
		ja = Timing.square_mult(1000);
		System.out.println("square multiplication (1000): " + ja);
		ja = Timing.square_mult(10000);
		System.out.println("square multiplication (10000): " + ja);
		ja = Timing.square_mult(100000);
		System.out.println("square multiplication (100000): " + ja);
		
		System.out.println("\n");
		ja = Timing.square_pow(1000);
		System.out.println("pow multiplication (1000): " + ja);
		ja = Timing.square_pow(10000);
		System.out.println("pow multiplication (10000): " + ja);
		ja = Timing.square_pow(100000);
		System.out.println("pow multiplication (100000): " + ja);
		System.out.println("\n");*/
		
		square_test(100);
		square_test(1000);
		square_test(10000);
		square_test(100000);
		square_test(100000000);
		
	}
}