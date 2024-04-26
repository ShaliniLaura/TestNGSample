package ParallelScripts;

import org.testng.annotations.Test;

public class SampleTwoTest {
	@Test
	  public void testOne() {
		  long id=Thread.currentThread().getId();
		  System.out.println("Test1 in SampleTwo..: "+ id);
	  }
	  
	  @Test
	  public void testTwo() {
		  long id=Thread.currentThread().getId();
		  System.out.println("Test2 in SampleTwo..: "+ id);
	  }
	  
	  @Test
	  public void testThree() {
		  long id=Thread.currentThread().getId();
		  System.out.println("Test3 in SampleTwo..: "+ id);
	  }
	  
	  @Test
	  public void testFour() {
		  long id=Thread.currentThread().getId();
		  System.out.println("Test4 in SampleTwo..: "+ id);
	  }
}
