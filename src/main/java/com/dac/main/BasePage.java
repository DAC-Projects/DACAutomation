package com.dac.main;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import autoitx4java.AutoItX;

public class BasePage {
	
	/**
	public void verifyPageIsDisplayed(WebDriver driver,String eResult) {
		String sETO=AutoUtil.getProperty(IAutoConst.CONFIG_PATH, "ETO");
		long ETO=Long.parseLong(sETO);
		WebDriverWait wait=new WebDriverWait(driver,ETO);
		try {
			Reporter.log(eResult,true);
			wait.until(ExpectedConditions.titleIs(eResult));
			Reporter.log("PASS: Expected Page is Displayed",true);
		}
		catch(Exception e) {
			Reporter.log("FAIL: Expected Page is NOT Displayed",true);
			Assert.fail();
		}
	}
	
	*/
	
	public void verifyText(WebElement e,String eText) {
		String aText=e.getText().trim();
		System.out.println(aText);
		Assert.assertEquals(aText, eText);
	}
	

	public void scrollByElement(WebElement element,WebDriver driver) {
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int yLoc = element.getLocation().getY();
		int xLoc = element.getLocation().getX();
		js.executeScript("window.scrollTo("+xLoc+", "+yLoc+")");
		//js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	protected void uploadFile(String fileName, String extension) {
		
		File file =new File("./"+fileName+extension);
		String fileAbsPath=file.getAbsolutePath();
		
		AutoItX x=new AutoItX();
		x.winWaitActive("Open");
		x.controlFocus("Open", "", "Edit1");
		x.ControlSetText("Open", "", "Edit1", fileAbsPath);
		x.controlClick("Open", "", "Button1");
	}
	

}
