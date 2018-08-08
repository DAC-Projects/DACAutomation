package com.dac.main.POM_CA;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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
	
	@FindBy(xpath ="//div[contains(text(), 'Export is processing')]/parent::div")
	private WebElement exportMessage;
	
	//
	
	public void exportvisibilityReport() throws InterruptedException {
		int windows =driver.getWindowHandles().size();
		click(exportBtn);
	if(	wait.until(ExpectedConditions.attributeContains(exportMessage, "style", "display: none")))
	{		
		try
		{	
		     Robot robot = new Robot();
		     robot.setAutoDelay(250);
		     robot.keyPress(KeyEvent.VK_ALT);
		     robot.keyPress(KeyEvent.VK_S);
		     Thread.sleep(1000);
		     robot.keyRelease(KeyEvent.VK_S);
		     robot.keyRelease(KeyEvent.VK_ALT);
		}
		catch (AWTException e)
		{
		    e.printStackTrace();
		}
	}
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
