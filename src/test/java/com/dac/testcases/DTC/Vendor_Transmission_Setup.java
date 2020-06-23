package com.dac.testcases.DTC;

import org.openqa.selenium.Dimension;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_DTC.DTC_Transmission_Setup;
import io.github.bonigarcia.wdm.WebDriverManager;
import resources.BaseClass;
import resources.ExcelHandler;

public class Vendor_Transmission_Setup {
	
	public static WebDriver driver;
	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test	
  public void launchBrowser() {
	   WebDriverManager.chromedriver().version("83.0.4103.39").setup(); 
	   driver=new ChromeDriver();
	// System.setProperty("webdriver.chrome.driver","C:\\Users\\abinaya\\Downloads\\chromedriver.exe");
	   driver.get(url);
    	Dimension d = new Dimension(1000,700);
	   driver.manage().window().maximize();
	   driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	   System.out.println(driver.getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(driver);
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
	    dtcLogin.pressYesKey();
		BaseClass.addEvidence(driver, "Testing", "yes");

}
	@Test( dependsOnMethods = { "launchBrowser"})
	 public void AddindNewfiled() throws Exception {
		DTC_Transmission_Setup vendor=new DTC_Transmission_Setup(driver);
		
		//vendor.Adding_New_filed("Bing","Bahrain Manual Set Up","Test","Account Name");
	}
	@Test( dependsOnMethods = { "AddindNewfiled"})
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
}
	}
