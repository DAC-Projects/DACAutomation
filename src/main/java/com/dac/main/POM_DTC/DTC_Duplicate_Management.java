package com.dac.main.POM_DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DTC_Duplicate_Management{

	WebDriver driver;
	Actions action; 

	@FindBy(xpath="//*[@id=\"i0116\"]")
	private WebElement txtLoginName;
	
	@FindBy(xpath="//*[@id='idSIButton9']")
	private WebElement btnSignIn;
	
/*	@FindBy(css="input[value=Sign in]")
	private WebElement btnSignIn;*/
	//*[@id="idSIButton9"]
	@FindBy(css="input[value=Next]")
	private WebElement btnNext;
	
	@FindBy(xpath="//*[@id=\"i0118\"]")
	private WebElement txtPassword;
	
	@FindBy(xpath="/html/body/div/form/div[1]/div/div[1]/div[2]/div/div[2]/div/div[3]/div[2]/div/div/div[2]")
	private WebElement btnYes;
	
	@FindBy(xpath="//*[@id=\"idA_PWD_ForgotPassword\"]")
	private WebElement txtFGTPassword;
	
	//*[@id="idA_PWD_ForgotPassword"]
	
	

	public DTC_Duplicate_Management(WebDriver driver) {
		this.driver =  driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		
	}
	
	public void EnterLogin(String name) {
		txtLoginName.sendKeys(name);
		txtLoginName.sendKeys(Keys.CONTROL, Keys.ENTER);
		
	}
	
	public void EnterPassword(String pwd) {
		txtPassword.sendKeys(Keys.CONTROL,Keys.CLEAR);
		txtPassword.sendKeys(pwd);
		
		txtPassword.sendKeys(Keys.CONTROL, Keys.TAB);
		txtFGTPassword.sendKeys(Keys.CONTROL, Keys.TAB);
		btnSignIn.sendKeys(Keys.RETURN);
		System.out.println("Click on sign in after password enter");

	}
	/*public void Next() {
		btnNext.click();
	}*/
	public void submitLogin(String name, String pwd) {
		EnterLogin(name);
		EnterPassword(pwd);
//		btnSignIn.click();
//		action.moveToElement(btnSignIn).click().perform();

	}
	public void pressYesKey() {
		btnSignIn.sendKeys(Keys.RETURN);

	}
	
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
}
