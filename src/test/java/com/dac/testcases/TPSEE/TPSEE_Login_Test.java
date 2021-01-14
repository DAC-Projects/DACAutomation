package com.dac.testcases.TPSEE;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.dac.main.POM_TPSEE.TPSEE_Login_Page;
import com.dac.testcases.LPAD.LaunchLPAD;

import resources.BaseClass;
import resources.CurrentState;


public class TPSEE_Login_Test extends LaunchLPAD{
	
	
	String UserName="TestAcccountOwner4";
	String Password="111111";
	TPSEE_Login_Page TSEE_loginPage;
	
	@Test
	  public void TC_Login_TSEE() throws Exception {
//			GetColmunNumber number =new GetColmunNumber();
			TSEE_loginPage=new TPSEE_Login_Page(driver);
//			driver.get("https://transparensee-dashboard-beta.azurewebsites.net/");
			TSEE_loginPage.TSEE_Login(UserName, Password);
//		  String pageTitle= loginPage.getTitle();
		 System.out.println("wait completes");
		 
//		 TakesScreenshot
	}
	
}


