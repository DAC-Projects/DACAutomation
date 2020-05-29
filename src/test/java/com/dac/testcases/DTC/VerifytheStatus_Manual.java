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
	  driver.get(url);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	  System.out.println(driver.getTitle());
 }
	
	@Test( dependsOnMethods = { "launchBrowser"})
	 public void complete() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  DTC_Navigation navi=new DTC_Navigation(driver);
		  dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
		  dtcLogin.pressYesKey();
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(1, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);	
			        String vendor="Bing";
			       // dtcLogin.verif_Status_complete(Manual_ID,vendor);
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			}
		
	  }
	

	@Test( dependsOnMethods = { "complete"})
	 public void cancel() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(2, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);		
			 	   //dtcLogin.verif_Status_cancel(Manual_ID);
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			}
		
	  }
	

	@Test( dependsOnMethods = { "cancel"})
	 public void allcomplete() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(3, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);			        
			 	   // dtcLogin.verif_Status_allcomplete(Manual_ID);
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			}
	  }

	@Test( dependsOnMethods = { "allcomplete"})
	 public void allcancel() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  String Manual_ID=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         Manual_ID=wb.getCellValue(4, wb.seacrh_pattern("Manual_ID", 0).get(0).intValue());
			        System.out.println(Manual_ID);
			        //dtcLogin.verif_Status_allcancel(Manual_ID);
					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			}
		
	  }
	
	@Test( dependsOnMethods = { "allcancel"})
	 public void Other_venodrs() throws Exception {
		  DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  String RqID = null,
		  LO_number="9000226217";
		  String apple_RqID=null;
		  String zomato_RqID=null;
		  String here_RqID=null;
		  String tomtom_Request_ID=null;
		  String Manual_ID=null;
		  String LO_number_API= null;
		  String pageTitle= dtcLogin.getTitle(driver);
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			         apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
			         zomato_RqID = wb.getCellValue(1, wb.seacrh_pattern("Zomato_Request_ID", 0).get(0).intValue());
			         here_RqID = wb.getCellValue(1, wb.seacrh_pattern("Here_Request_ID", 0).get(0).intValue());
			         tomtom_Request_ID = wb.getCellValue(1, wb.seacrh_pattern("tomtom_Request_ID", 0).get(0).intValue());
			      					count++;
				}
		  catch(Exception e)
		  {
		  e.printStackTrace();
			}
		  LO_number_API=" 9000226221";
		 
		  String vendor="Google";
		  String vendor1="Bing";
		  //String vendor2="Foursquare";
		  System.out.println(pageTitle);
		 // dtcLogin.API_Transmission();
		//dtcLogin.API_Transmission_vendor(LO_number_API, vendor1);
		// dtcLogin.API_Transmission_vendor(LO_number_API, vendor);
		dtcLogin.verify_apple(LO_number,apple_RqID);
		dtcLogin.verify_zomato(LO_number,zomato_RqID );
		dtcLogin.verify_here(here_RqID);
		dtcLogin.verify_tomtom(LO_number,tomtom_Request_ID);
		driver.close();
	  }
}



