package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.baseClass;
import utilities.dataProvider;

/*
 * Data is valid - login successful - test pass logout
 * Data is valid - login failed - test fail
 * 
 * Data is invalid - login successful - test failed
 * Data is invalid - login failed - test pass
 */


public class TC003_LoginPageDatadriventest extends baseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass = dataProvider.class, groups = {"Master","datadriven"}) //getting data provided from diff. class
	public void login(String username, String password, String result) throws InterruptedException
	{
       logger.info("****** Starting LoginDataDriven Testcase... ******");
		
       try 
      { 
		//homepage
		homePage hp = new homePage(driver);
	    hp.clickmyaccount();
	    hp.clicklogin();
	    
	    //loginpage
	    loginPage lp = new loginPage(driver);
	    lp.inputid(username);
	    lp.inputpassword(password);
	    lp.clickoginbtn();
	    
	    //myaccountpage
	    myAccountPage ap = new myAccountPage(driver);
	    
	    boolean targetpage = ap.myaccountisexists(); //if true - login is successful.
	    //data valid scenario
	    if(result.equalsIgnoreCase("Valid"))
	    {
	    	if(targetpage == true)
	    	{
	    		ap.logout();
	    		System.out.println("DATA IS VALID - Log in successful, Test case is passed");
	    		Assert.assertTrue(true);
	    	}
	    	else
	    	{
	    		System.out.println("DATA IS VALID - Log in failed, Test case is failed");
	    		Assert.assertTrue(false);
	    	}
	    }
	    
	    if(result.equalsIgnoreCase("Invalid"))
	    {
	    	if(targetpage == true) 
	    	{
	    		ap.logout();
	    		System.out.println("DATA IS INVALID - Log in successful, Test case is failed");
	    		Assert.assertTrue(false);
	    	}
	    	else
	    	{
	    		System.out.println("DATA IS INVALID - Log in failed, Test case is passed");
	    		Assert.assertTrue(true);
	    	}
	    }
	    
      }
       
       catch(Exception e)
       {
    	   Assert.fail();
       }
       Thread.sleep(2000);
	    
	   logger.info("****** Finished LoginDataDriven Testcase... ******");
	}

}
