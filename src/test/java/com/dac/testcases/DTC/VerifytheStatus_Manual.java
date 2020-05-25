package com.dac.testcases.DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_TPSEE.TPSEE_ESRreports_Page;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.CurrentState;
import resources.ExcelHandler;

public class VerifytheStatus_Manual {
	public static WebDriver driver;
	 WebDriverWait wait;
	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test	
 public void launchBrowser() {
	  
	   WebDriverManager.chromedriver().version("81.0.4044.69").setup(); 
	   driver=new ChromeDriver();
	 // System.setProperty("webdriver.chrome.driver","C:\\Users\\abinaya\\Downloads\\chromedriver.exe");
	  driver.get(url);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  System.out.println(driver.getTitle());
 }

	
	@Test( dependsOnMethods = { "launchBrowser"})
	 public void LoginDTC() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  DTC_Navigation navi=new DTC_Navigation(driver);
		  dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
		  dtcLogin.pressYesKey();
		  String RqID = null, LO_number="null";
		  String apple_RqID=null;
		  String zomato_RqID=null;
		  String here_RqID=null;
		  String manual_RqID =null;
		  String tomtom_Request_ID=null;
		  String Manual_ID=null;
		  String vendor="Bing";
		  String LO_number_API= null;
		  String pageTitle= dtcLogin.getTitle(driver);
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					//CurrentState.getDriver().navigate().refresh();
			         //apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
			         zomato_RqID = wb.getCellValue(1, wb.seacrh_pattern("Zomato_Request_ID", 0).get(0).intValue());
			         here_RqID = wb.getCellValue(1, wb.seacrh_pattern("Here_Request_ID", 0).get(0).intValue());
			         tomtom_Request_ID = wb.getCellValue(1, wb.seacrh_pattern("tomtom_Request_ID", 0).get(0).intValue());
			         Manual_ID=wb.getCellValue(1, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			         System.out.println(Manual_ID);
			         LO_number = "9000226169";
			         

					System.out.println(apple_RqID);
					System.out.println(zomato_RqID);
					
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			}
		  LO_number_API="9000045938";
		  String Ma_vendor="Bing";
		  System.out.println(pageTitle);
		//dtcLogin.verif_Status_complete(manual_RqID,Ma_vendor);
		//dtcLogin.verif_Status_cancel(RqID);
		dtcLogin.verif_Status_allcomplete(Manual_ID);
		 // dtcLogin.verif_Status_allcancel(RqID);
		  //dtcLogin.API_Transmission(LO_number_API, vendor);
		// dtcLogin.verify_apple(LO_number,apple_RqID);
		//dtcLogin.verify_zomato(LO_number,zomato_RqID );
		//dtcLogin.verify_here(here_RqID);
		  //dtcLogin.verify_tomtom(LO_number,tomtom_Request_ID);
		
	  }
}



