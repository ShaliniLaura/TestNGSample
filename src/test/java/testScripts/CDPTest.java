
package testScripts;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.devtools.v120.log.Log;
import org.openqa.selenium.devtools.v120.log.model.LogEntry;
import org.openqa.selenium.devtools.v121.network.Network;
import org.openqa.selenium.devtools.v121.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64;

public class CDPTest {
	
	ChromeDriver driver;
	DevTools devTools;
	
 @BeforeMethod
 public void setup() {
	 driver= new ChromeDriver();
	 devTools= driver.getDevTools();
	 devTools.createSession(driver.getWindowHandle());
 }
	
  @Test
  public void deviceModeTest() {
	  Map deviceMetrics= new HashMap() {
		  {
			  put("width",800);
			  put("height", 1200);
			  put("deviceScaleFactor", 50);
			  put("mobile", true);
		  }
	  };
	  driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
	  driver.get("https://www.selenium.dev/");
	  driver.manage().window().maximize();
	  		
		  }
	  
 @Test
 public void getLocationTest() {
	 Map deviceMetrics= new HashMap() {
		 {
			 put("latitude", 33.748997);
			 put("longitude",-84.387985);
			 put("accuracy",100);
		 }
	 };
	 driver.executeCdpCommand("Emulation.setGeolocationOverride", deviceMetrics);
     driver.get("https://oldnavy.gap.com/stores");
     driver.manage().window().maximize();
	 }
  
   @Test
   public void captureConsoleLogTest() {
	   devTools.send(Log.enable());
	   devTools.addListener(Log.entryAdded(), 
			   new Consumer<LogEntry>() {
		     public void accept(LogEntry logEntry)
		     {
		    	 System.out.println("Log: "+ logEntry.getText());
		    	 System.out.println("Level: "+ logEntry.getLevel());
		     }
	   });
	   driver.get("http://the-internet.herokuapp.com/");
   }
   
   @Test
   public void basicAuthTest()
   {
	   devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
	   Map<String, Object> headers = new HashMap();
	   String strUser= "admin";
	   String strPwd="admin";
	   
	   String basicAuth="Basic " + new String(new Base64().encode(String.format("%s:%s", strUser, strPwd).getBytes()));
	   
	   System.out.println("Auth : " + basicAuth);
	   //Set header- Auth Token
	   headers.put("Authorization", basicAuth);
	   devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
	   
	   driver.get("http:the-internet.herokuapp.com/basic_auth");
	   String strMsg= driver.findElement(By.cssSelector("div.example p")).getText();
	   Assert.assertEquals(strMsg,"Congratulations! You must have the proper credentials.");
	   
   }
   
 }
  
