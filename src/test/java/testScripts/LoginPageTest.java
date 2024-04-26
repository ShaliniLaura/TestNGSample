package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginPageTest {
  @Test
  public void validLogin() {
	  WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/login");
		
	    driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("SuperSecretPassword!");
		
		driver.findElement(By.className("radius")).click();
   }
  
  
}
