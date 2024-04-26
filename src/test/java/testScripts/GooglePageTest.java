package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GooglePageTest {
	WebDriver driver;

@BeforeMethod
public void setup(){
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	}
	
  
  @Test(dependsOnMethods="seleniumSearchTest")
  public void javaSearchTest() {
	  
	  
	  driver.get("https://www.google.com/");
	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
	  srcbox.sendKeys("Java Tutorial");
	  srcbox.submit();
      Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	  
  }
  
  @Test
  public void seleniumSearchTest() {
	  
	  driver.get("https://www.google.com/");
	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
	  srcbox.sendKeys("Selenium Tutorial");
	  srcbox.submit();
	  Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
	  
  }
  
  //@Test(enabled =false)
  public void cucumberSearchTest() {
	  
	  driver.get("https://www.google.com/");
	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
	  srcbox.sendKeys("Cucumber Tutorial");
	  srcbox.submit();
	  Assert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search");
	  
  }
  //@Test(priority=4)
  public void appiumSearchTest() {
	  
	  driver.get("https://www.google.com/");
	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
	  srcbox.sendKeys("Appium Tutorial");
	  srcbox.submit();
	  Assert.assertEquals(driver.getTitle(), "Appium Tutorial - Google Search");
	  
  }
  
//  @Test
//  public void softassertcheck() {
//	  
//	  
//	  driver.get("https://www.google.com/");
//	  SoftAssert softasrt= new SoftAssert();
//	  softasrt.assertEquals(driver.getTitle(), "Google Search"); //fail because only Google is expected
//	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
//	  srcbox.sendKeys("Java Tutorial");
//	  srcbox.submit();
//      softasrt.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
//	  softasrt.assertAll(); // Consider all assert results and reports pass or fail
//	  
//  }
  
  @AfterMethod
  public void tearDown()
  {
	  driver.close();
  }
  
}
