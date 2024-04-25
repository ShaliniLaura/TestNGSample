package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GooglePageTest {
  @Test
  public void javaSearchTest() {
	  
	  WebDriver driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://www.google.com/");
	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
	  srcbox.sendKeys("Java Tutorial");
	  srcbox.submit();
	  
	  
  }
}
