package testScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;






public class ExternalFileTest {
	WebDriver driver;
	Properties prop;

  @BeforeTest
  public void initSetup() throws IOException
  {
	  prop = new Properties();// to create property object
	  String path= System.getProperty("user.dir")+ "//src//test//resources//configFiles//config.properties";
	  FileInputStream fin= new FileInputStream(path); //to open connection to the file in the path
	  prop.load(fin);// to read/load key/values pairs from file to property variable
	  fin.close();//to close connection to the file.
	  
  }
	
  @BeforeMethod
  public void setup(){
	String strBrowser=prop.getProperty("browser");
	if(strBrowser.equalsIgnoreCase("chrome")) {
	  driver= new ChromeDriver();
	}
	else if(strBrowser.equalsIgnoreCase("edge"))
	{
		driver= new EdgeDriver();
	}
	driver.manage().window().maximize();
	}	
	
  
  @Test(dataProvider="logindata")
  public void validLogin(String strUser, String strPwd) {
	  
		driver.get(prop.getProperty("url"));
		
	    driver.findElement(By.id("username")).sendKeys(strUser);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(strPwd);
		
		driver.findElement(By.className("radius")).click();
		Boolean res=driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
        Assert.assertTrue(res);
  }

   //Dataprovider to read data from .csv file  
//  @DataProvider(name="logindata")
//  public Object[][] getData(){
//    String path= System.getProperty("user.dir")+ "//src//test//resources//testData/loginData.csv";
//    CSVReader reader=null;
//    try {
//    	reader= new CSVReader( new FileReader(path));
//    }
//    catch(FileNotFoundException e)
//    {
//    	e.printStackTrace();
//    }
//    String cols[];
//    ArrayList<Object> dataList= new ArrayList<Object>();
//    try {
//    	while((cols=reader.readNext())!=null)
//    	{
//    		Object record[]= {cols[0], cols[1]};
//    		dataList.add(record);
//    	}
//    	reader.close();
//    }catch(CsvValidationException | IOException e) {
//    	e.printStackTrace();
//    }
//    return dataList.toArray(new Object[dataList.size()][]);
//  }

  
  //Dataprovider to read data from json file
  @DataProvider(name="logindata")
  public String[][] getData() throws IOException, ParseException {
	  String path= System.getProperty("user.dir")+ "//src//test//resources//testData//loginData.json";
	  FileReader reader= new FileReader(path);
	  JSONParser parser= new JSONParser();
	  Object obj= parser.parse(reader);
	  JSONObject jsonobj= (JSONObject) obj;
	  JSONArray userArray=(JSONArray) jsonobj.get("userLogins");
	  String arr[][]= new String[userArray.size()][];
	  for(int i=0; i<userArray.size();i++)
	  {
		  JSONObject user=(JSONObject) userArray.get(i);
		  String strUser=(String) user.get("username");
		  String strPwd=(String) user.get("password");
		  String record[]= { strUser, strPwd};
		  arr[i] =record;
		  
	  }
	  return arr;
	  }

}

