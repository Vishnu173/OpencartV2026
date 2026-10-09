package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccountPage extends basePage {
	
	public myAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//By.xpath("//h2[normalize-space()='My Account']")
	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement myaccounttext;
	
	//By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']")
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logoutbtn;
	
	public boolean myaccountisexists() //returns only if the element is present or not.
	{
		try
		{
		return myaccounttext.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void logout() //added in step 06;
	{
		logoutbtn.click();
	}
	

}
