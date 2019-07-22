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
	
	@FindBy(linkText="TransparenSee")  //Phoenix Dashboard
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
		Dashboard.sendKeys(Keys.CONTROL, Keys.ENTER);;
	}
	
	public void findUser(String data) {
		search_email.sendKeys(data);
		search_email.sendKeys(Keys.ENTER);
	}
	
	public void notificationHandle() {
			if(driver.findElements(By.xpath("//span[text()='Okay']")).size() != 0){
				System.out.println("Issue Notification Pop up is Present");
				driver.findElement(By.xpath("//span[text()='Okay']")).click();
				}else{
				System.out.println("Issue Notification Pop up Not Present");
				}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(driver.findElements(By.xpath("//span[text()='No Thanks.']")).size() != 0){
				System.out.println("DashBoard update Pop up is Present");
				driver.findElement(By.xpath("//span[text()='No Thanks.']")).click();
				}else{
				System.out.println("DashBoard update Pop up Not Present");
				}
			
		}
	  
}

