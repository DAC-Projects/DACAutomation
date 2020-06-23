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

	public static WebDriver driver;
	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test	
  public void launchBrowser() {
	   WebDriverManager.chromedriver().version("83.0.4103.39").setup(); 
	   driver=new ChromeDriver();
	// System.setProperty("webdriver.chrome.driver","C:\\Users\\abinaya\\Downloads\\chromedriver.exe");
	   driver.get(url);
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	   System.out.println(driver.getTitle());
		BaseClass.addEvidence(driver, "Testing", "yes");

}
	@Test( dependsOnMethods = { "launchBrowser"})
	 public void LoginDTC() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  DTC_Google_Category google=new DTC_Google_Category(driver);
		  dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
		  dtcLogin.pressYesKey();
		  google.AddGooglecategory("Google","Google Category","Guyana");
		  google.verifyAddeedGoogleCategory("DAC","DAC");
		  google.deleteGoogleCategory("Google","Google Category","Guyana");
		  google.verifydeletedGooglecategory("DAC","DAC");
		  google.AddDACcatgeory("Google");
		  google.verifyDACcatgeory("Google","Google");
		  google.DeleteDACcatgeory("DAC","DAC");
		  google.verifyDeleteDACcatgeory("Google","Google Category");
		  google.newcategory("Google","Google Category","Guyana");
		  google.scrollelement();
		  System.out.println();
		  String pageTitle= dtcLogin.getTitle(driver);
		  System.out.println(pageTitle);
		  BaseClass.addEvidence(driver, "Testing", "yes");
		 // driver.close();
		  
	}
}
