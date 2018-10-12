package com.dac.main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.CurrentState;

public class LoginAC_Beta {
	
	WebDriver driver;

	@FindBy(id="user_username")
	private WebElement UserNameTB;
	
	@FindBy(id="user_password")
	private WebElement PasswordTB;
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement loginBTN;
	
	@FindBy(id="search_email")
	private WebElement search_email;
	
	@FindBy(linkText="Phoenix Dashboard")
	private WebElement Dashboard;
	
	
	public LoginAC_Beta() {
		this.driver = CurrentState.getDriver();
		PageFactory.initElements(driver, this);
		
	}
	
	public void setUserName(String un) {
		UserNameTB.sendKeys(un);
	}
	
	public void setPassword(String pwd) {
		PasswordTB.sendKeys(pwd);
	}
	
	public void clickLogin() {
		//loginBTN.submit();
		loginBTN.sendKeys(Keys.ENTER);
	}
	
	public void clickDashboardLink() {
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(Dashboard));
		Dashboard.sendKeys(Keys.ENTER);;
	}
	
	public void findUser(String data) {
		search_email.sendKeys(data);
		search_email.sendKeys(Keys.ENTER);
	}
}

