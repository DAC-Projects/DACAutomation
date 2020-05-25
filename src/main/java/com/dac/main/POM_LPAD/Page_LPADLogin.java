package com.dac.main.POM_LPAD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class Page_LPADLogin 
{
	WebDriver driver;

	@FindBy(name="username")
	private WebElement UserNameTB;
	
	@FindBy(id="txt_password")
	private WebElement PasswordTB;
	
	@FindBy(id="btlogin")
	private WebElement LoginBTN;
	
	
	public  Page_LPADLogin(WebDriver driver) {
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
	}
	
	public void setUserName(String un) {
		
		UserNameTB.sendKeys(un);
	}
	
	public void setPassword(String pwd) {
	
		PasswordTB.sendKeys(pwd);
	}
	public String getTitle() {
		
		return driver.getTitle();
		
	}
	
	public void clickLogin() {
		//loginBTN.submit();
		System.out.println("Trying to Login");
		LoginBTN.click();
	}
}
