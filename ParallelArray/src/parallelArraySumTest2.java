
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class parallelArraySumTest2 {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@Test
	void testBuildArray() {
		
		int max = 5;
		int min = 1;
		int[] arr = new int[max];

		arr = parallelArraySum.buildArray(arr, max, min);
		
		Assert.assertNotNull(arr);
		Assert.assertTrue(arr[0] <= max || arr[0] >= min );
		Assert.assertTrue(arr[1] <= max || arr[0] >= min );
		Assert.assertTrue(arr[2] <= max || arr[0] >= min );
		Assert.assertTrue(arr[3] <= max || arr[0] >= min );
		Assert.assertTrue(arr[4] <= max || arr[0] >= min );
		
		Assert.assertEquals(arr.length, max);
	}

	@Test
	void testGetRandomInteger() {
		
		int max = 5;
		int min = 1;
		int randomNumber = parallelArraySum.getRandomInteger(max, min);
	
		Assert.assertNotNull(randomNumber);
		Assert.assertTrue(randomNumber <= max || randomNumber >= min );
	}

	@Test
	void testGetRunTime() {
		
		long start = 1000;
		long end = 2000;
		long difference = parallelArraySum.getRunTime(end, start);
		
		Assert.assertEquals(difference, 1000);
		Assert.assertTrue(end > start);
		Assert.assertTrue(difference > 0);	
		
	}

	@Test
	public void testRuntimeDiff() {
		
		long parallel = 1000;
		long sequential = 2000;
		long diff = parallelArraySum.runtimeDiff(parallel, sequential);
		
		Assert.assertEquals(-1000, diff);
		
	}
	
	
	@BeforeEach
	public void setUp() {
		
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	void testRuntimeMessage() {
		
		long p = 1000;
		long s = 2000;
		
		parallelArraySum.runtimeMessage(p, s);

		Assert.assertEquals("The sequential runtime is " + Math.abs(p-s) + " nanoseconds longer than the parallel runtime.", outputStreamCaptor.toString()
		      .trim());
	}
	
	@BeforeEach
	public void tearDown() {
		
	    System.setOut(standardOut);
	}

	
	@Test
	void testParallelOutputMessage() {

		int a = 11;
		long b = 1111;
		
		parallelArraySum.parallelOutputMessage(a, b);
		
		Assert.assertEquals("Final Parallel Total: " + a + "\nFinal Parallel Time: " + b, outputStreamCaptor.toString().trim());
	}
	


	@Test
	void testSequentialOutputMessage() {
		
		int a = 11;
		long b = 1111;
		
		parallelArraySum.sequentialOutputMessage(a, b);
		
		Assert.assertEquals("Final Sequential Total: " + a + "\nFinal Sequential Time: " + b, outputStreamCaptor.toString().trim());
		}

}
