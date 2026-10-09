package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; //Log4j

public class baseClass {
	
	public static WebDriver driver;
	public Logger logger;  //Log4j
	public Properties p; //properties class
	
	@BeforeClass(groups = {"Sanity", "Regression", "Master", "datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		//LOADING config.properties FILE; //mention in setup class itself, as that would be executed first
		  ///Users/vishnukannan/eclipse-workspace/OpencartV2026/src/test/resources
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		//load property file
		p.load(file);
		
		//logger for logs
		logger = LogManager.getLogger(this.getClass()); //log for the particular class in the run time it will get.
			
	//parameter from properties file for remote execution;
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		//grid setup
		String huburl = "http://localhost:4444/wd/hub";
		DesiredCapabilities dc = new DesiredCapabilities();
		  //os and browser from xml file; 
	//OS
		switch(os.toLowerCase())
		{
		//macos
		case "macintosh" : dc.setPlatform(Platform.MAC);
		break;
		//windows
		case "windows" : dc.setPlatform(Platform.WINDOWS);
		break;
		//linux
		case "linux" : dc.setPlatform(Platform.LINUX);
		break;
		
		default : System.out.println("no matching OS found");
		return;
		}
		
	//Browser
		switch(br.toLowerCase())
		{
		//chrome
		case "chrome" : dc.setBrowserName("chrome");
		break;
		//edge
		case "edge" : dc.setBrowserName("MicrosoftEdge");
		break;
		//firefox
		case "firefox" : dc.setBrowserName("firefox");
		break;
		//safari
		case "safari" : dc.setBrowserName("safari");
		break;
		
		default : System.out.println("no matching BROWSER found");
		return;
		}
		
		driver = new RemoteWebDriver(new URL(huburl), dc);
	}	
		
	//parameter from properties file for local execution;	
	else if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{	
		switch(br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver();
		break;
		case "edge" : driver = new EdgeDriver();
		break;
		case "firefox" : driver = new FirefoxDriver();
		break;
		default : System.out.println("Invalid browser");
		return; 
		}	
	}
		
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
	driver.get(p.getProperty("url"));  //reading url from properties file
	driver.manage().window().maximize();
	
	}
	
	@AfterClass(groups = {"Sanity", "Regression", "Master", "datadriven"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomString() //method to create random string
	{
		@SuppressWarnings("deprecation")
		String generatedstrings = RandomStringUtils.randomAlphabetic(5);
		return generatedstrings;
	}
	
	public String randomNumber()
	{
		String randomnumbers = RandomStringUtils.randomNumeric(10);
		return randomnumbers;
	}
	
	public String randomAlphanumeric()
	{
		String randname = RandomStringUtils.randomAlphabetic(5);
		String randnum = RandomStringUtils.randomNumeric(5);
		return (randname+"@"+randnum);
	}

	public String captureScreen(String tname) //name of the method from the result is passed into.
	{
		// TODO Auto-generated method stub
		String timestamp =  new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File SourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timestamp + ".png";
		File targetfile = new File(targetFilePath);
		
		SourceFile.renameTo(targetfile);
		
		return targetFilePath; //targetfilepath "string" is assigned to img path
	}	
}
