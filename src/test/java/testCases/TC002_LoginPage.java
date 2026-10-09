package testCases;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.baseClass;

public class TC002_LoginPage extends baseClass {
	
	@Test(groups = {"Sanity","Master",})
	public void login() throws IOException 
	{
		
		logger.info("****** Starting Login Testcase... ******");
		
		try 
		{
		//homepage
		homePage hp = new homePage(driver);
	    hp.clickmyaccount();
	    hp.clicklogin();
	    
	    //loginpage
	    loginPage lp = new loginPage(driver);
	    lp.inputid(p.getProperty("email"));
	    lp.inputpassword(p.getProperty("password"));
	    lp.clickoginbtn();
	    
	    //myaccountpage
	    myAccountPage ap = new myAccountPage(driver);
	    boolean value = ap.myaccountisexists();
	    Assert.assertEquals(value, true, "account is not written");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	    
	    logger.info("****** Finished Login Testcase... ******");
	}
}
