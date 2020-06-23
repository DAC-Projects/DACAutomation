package com.dac.testcases.DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Google_Category;
import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_DTC.DTC_Transmission;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.BaseClass;
import resources.CurrentState;

public class Google_categories extends BaseClass {

//	public static WebDriver driver;
	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test(description="Test")	
  public void launchBrowser() {
	  
	   System.out.println("driver test"+CurrentState.getDriver());
	  


	   CurrentState.getDriver().get(url);
	  
	  
	   System.out.println(CurrentState.getDriver().getTitle());
	

}
	@Test( dependsOnMethods = { "launchBrowser"},description="Test")
	 public void LoginDTC() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  DTC_Google_Category google=new DTC_Google_Category(CurrentState.getDriver());
		  dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
		  dtcLogin.pressYesKey();
	
		  addEvidence(CurrentState.getDriver(), "Testing", "yes");
		//  BaseClass.addEvidence(driver, "Testing", "yes");
		  /*google.AddGooglecategory();
		  google.verifyAddeedGoogleCategory();
		  google.deleteGoogleCategory();
		  google.verifydeletedGooglecategory();
		  google.AddDACcatgeory();
		  google.verifyDACcatgeory();
		  google.DeleteDACcatgeory();
		  google.verifyDeleteDACcatgeory();*/
//		  google.newcategory("Google","Google Category","Guyana");
//		  google.scrollelement();
//		  System.out.println();
//		  
//		  String pageTitle= dtcLogin.getTitle(driver);
//		  System.out.println(pageTitle);
//		  BaseClass.addEvidence(driver, "Testing", "yes");
		 // driver.close();
		  
	}
}
