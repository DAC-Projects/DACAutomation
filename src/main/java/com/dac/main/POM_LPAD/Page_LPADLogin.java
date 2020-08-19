package com.dac.main.POM_LPAD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.IAutoconst;



public class Page_LPADLogin extends BaseClass
{
	WebDriver driver;

	@FindBy(name="username")
	private static WebElement UserNameTB;
	
	@FindBy(id="txt_password")
	private static WebElement PasswordTB;
	
	@FindBy(id="btlogin")
	private static WebElement LoginBTN;
	
	
	public  Page_LPADLogin(WebDriver driver) {
//		super(driver);
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
	}
	
	private static void setUserName(String un) {
		
		UserNameTB.sendKeys(un);
	}
	
	private static void setPassword(String pwd) {
	
		PasswordTB.sendKeys(pwd);
	}
	private String getTitle() {
		
		return driver.getTitle();
//		if (pageTitle.equalsIgnoreCase("Management Administration Portal Login")) {
//			  System.out.println("LPAD Login TC Passed");
//		  }else {
//			  System.out.println("LPAD Login TC Failed");
//		  }
		
	}
	
	private static void clickLogin() {
		//loginBTN.submit();
		System.out.println("Trying to Login");
		LoginBTN.click();
	}
	
	public  void LoginTOLPAD() {
		driver.get(IAutoconst.url);
		setUserName(IAutoconst.ResellerAdmin);
		setPassword(IAutoconst.ResellerPassword);
		clickLogin();
	}
}
