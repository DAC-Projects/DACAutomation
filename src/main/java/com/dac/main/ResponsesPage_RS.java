package com.dac.main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResponsesPage_RS extends BasePage{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public ResponsesPage_RS(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="btnReviewsExport")
	private WebElement exportBTN;
	
	public void clickExportBTN() {
		if(exportBTN.isEnabled() & exportBTN.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(exportBTN));
			exportBTN.click();
		}
		else {
			System.out.println("No Data Available in Review Table");
		}
	}
}
