package com.dac.testcases.DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Navigation;
import com.dac.main.POM_DTC.DTC_Transmission;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.CurrentState;
import resources.ExcelHandler;
public class Create_Transmission {

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
		  String pageTitle= dtcLogin.getTitle(driver);
		  String LO_number = null , account= null, reseller=null, parameter=null;
		  try {	
			  dtcLogin.Transmission();

				int count = 1;
				int a1=1;
				int b=0;
				ExcelHandler wb = new ExcelHandler("./data/DTC.xlsx", "Sheet1");
				wb.deleteEmptyRows();
				int a=wb.getRowCount();
				System.out.println(a);
				for(int i=1;i<=a;i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					
					//CurrentState.getDriver().navigate().refresh();
					LO_number = wb.getCellValue(i, wb.seacrh_pattern("LO_number", 0).get(0).intValue());
					System.out.println(LO_number);
					
					dtcLogin.manual_Transmission(LO_number,a1,b);
					a1++;
					count++;
				}}
		  catch(Exception e) {
		  e.printStackTrace();
			}
		  System.out.println(pageTitle);
		

		
		  
		  
		}
	
	@Test( dependsOnMethods = { "LoginDTC"})
	 public void Addrequest() throws Exception {
		 DTC_Transmission dtcLogin=new DTC_Transmission(driver);
		  DTC_Navigation navi=new DTC_Navigation(driver);
		  String pageTitle= dtcLogin.getTitle(driver);
		
		  String LO_number = null , account= null, reseller=null, parameter=null;
		  try {	
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/DTC.xlsx", "Sheet1");
				wb.deleteEmptyRows();
				int a=wb.getRowCount();
				System.out.println(a);
				for(int i=1;i<=1;i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					
					//CurrentState.getDriver().navigate().refresh();
					LO_number = wb.getCellValue(i, wb.seacrh_pattern("LO_number", 0).get(0).intValue());
					account=wb.getCellValue(i, wb.seacrh_pattern("Account Name", 0).get(0).intValue());
					reseller=wb.getCellValue(i, wb.seacrh_pattern("Reseller", 0).get(0).intValue());
					parameter=wb.getCellValue(i, wb.seacrh_pattern("Parameter", 0).get(0).intValue());
					System.out.println(LO_number);
					count++;
				}}
		  catch(Exception e) {
		  e.printStackTrace();
			}
		  System.out.println(pageTitle);
		

		dtcLogin.apple_Trans(account);	  
		dtcLogin.zoamto_Trans(account);
		dtcLogin.Here_Trans(account);
		dtcLogin.Tomtom_Trans(LO_number, account, reseller, parameter);

		 
		
		  
		  driver.close();
		}
		 
	  }
