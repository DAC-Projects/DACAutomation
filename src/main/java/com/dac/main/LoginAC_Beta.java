package com.dac.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginAC_Beta {

	@FindBy(id="user_username")
	private WebElement UserNameTB;
	
	@FindBy(id="user_password")
	private WebElement PasswordTB;
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement loginBTN;
	
	public LoginAC_Beta(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setUserName(String un) {
		UserNameTB.sendKeys(un);
	}
	
	public void setPassword(String pwd) {
		PasswordTB.sendKeys(pwd);
	}
	
	public void clickLogin() {
		loginBTN.submit();
	}
}
