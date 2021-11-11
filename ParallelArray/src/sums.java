
public class sums extends Thread {

	public static int parallelSum;
	public static int parallelSum1;
	public static int parallelSum2;


	public static int singleSum;
	static int count = 0; 

	public static int singleSum(int[] arr, int a, int b) {

		for (int i = a; i < b; i++) {
			singleSum += arr[i];  
		}
		return singleSum;
	}

	public static void parallelSum(int[] arr, String threadName) {

		int minRange = 0 ;
		int maxRange = 0;
		int y;

		if ((threadName.equals("Thread-0"))) {
			y = 0;
			minRange = parallelArraySum.indexOne * y ; 
			maxRange = parallelArraySum.indexOne * (y + 1);

			for (int i = minRange; i < maxRange; i++) {
				parallelSum += arr[i]; 

			}
		} else if ((threadName.equals("Thread-1"))) {
			y = 1;
			minRange = parallelArraySum.indexOne * y ; 
			maxRange = parallelArraySum.indexOne * (y + 1);

			for (int i = minRange; i < maxRange; i++) {
				parallelSum1 += arr[i]; 
			}
		}else if ((threadName.equals("Thread-2"))) {
			y = 2;
			minRange = parallelArraySum.indexOne * y ; 
			maxRange = arr.length;

			for (int i = minRange; i < maxRange; i++) {
				parallelSum2 += arr[i]; 
			}
			
		} else if ((Thread.currentThread().getName().equals("main"))) {
			maxRange = arr.length;

			for (int i = minRange; i < maxRange; i++) {
				parallelSum += arr[i]; 
			}
		}

	}


	@Override
	public void run() {

		parallelSum(parallelArraySum.arr, Thread.currentThread().getName());
		parallelArraySum.pSum = parallelSum + parallelSum1 + parallelSum2;
	}
}
