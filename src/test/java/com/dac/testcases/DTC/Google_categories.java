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
import resources.ExcelHandler;

public class Google_categories extends BaseClass {

	String url="https://beta-dtc-web.azurewebsites.net/";
	
	@Test(description="Test")	
		public void launchBrowser() {
		  DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
		 
		  System.out.println("driver test"+CurrentState.getDriver());
		  CurrentState.getDriver().get(url);
		  dtcLogin.submitLogin("adevaraj@dacgroup.com","lockdown@123");
		  addEvidence(CurrentState.getDriver(), "Testing", "yes");

		  dtcLogin.pressYesKey();
		  String pageTitle= dtcLogin.getTitle(CurrentState.getDriver());
		  System.out.println(pageTitle);


}
	@Test( dependsOnMethods = { "launchBrowser"},description="Test")
	 public void LoginDTC() throws Exception {
				int count = 1;
				ExcelHandler wb = new ExcelHandler("./data/DTC.xlsx", "Google");
				wb.deleteEmptyRows();
				int a=wb.getRowCount();
				System.out.println(a);
				 DTC_Google_Category google=new DTC_Google_Category(CurrentState.getDriver());
				 google.cat();
				 addEvidence(CurrentState.getDriver(), "google", "yes");

				for(int i=1;i<=a;i++) {
					System.out.println("*******************  Scenarios : "+ count +"Starts ****************************");
					String goocategory = wb.getCellValue(i, wb.seacrh_pattern("goocategory", 0).get(0).intValue());
					String selectcatgoogle=wb.getCellValue(i, wb.seacrh_pattern("selectcatgoogle", 0).get(0).intValue());
					String selectcountry=wb.getCellValue(i, wb.seacrh_pattern("selectcountry", 0).get(0).intValue());
					String Dac_category=wb.getCellValue(i, wb.seacrh_pattern("Dac_category", 0).get(0).intValue());
					String Vendor_category=wb.getCellValue(i, wb.seacrh_pattern("Vendor_category", 0).get(0).intValue());
					String category=wb.getCellValue(i, wb.seacrh_pattern("Category", 0).get(0).intValue());
					String selectdaccategory=wb.getCellValue(i, wb.seacrh_pattern("selectdaccategory", 0).get(0).intValue());
					String countrycode=wb.getCellValue(i, wb.seacrh_pattern("countrycode", 0).get(0).intValue());
					String filepath=wb.getCellValue(i, wb.seacrh_pattern("filepath", 0).get(0).intValue());

					count++;
					System.out.println(goocategory+selectcatgoogle+category);
		 
		 google.AddGooglecategory(goocategory,selectcatgoogle,selectcountry,Dac_category,Vendor_category);
		  addEvidence(CurrentState.getDriver(), "AddGooglecategory", "yes");

		google.verifyAddeedGoogleCategory(category,selectdaccategory,goocategory,Dac_category,Vendor_category,countrycode);
		  addEvidence(CurrentState.getDriver(), "verifyAddeedGoogleCategory", "yes");

		 google.deleteGoogleCategory(goocategory,selectcatgoogle,selectcountry,Vendor_category);
		  addEvidence(CurrentState.getDriver(), "deleteGoogleCategory", "yes");

		 google.verifydeletedGooglecategory(category,selectdaccategory,Dac_category,countrycode, goocategory);
		  addEvidence(CurrentState.getDriver(), "verifydeletedGooglecategory", "yes");

		 google.AddDACcatgeory(goocategory,Vendor_category,countrycode);
		  addEvidence(CurrentState.getDriver(), "AddDACcatgeory", "yes");

		  google.verifyDACcatgeory(goocategory,selectcatgoogle,selectcountry,Vendor_category);
		  addEvidence(CurrentState.getDriver(), "verifyDACcatgeory", "yes");

		 google.DeleteDACcatgeory(category,selectdaccategory,goocategory,Dac_category,Vendor_category,countrycode);
		  addEvidence(CurrentState.getDriver(), "DeleteDACcatgeory", "yes");

		  google.verifyDeleteDACcatgeory(goocategory,selectcatgoogle,selectcountry,Vendor_category);
		  addEvidence(CurrentState.getDriver(), "verifyDeleteDACcatgeory", "yes");

		  google.newcategory(goocategory,selectcatgoogle , selectcountry, countrycode);
		  addEvidence(CurrentState.getDriver(), "New category added", "yes");

		  google.scrollelement(filepath);	
		 // addEvidence(CurrentState.getDriver(), "scrollelement", "yes");

		  Thread.sleep(3000);
			  
	}}}

