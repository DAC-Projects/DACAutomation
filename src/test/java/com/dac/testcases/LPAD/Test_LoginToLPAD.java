package com.dac.testcases.LPAD;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_LPAD.Page_LPADLogin;



public class Test_LoginToLPAD extends LaunchLPAD {
	
	
@Test
  public void TC_Login_LPAD() {
	  Page_LPADLogin loginPage=new Page_LPADLogin(driver);
	  
//	  CommonFunctions.waitBrowser(driver);
	  loginPage.setUserName(UserName);
	  loginPage.setPassword(Password);
	  loginPage.clickLogin();
	  String pageTitle= loginPage.getTitle();
//	  System.out.println("@page "+ pageTitle);
	  if (pageTitle.equalsIgnoreCase("Management Administration Portal Login")) {
		  System.out.println("LPAD Login TC Passed");
	  }else {
		  System.out.println("LPAD Login TC Failed");
	  }
	 WebDriverWait wait=new WebDriverWait(driver, 50);
	 WebElement export= driver.findElement(By.xpath("//*[@id='main-wrapper']/section/div/div/div[2]/div/a"));
	 wait.until(ExpectedConditions.visibilityOf(export));
	 System.out.println("wait completes");
  }


}