package com.dac.main.POM_CA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dac.main.BasePage;

public class CA_Visibility_Page extends BasePage{

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	
	public CA_Visibility_Page(WebDriver driver) {
		
		this.driver = driver;
		wait = new WebDriverWait(driver, 35);
		action=new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css ="button#btnExport>span")
	private WebElement exportBtn;
	
	public void exportvisibilityReport() {
		
		click(exportBtn);
	}
	
public void exportaccuracyReport() {
		click(exportBtn);
	}

private void click(WebElement exportBtn2) {
	wait.until(ExpectedConditions.elementToBeClickable(exportBtn2));
	scrollByElement(exportBtn2, driver);
	try {
		//Thread.sleep(5000);
		action.moveToElement(exportBtn2).click().perform();
		
	}
	catch(Exception e) {
		e.printStackTrace();
		exportBtn2.click();
	}
	
}


}
