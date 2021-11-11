
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class sumsTest2 extends Thread {

	@Test
	void testSingleSum() {
		
		int[] arr = {2,3,4,5};
		
		int testSingleSum = sums.singleSum(arr, 0, arr.length);
		
		Assert.assertEquals(testSingleSum, 14);	}

	@Test
	void testParallelSum() {
		
		//array of ten 1's
		int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		
		String thread = "main";

		sums.parallelSum(arr, thread);
		
		Assert.assertEquals(10, sums.parallelSum);
		
	}

}
