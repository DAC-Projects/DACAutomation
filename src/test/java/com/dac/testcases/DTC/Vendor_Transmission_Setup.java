package com.dac.testcases.DTC;

import org.openqa.selenium.Dimension;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Google_Category;
import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_DTC.DTC_Transmission_Setup;
import io.github.bonigarcia.wdm.WebDriverManager;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;

public class Vendor_Transmission_Setup extends BaseClass{
	
	//public static WebDriver driver;
	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test	
  public void launchBrowser() {
	// System.setProperty("webdriver.chrome.driver","C:\\Users\\abinaya\\Downloads\\chromedriver.exe");
		  CurrentState.getDriver().get(url);
    	Dimension d = new Dimension(1000,700);
	  
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    dtcLogin.pressYesKey();

}
	@Test( dependsOnMethods = { "launchBrowser"})
	 public void AddindNewfiled() throws Exception {
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(CurrentState.getDriver());
		int count = 1;
		ExcelHandler wb = new ExcelHandler("./data/Google Attributes for Vendor Transmission Setup.xlsx", "YesNo");
		wb.deleteEmptyRows();
		int a=wb.getRowCount();
		System.out.println(a);
		vendor.setup("Google", "Google");
		for(int i=1;i<=2;i++) {
			System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
			String fieldname = wb.getCellValue(i, wb.seacrh_pattern("Vendor Field Name", 0).get(0).intValue());
			System.out.println(fieldname);
			//String selectcatgoogle=wb.getCellValue(i, wb.seacrh_pattern("selectcatgoogle", 0).get(0).intValue());
			vendor.Adding_New_filed("Google","Google","Payment Type - Apple Pay","Payment Type - Apple Pay");
			addEvidence(CurrentState.getDriver(), "Adding new filed", "yes");
			count++;
		}
	}
	/*@Test( dependsOnMethods = { "AddindNewfiled"})
	 public void Transmission() throws Exception {
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(driver);
		//vendor.transmission("9000226896");
		//vendor.verif_Status_complete("95675", "Bing","Bahrain");
		ExcelHandler wb = new ExcelHandler("C:\\Users\\abinaya\\Downloads\\BahrainManualSetUp_95675_Active.xlsx", "BahrainManualSetUp_95675_Active");
		wb.deleteEmptyRows();
		int a=wb.getRowCount();
		System.out.println(a);
		for(int i=1;i<=a;i++) {
			System.out.println("*******************  Scenarios : "+"Starts ****************************");
			String field = wb.getCellValue(i, wb.seacrh_pattern("Name", 0).get(0).intValue());
			System.out.println(field);
			
	}
}*/
	}
