package ParallelScripts;

import org.testng.annotations.Test;

public class SampleOneTest {
  @Test
  public void testOne() {
	  long id=Thread.currentThread().getId();
	  System.out.println("Test1 in SampleOne..: "+ id);
  }
  
  @Test
  public void testTwo() {
	  long id=Thread.currentThread().getId();
	  System.out.println("Test2 in SampleOne..: "+ id);
  }
  
  @Test
  public void testThree() {
	  long id=Thread.currentThread().getId();
	  System.out.println("Test3 in SampleOne..: "+ id);
  }
  
  @Test(invocationCount=6, threadPoolSize=2, timeOut=2000)
  public void testFour() {
	  long id=Thread.currentThread().getId();
	  System.out.println("Test4 in SampleOne..: "+ id);
  }
}
