package testScripts;

import org.testng.annotations.Test;

public class SampleThreeTest {
  @Test(groups="featureOne")
  public void testOne() {
	  System.out.println("TestOne in SampleThree");
  }
  
  @Test(groups="featureTwo")
  public void testTwo() {
	  System.out.println("TestTwo in SampleThree");
  }
  
  @Test(groups="featureThree")
  public void testThree() {
	  System.out.println("TestThree in SampleThree");
  }
  
  @Test
  public void testFour() {
	  System.out.println("TestFour in SampleThree");
  }
}
