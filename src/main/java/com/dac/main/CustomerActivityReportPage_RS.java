package com.dac.main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerActivityReportPage_RS extends BasePage{

	WebDriver driver;
	Actions action;
	Select select;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public CustomerActivityReportPage_RS(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="btnDownload")
	private WebElement downloadReportBTN;
	
	@FindBy(xpath="//div[contains(@class,'campaignLocation')]/b")
	private WebElement campName;
	
	public void clickDownloadReport() {
		downloadReportBTN.click();
	}
	
	/**
	 * @eText : Expected camp name Text tend to display in customer activity page 
	 * note : eText should be Case Sensitive*/
	public void verifyCampName(String eText) {
		verifyText(campName, eText);
	}
	
}
