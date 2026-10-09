package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registrationPage extends basePage{
	
	public registrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	/*
	 * 
By.xpath("//input[@id='input-firstname']")
By.xpath("//input[@id='input-lastname']")
By.xpath("//input[@id='input-email']")
By.xpath("//input[@id='input-telephone']")
By.xpath("//input[@id='input-password']")
By.xpath("//input[@id='input-confirm']")
By.xpath("//input[@name='agree']")
By.xpath("//input[@value='Continue']")


By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")
	 * 
	 */
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtfirstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtlastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtconfirmpassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkprivacypolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirmation;
	
	//Actions
	public void setFirstName(String fname)
	{
		txtfirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtlastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void setTelephoneNumber(String tel)
	{
		txttelephone.sendKeys(tel);
	}
	
	public void setPassword(String pw)
	{
		txtpassword.sendKeys(pw);
	}
	
	public void confirmPw(String confpw)
	{
		txtconfirmpassword.sendKeys(confpw);
	}
	
	public void checkPrivacy()
	{
		chkprivacypolicy.click();
	}
	
	public void clickContinue()
	{
		btncontinue.click();
	}
	
	//validation should be performed in separate testcase classes, not in page object class.
	  //for confirmation element in PAGE OBJECT Class;
	public String confirmationtext() 
	//returns the text value alone.
   {
	try 
	    {
		return (msgconfirmation.getText());
	    }
	    catch (Exception e)
	    {
		  return(e.getMessage());
	    }
	}

}
