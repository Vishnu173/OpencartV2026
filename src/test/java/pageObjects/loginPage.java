package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage{
	
	public loginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	/*
	By.xpath("//input[@id='input-email']")
	By.xpath("//input[@id='input-password']")
	By.xpath("//input[@value='Login']")
	*/
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement inputidfield;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement inputpwfield;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginbtn;
	
	public void inputid(String id)
	{
		inputidfield.sendKeys(id);
	}
	
	public void inputpassword(String pw)
	{
		inputpwfield.sendKeys(pw);
	}
	
	public void clickoginbtn()
	{
		loginbtn.click();
	}

}
