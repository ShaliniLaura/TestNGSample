package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import commonUtils.Utility;

public class ExtentReportTest {
	WebDriver driver;
	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	@BeforeTest
	public void initExtent() {
		extentReports= new ExtentReports();
		spark= new ExtentSparkReporter("test-output/SparkReport.html")
				.viewConfigurer()
				.viewOrder()
				.as(new ViewName[] {
						ViewName.DASHBOARD, 
						ViewName.TEST,
						ViewName.AUTHOR,
						ViewName.DEVICE,
						ViewName.LOG
						}).apply();
		extentReports.attachReporter(spark);
	}

@BeforeMethod
public void setup(){
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	}
	
  
  @Test
  public void javaSearchTest() {
	  
	  extentTest=extentReports.createTest("Java Search Test");
	  driver.get("https://www.google.com/");
	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
	  srcbox.sendKeys("Java Tutorial");
	  srcbox.submit();
      Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	  
  }
  
  @Test(retryAnalyzer=RetryAnalyserImpl.class)
  public void seleniumSearchTest() {
	  
	  extentTest=extentReports.createTest("Selenium Search Test");
	  driver.get("https://www.google.com/");
	  WebElement srcbox=driver.findElement(By.className("gLFyf"));
	  srcbox.sendKeys("Selenium Tutorial");
	  srcbox.submit();
	  Assert.assertEquals(driver.getTitle(), "Selenium1 Tutorial - Google Search");
	  
  }
  
  
  @AfterMethod
  public void tearDown(ITestResult res)
  {
	  extentTest.assignAuthor("Automation Tester1")
	  .assignAuthor("Regression")
	  .assignDevice(System.getProperty("os.name"))
	  .assignDevice(System.getProperty("os.version"));
	  if(ITestResult.FAILURE==res.getStatus())
	  {
		  extentTest.log(Status.FAIL, res.getThrowable().getMessage());
		  String strpath=Utility.getScreenshotPath(driver);
		  extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(strpath).build());
	  }
	  if(ITestResult.SKIP==res.getStatus())
	  {
		  extentTest.log(Status.SKIP, res.getThrowable().getMessage());
	  }
	  driver.close();
  }

  @AfterTest
  public void finishExtent()
  {
	  extentReports.flush();
  }
  
}
