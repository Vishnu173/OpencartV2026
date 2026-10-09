package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import testBase.baseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    
    String repName;
    
    public void onStart(ITestContext testContext) {
    	
    	//date format to be append to the report
    	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); //dateformat
    	Date dt = new Date();  //create date
    	String currentdatetimestamp = df.format(dt);  //implementing format for the date, return in string.  	
    	
        /*  //both are same
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        */
    	
        repName = "Test-Report-" + currentdatetimestamp + ".html";

        // Specify location for the report
        sparkReporter = new ExtentSparkReporter("./reports/" + repName); 
        
        // Set report configurations
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add system information to the report
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // Get additional information from test context
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        // Add groups to the report if available
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) { //not empty
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }
    
    public void onTestSuccess(ITestResult result) {
        // Log success if the test passed
    	test = extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    
    public void onTestFailure(ITestResult result) {
        // Log failure details
    	test=extent.createTest(result.getClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        String imgPath = new baseClass().captureScreen(result.getName()); // Capture screenshot 
            //base class while creating object, driver is created here, so we need to make web driver as static in the baseclass.
		test.addScreenCaptureFromPath(imgPath);
    }

    public void onTestSkipped(ITestResult result) {
    	
    	test=extent.createTest(result.getClass().getName());
    	test.assignCategory(result.getMethod().getGroups());
    	
        // Log skipped test details
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @SuppressWarnings("deprecation")
	public void onFinish(ITestContext testContext) {
    	
        // Finalize the report after test execution
        extent.flush();

        // Open the report in the default browser automatically.
        String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
        File extentReport = new File(pathOfExtentReport);
        
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     /* 
        try 
        {
		URL url = new URL("file:///"+System.getProperty("user.dir") + "/reports/" + repName);
		//convert html file path to url
        
        //Create the email message after generation of reports
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("kannanvishnu6773@gmail.com","gtow rrst yoim hgll"));
        email.setSSLOnConnect(true);
        email.setFrom("kannanvishnu6773@gmail.com"); //sender
        email.setSubject("Automation Test Execution Report");
        email.setMsg("Please find attached report...");
        email.addTo("kannanvishnu170@gmail.com");  //receiver
        email.attach(url, "extent report", "please check the extent report of the test run");
        email.send();
        
        } 
        
        catch (MalformedURLException | EmailException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     */
        
    }
}