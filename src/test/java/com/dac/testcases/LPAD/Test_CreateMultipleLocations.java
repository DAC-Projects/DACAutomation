package com.dac.testcases.LPAD;

import java.io.IOException;

import org.jfree.base.BasicProjectInfo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dac.main.POM_LPAD.Page_LPADHome;
import com.dac.main.POM_LPAD.Page_LocationBasicInfoTab;
import com.dac.main.POM_LPAD.Page_LocationBusinessInfoTab;
import com.dac.main.POM_LPAD.Page_LocationManageProductsTab;
import com.dac.main.POM_LPAD.Page_LocationNavigationTabList;
import com.dac.main.POM_LPAD.Page_LocationsListPage;

import resources.ExcelHandler;


public class Test_CreateMultipleLocations extends LaunchLPAD {
	Page_LocationBasicInfoTab basicInfo;
	Page_LocationBusinessInfoTab businessInfo;
	Page_LocationNavigationTabList tabs;
	Page_LocationManageProductsTab products;
	Page_LPADHome home;
	Page_LocationsListPage locations;
	WebDriverWait wait;  
	JavascriptExecutor js;
	String basicInfoData[][];
	ExcelHandler wb, newlocations;
	
/*@BeforeTest
public void readLocationData() {
	CommonFunctions cf=new CommonFunctions(driver);
	try {
		cf.ReadExcelData("Data-Input", "Locationdata.xlsx", "data");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
	
//@Test(dependsOnMethods = "com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD")
//public void TC_NavigateToLocationsPage() {
//	System.out.println("Step1: Navigate To Locations Page ");
//	home=new Page_LPADHome(driver);
//	home.NavigateToLocations();
//		  
//}
//
//@Test(dependsOnMethods= {"TC_NavigateToLocationsPage"})
//public void TC_CreateLocations() {
//	System.out.println("Step2: Click on [Create Location] Button");
//	locations=new Page_LocationsListPage(driver);
//	locations.NaviagteToCreateLocation();
//}

@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_EnterLocationData() throws Exception {
	int column=17;
	System.out.println("Step4: Enter Location Data");
	wait=new WebDriverWait(driver, 30);
	wb = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
	
	basicInfoData = wb.getExcelTable();
	int TotalRow=basicInfoData.length-1;
	
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	basicInfo=new Page_LocationBasicInfoTab(driver);
	businessInfo=new Page_LocationBusinessInfoTab(driver);
	products=new Page_LocationManageProductsTab(driver);
	
	
	for (int i=1;i<=TotalRow;i++) {
		
		home.NavigateToLocations();
		locations.NaviagteToCreateLocation();
		Thread.sleep(1000);
		basicInfo.fillBasicInfoData(i);
		tabs.navigateBusinessInfoTab();
		businessInfo.fillBusinessInfoData();
		tabs.navigateProductsTab();
		products.clickOnDSOptions();
		tabs.submitLocation();
		Thread.sleep(1000);
		locationNumber=basicInfo.getLocationNumber();
		System.out.println("Row is: "+i+"Location Number is: "+locationNumber);
		home.writeToNotepad(locationNumber);
		
		wb.setCellValue( i, column, locationNumber);
		
	}
	
	
	System.out.println("Location Data Insertion Completed.....");
}

//@Test(dependsOnMethods= {"TC_EnterBasicInfoData"})
//public void TC_EnterPFOData() throws Exception {
//	System.out.println("Step4: Enter PFO Data");
//	products=new Page_LocationManageProductsTab(driver);
//	wait=new WebDriverWait(driver, 30);
//	tabs.navigateProductsTab();
////	Thread.sleep(2000);
//	products.clickOnDSOptions();
//	System.out.println("PFO Data Insertion Completed.....");
//}
//@Test(dependsOnMethods= {"TC_EnterBusinessInfoData"})
//public void TC_SubmitLocation() throws Exception {
//
//	tabs=new Page_LocationNavigationTabList(driver);
//	basicInfo=new Page_LocationBasicInfoTab(driver);
//	tabs.submitLocation();
////	tabs.navigateBasicInfoTab();
//	locationNumber=basicInfo.getLocationNumber();
//	System.out.println("Location Number is: "+locationNumber);
//	System.out.println("Location Data Submission completed.....");
//	
//}

}
