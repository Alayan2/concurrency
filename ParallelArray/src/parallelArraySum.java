
/**
 * Assignment Description
 * 
 * Implement a parallel array sum, and compare performance with single thread sum. 
 * This is not an easy assignment – just do as much as you can and turn in what 
 * you have for partial credit.
 * 
 * Make an array of 200 million random numbers between 1 and 10. Compute the sum
 * in parallel using multiple threads. Then compute the sum with only one thread, 
 * and display the sum and times for both cases.
 * 
 * @author alayan2
 *
 */

public class parallelArraySum extends Thread{

	static int maximum = 10;
	static int minimum = 1;

	public static int[] arr = new int[200000000]; //array of 200 million numbers
	public static int indexOne = arr.length/3; //divide array length by number of threads

	public static int pSum;
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	
	public static void main(String[] args) throws InterruptedException {


		//implement method to build array of random numbers between min and max
		arr = buildArray(arr, maximum, minimum);


		//starting point for logging parallel runtime
		long parallelStartTime = System.nanoTime();

		//create instances of sums class with array divided equally between threads
		Thread threadOneSum = new sums();
		Thread threadTwoSum = new sums() ;
		Thread threadThreeSum = new sums();

		//start parallel threads
		threadOneSum.start();
		threadTwoSum.start();
		threadThreeSum.start();

		try
		{
			threadOneSum.join();
			threadTwoSum.join();
			threadThreeSum.join();
		}
		
		catch (InterruptedException e)
		{
			System.out.println("Interrupt Occurred");
			e.printStackTrace();
		}
		
		//end point for logging parallel runtime
		long parallelEndTime   = System.nanoTime();
		long parallelTotalTime = getRunTime(parallelEndTime, parallelStartTime);

		parallelOutputMessage(pSum, parallelTotalTime);

		//starting point for logging sequential runtime
		long sequentialStartTime = System.nanoTime();

		int newArray = sums.singleSum(arr, 0 , arr.length);

		//end point for logging sequential runtime
		long sequentialEndTime   = System.nanoTime();
		long sequentialTotalTime = getRunTime(sequentialEndTime, sequentialStartTime);

		//print messages
		sequentialOutputMessage(newArray, sequentialTotalTime);
		runtimeMessage(parallelTotalTime, sequentialTotalTime );

		if(!(pSum == newArray)) {
			System.out.println("not a match");
		}
	}

	/**
	 * returns array of random integer between minimum and maximum range
	 * 
	 * @param arr array of int
	 * @param min low range of random number values
	 * @param max high range of random number values
	 */

	public static int[] buildArray(int[] arr, int min, int max){
		for (int i = 0; i < arr.length; i++) {
			arr[i]= getRandomInteger(max, min); // storing random integers in an array
		}
		return arr;
	}

	/**
	 * returns random integer between minimum and maximum range
	 * @param minimum low range of random number values
	 * @param maximum high range of random number values
	 */

	public static int getRandomInteger(int maximum, int minimum){
		return ((int) (Math.random()*(maximum - minimum))) + minimum;
	}


	/**
	 * returns sum of array 
	 * 
	 * @param sequentialEndTime time logged when single thread sum completed
	 * @param parallelEndTime time logged when parallel thread sum completed

	 */

	public static long getRunTime(long sequentialEndTime, long sequentialStartTime){
		long sequentialTotalTime = sequentialEndTime - sequentialStartTime;

		return sequentialTotalTime;
	}
	
	/**
	 * calculates difference between parallel and sequential runtime 
	 * 
	 * @param a duration logged when parallel thread sum completed
	 * @param b duration logged when single thread sum completed
	 * 	
	 */
	
	public static long runtimeDiff(long a, long b) {
		
		long c = a - b;
		
		return c;
	}
	
	/**
	 * prints difference between parallel and sequential runtime 
	 * 
	 * @param p duration logged when parallel thread sum completed
	 * @param s duration logged when single thread sum completed	 
	 * 
	 */
	
	public static void runtimeMessage (long p, long s)
	{
		long timeDiff = runtimeDiff(p, s);
		
		if (timeDiff > 0) {
			System.out.println("\n The parallel runtime is " + Math.abs(timeDiff) + " nanoseconds longer than the sequential runtime." ); // printing runtime
		} else {
			System.out.println("\n The sequential runtime is " + Math.abs(timeDiff) + " nanoseconds longer than the parallel runtime." ); // printing runtime
		}	
	}
	
	/**
	 * prints sum of parallel array and runtime 
	 * @param a total sum of array
	 * @param b total duration logged when sum completed	 
	 * 
	 */
	
	public static void parallelOutputMessage(int a, long b) {
		System.out.println("Final Parallel Total: " + a); // printing sum of array 
		System.out.println("Final Parallel Time: " + b + "\n"); // printing runtime
	}

	/**
	 * prints sum of sequential array and runtime 
	 * 
	 * @param a total sum of array
	 * @param b total duration logged when sum completed	 
	 * 
	 */
	
	public static void sequentialOutputMessage(int a, long b) {
		System.out.println("Final Sequential Total: " + a); // printing sum of array 
		System.out.println("Final Sequential Time: " + b); // printing runtime
	}
}
