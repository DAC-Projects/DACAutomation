package com.dac.testcases.DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Duplicate_Management;
import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_TPSEE.TPSEE_ESRreports_Page;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.CurrentState;
import resources.ExcelHandler;

public class DTCLoginPage_TC {

	//public static WebDriver driver;
	 WebDriverWait wait;
	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test	
  public void launchBrowser() {
		  CurrentState.getDriver().get(url);
	    	Dimension d = new Dimension(1000,700);
		  
		   System.out.println(CurrentState.getDriver().getTitle());
		   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
		    dtcLogin.pressYesKey();

  }

	 @Test( dependsOnMethods = { "launchBrowser"})
 public void LoginDTC() throws Exception {
	  DTC_Duplicate_Management dtcLogin=new DTC_Duplicate_Management(CurrentState.getDriver());
	  DTC_Navigation navi=new DTC_Navigation(CurrentState.getDriver());

	  navi.Dup();
	  int i=1;
	  navi.Count_check("9000223167");
	  navi.Count_check_ignore("9000223167");
	  navi.excel(i);
	  
  }
	 /*@Test( dependsOnMethods = { "launchBrowser"})
	 public void LoginDTC() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  DTC_Navigation navi=new DTC_Navigation(driver);
		  dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
		  dtcLogin.pressYesKey();
		  String pageTitle= dtcLogin.getTitle(driver);
		  String LO_number = null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/DTC.xlsx", "Sheet1"); wb.deleteEmptyRows();
				for(int i=1;i<=1;i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					if(i>1) CurrentState.getDriver().navigate().refresh();
					LO_number = wb.getCellValue(i, wb.seacrh_pattern("LO_number", 0).get(0).intValue());
					System.out.println(LO_number);
					count++;
				}}
		  catch(Exception e) {
		  e.printStackTrace();
			}
		//  dtcLogin.Transmission(LO_number);
		  //dtcLogin.API_Transmission();
		  System.out.println(pageTitle);
		}*/
		 
	  }
