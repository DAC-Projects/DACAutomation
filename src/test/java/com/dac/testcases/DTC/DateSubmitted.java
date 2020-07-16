package com.dac.testcases.DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_TPSEE.TPSEE_ESRreports_Page;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class DateSubmitted extends BaseClass {
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
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		  DTC_Navigation navi=new DTC_Navigation(CurrentState.getDriver());
		  
		  String RqID = null,
		  LO_number="null";
		  String apple_RqID=null;
		  String zomato_RqID=null;
		  String here_RqID=null;
		  String manual_RqID =null;
		  String tomtom_Request_ID=null;
		  String Manual_ID=null;
		  String vendor="Bing";
		  String LO_number_API= null;
		  String pageTitle= dtcLogin.getTitle(CurrentState.getDriver());
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1");
				wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					//CurrentState.getDriver().navigate().refresh();
			         zomato_RqID = wb.getCellValue(1, wb.seacrh_pattern("Zomato_Request_ID", 0).get(0).intValue());
			         here_RqID = wb.getCellValue(1, wb.seacrh_pattern("Here_Request_ID", 0).get(0).intValue());
			         tomtom_Request_ID = wb.getCellValue(1, wb.seacrh_pattern("tomtom_Request_ID", 0).get(0).intValue());
			        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());

					System.out.println(apple_RqID);
					System.out.println(zomato_RqID);
					
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			}
		 dtcLogin.apple_date(apple_RqID);
			addEvidence(CurrentState.getDriver(), "Apple", "yes");

		 dtcLogin.zomato_date(zomato_RqID);
			addEvidence(CurrentState.getDriver(), "Zomato", "yes");

		 dtcLogin.here_date(here_RqID);
			addEvidence(CurrentState.getDriver(), "Here", "yes");

		  dtcLogin.tomtom_date(tomtom_Request_ID);	
			addEvidence(CurrentState.getDriver(), "tomtom", "yes");

	  }
}



