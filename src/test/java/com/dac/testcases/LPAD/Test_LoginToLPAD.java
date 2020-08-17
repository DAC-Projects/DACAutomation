package com.dac.testcases.LPAD;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.BasePage;
import com.dac.main.POM_LPAD.GetColmunNumber;
import com.dac.main.POM_LPAD.Page_LPADHome;
import com.dac.main.POM_LPAD.Page_LPADLogin;

import resources.CurrentState;



public class Test_LoginToLPAD  {
//	WebDriver driver;
	
@Test
  public void TC_Login_LPAD() throws Exception {
		GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50);
	  
	  loginPage.LoginTOLPAD();
	  
//	  String pageTitle= loginPage.getTitle();
	 System.out.println("wait completes");
	 home.switchToDomain();
	 
	 
  }


}
