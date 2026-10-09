package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage{
	
	//this constructor is needed, without invoking this constructor we cannot invoke parent class constructor
	public homePage(WebDriver driver)
	{
		super(driver);//constructors added in a separate class, due to which we are adding SUPER keyword, to invoke immediate constructor of parent class.
	}

//locators
	
	/*
	 * 
By.xpath("//span[normalize-space()='My Account']")
By.xpath("//a[normalize-space()='Register']")
By.xpath("//a[normalize-space()='Login']")

By.xpath("//a[normalize-space()='Login']")
	 * 
	 */
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement myaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registeraccount;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement loginaccount;
	
	//Actions
	public void clickmyaccount()
	{
		myaccount.click();
	}
	
	public void clickregister() 
	{
		registeraccount.click();
	}
	
	public void clicklogin()
	{
		loginaccount.click();
	}
	
}
