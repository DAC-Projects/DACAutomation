package com.dac.main.POM_LPAD;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dac.main.BasePage;

import resources.BaseClass;
import resources.IAutoconst;



public class Page_LPADLogin extends BasePage
{
	WebDriver driver;

	@FindBy(name="username")
	private static WebElement UserNameTB;
	
	@FindBy(id="txt_password")
	private static WebElement PasswordTB;
	
	@FindBy(id="btlogin")
	private static WebElement LoginBTN;
	
	
	public  Page_LPADLogin(WebDriver driver) {
		super(driver);
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
	}
	
	private  void setUserName(String un) throws InterruptedException {
		
		Thread.sleep(2000);
		UserNameTB.sendKeys(un);
	}
	
	private  void setPassword(String pwd) throws InterruptedException {
	
		Thread.sleep(2000);
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
	
	private  void clickLogin() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Trying to Login");
		clickelement(LoginBTN);
		
	}
	
	public  void LoginTOLPAD() throws InterruptedException {
		Thread.sleep(10000);
		driver.get(IAutoconst.LPADUrlBeta);
		
		setUserName(IAutoconst.ResellerAdmin);
		System.out.println(IAutoconst.ResellerAdmin);
		setPassword(IAutoconst.ResellerPassword);
		clickLogin();
	}
}
