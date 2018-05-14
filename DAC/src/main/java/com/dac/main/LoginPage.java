package com.dac.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	public LoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "txt_username")
	WebElement unTB;

	@FindBy(id = "txt_password")
	WebElement pwTB;

	@FindBy(id = "btlogin")
	WebElement loginBTN;

	@FindBy(xpath = "//div[@id='errordiv1']/span")
	WebElement warning;

	@FindBy(xpath = "//img[@src='http://res.cloudinary.com/cloudasset/image/upload/v1461602331/map/images/f5o8ptkieiv15gbih1jc.png']")
	WebElement logo;

	public void clearUserName() {
		unTB.clear();
	}
	
	public void setUserName(String un) {
		unTB.sendKeys(un);
	}

	public void clearPassword() {
		pwTB.clear();
	}
	
	public void setPassword(String pw) {
		pwTB.sendKeys(pw);
	}

	public void clickLogin() {
		loginBTN.click();
	}

	public WebElement warning() {
		return warning;
	}

	public WebElement logo() {
		return logo;
	}

}
