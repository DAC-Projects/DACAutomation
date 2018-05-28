package com.dac.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BasePage {
	

	
	/*
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
	
/*	protected void uploadFile(String fileName, String extension) {
		
		File uploadingFilePath =new File("./"+fileName+extension);
		String fileAbsPath=uploadingFilePath.getAbsolutePath();
		
		AutoItX x=new AutoItX();
		x.winWaitActive("Open");
		x.controlFocus("Open", "", "Edit1");
		x.ControlSetText("Open", "", "Edit1", fileAbsPath);
		x.controlClick("Open", "", "Button1");
	}*/
	
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String dateFormat = sdf.format(date);
		return dateFormat.toString();
	}
	

}
