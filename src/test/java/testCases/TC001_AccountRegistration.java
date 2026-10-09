package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import pageObjects.homePage;
import pageObjects.registrationPage;
import testBase.baseClass;

public class TC001_AccountRegistration extends baseClass{
	
	//every class is considered as 1 test case -> in java class pov
	
	//3 methods - void setup, testmethod, teardowm -> setp and teardown moved to baseclass
	
	
	//methods commonly used in every testcases can be moved to in a single class - base class, for reusability containing reusability methods.
	
	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration()
	{
		
		try 
		{
		
		//log using logger variable
		logger.info("************  Starting TC001_AccountRegistration  ************");
		
		//Access page object class methods by creating object for the class for testcases.
		 //homePage constructor is there, constructor is there, it invokes driver at run time, so this time it needs driver, parse it from the first method we created
		homePage hp = new homePage(driver);
		
		hp.clickmyaccount();
		logger.info("************  clicked on my account link...  ************");
		hp.clickregister();
		logger.info("************  clicked on my register link...  ************");
		
		registrationPage rp = new registrationPage(driver);
		
		logger.info("************  provided customer details...  ************");
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		/*everytime we have to provide different email id //to make it dynamic, here we used static. -> randomly generate
		email id -----> we can write a method for that.*/ 
        rp.setEmail(randomString()+"@gmail.com"); //calling the string method
        rp.setTelephoneNumber(randomNumber());
        
        //string password alpha numeric
        String password = randomAlphanumeric();
        rp.setPassword(password);
        rp.confirmPw(password);
        
        rp.checkPrivacy();
        rp.clickContinue();
        
        logger.info("************  Validating expected message... ************");
        String confirmationmsge = rp.confirmationtext();
        
        if(confirmationmsge.equals("Your Account Has Been Created!"))
        {
        	logger.info("************  message successfully validated...  ************");
        	AssertJUnit.assertTrue(true);
        }
        else
        {
        	logger.error("Test failed...");
			logger.debug("Debug logs..."); //this will not capture as in xml info only provided. from info only it will capture.
			AssertJUnit.assertTrue(false);
        }
        
        AssertJUnit.assertEquals("Your Account Has Been Created!", confirmationmsge);
        
		}
		         //if validation fails -> so use try catch method
		catch(Exception e)
		{
			AssertJUnit.fail();
		}
		
		logger.info("************  TC001_AccountRegistration completed...  ************");
 
	}
}
