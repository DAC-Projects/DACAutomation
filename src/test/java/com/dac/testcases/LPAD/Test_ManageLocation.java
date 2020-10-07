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
import com.dac.main.POM_LPAD.Page_SiteSpecificInfoTab;

import resources.ExcelHandler;


public class Test_ManageLocation extends LaunchLPAD {
	Page_LocationBasicInfoTab basicInfo;
	Page_LocationBusinessInfoTab businessInfo;
	Page_LocationNavigationTabList tabs;
	Page_LocationManageProductsTab products;
	Page_LPADHome home;
	Page_LocationsListPage locations;
	Page_SiteSpecificInfoTab site;
	WebDriverWait wait;  
	JavascriptExecutor js;
	String basicInfoData[][];
	String locationUpdate[][];
	ExcelHandler wb, newlocations, update;
	


	@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_CreateMultipleLocations.TC_EnterLocationData"})
public void TC_UpdateLPM_OptionsData() throws Exception {
	int column=17;//for getting Location Number from Excel
	System.out.println("Scenario1: Update LPM Product Options with New Options");
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
	site=new Page_SiteSpecificInfoTab(driver);
	
	home.NavigateToLocations();
	String locationNumber  	= wb.getCellValue(1, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
//	String locationNumber=basicInfoData[1][column];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
		products.clickOnDSOptions("UPDATE",wb,1);
		Thread.sleep(2000);
//		tabs.navigateSiteSpecificInfoTab();
//		site.fillSiteSpecificInfoData();
		tabs.updateLocation();
		wb.setCellValue(1, wb.seacrh_pattern("Status", 0).get(0).intValue(), "Option_Update");
//		wb.setCellValue( 1, 18, "PFOUpdate");
	
	System.out.println("LPM Options Updated.....");
}
	
/*
 
@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_ManageLocation_DisableLPM() throws Exception {
	int column=17;//for getting Location Number from Excel
	String status="LPM OFF";
	System.out.println("Scenario2: Disable LPM Product....");
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
	
	home.NavigateToLocations();
	String locationNumber=basicInfoData[2][column];
	System.out.println("Location Number"+ locationNumber);
	//	String locationNumber  	= wb.getCellValue(1, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());

	locations.searchLocation(locationNumber);
	Thread.sleep(5000);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
		boolean change=products.disableLPM();	
		Thread.sleep(2000);
		if(change) {
			System.out.println("Change " + change);
		tabs.updateLocation();
		}
	
	System.out.println(locationNumber+" >> PFO Disabled....");
	update.setCellValue( 2, 0, locationNumber);
	update.setCellValue( 2, 1, status);
}

@Test(dependsOnMethods= {"TC_ManageLocation_DisableLPM"})
public void TC_ManageLocation_EnableLPM() throws Exception {
	int column=17;//for getting Location Number from Excel
	System.out.println("Scenario3: Enable LPM Product....");
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
	
	home.NavigateToLocations();
	String locationNumber=basicInfoData[2][column];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(5000);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
		boolean change=products.enableLPM();	
		Thread.sleep(2000);
		if(change) {
			System.out.println("Change " + change);
		tabs.updateLocation();
		wb.setCellValue( 2, 18, "LPM Turned ON");
		}
	
	System.out.println("PFO: LPM Enabled.....");
}
*/
	/*
//Deactivate Location	
@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_DeactivateLocation() throws Exception {
	int column=17, LocationRow=1;//for getting Location Number from Excel
	String status="DEACTIVATED";
	wait=new WebDriverWait(driver, 30);
	wb = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	basicInfoData = wb.getExcelTable();
	
	System.out.println("Scenario: Deactivating Location....");
	
	//Starting....
	home.NavigateToLocations();
	String locationNumber=basicInfoData[LocationRow][column];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.DeactivateLocation();
	
	System.out.println(locationNumber+" >> Location Deactivated.....");
	//update.setCellValue( 1, 0, locationNumber);
	//update.setCellValue( 1, 1, status);
}
/*	
//Delete Location
@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_DeleteLocation() throws Exception {
	int column=17, LocationRow=2;//for getting Location Number from Excel
	String status="DELETED";
	wait=new WebDriverWait(driver, 30);
	wb = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	basicInfoData = wb.getExcelTable();
	System.out.println("Scenario: Delete Location....");
	
	home.NavigateToLocations();
	String locationNumber=basicInfoData[LocationRow][column];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.DeleteLocation();
	
	System.out.println(locationNumber+" >> Location Deleted.....");
	//update.setCellValue( 2, 0, locationNumber);
	//update.setCellValue( 2, 1, status);
}

//Close Location
@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_CloseLocation() throws Exception {
	int column=17, LocationRow=3;//for getting Location Number from Excel
	String status="CLOSED";
	wait=new WebDriverWait(driver, 30);
	wb = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	basicInfoData = wb.getExcelTable();
	System.out.println("Scenario: Close Location....");
	
	home.NavigateToLocations();
	String locationNumber=basicInfoData[LocationRow][column];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.CloseLocation();
	
	System.out.println(locationNumber+" >> Location Closed.....");
	//update.setCellValue(3, 0, locationNumber);
	//update.setCellValue(3, 1, status);
}

//Deactivate Location	
@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_ReactivateLocation() throws Exception {
	int column=17, LocationRow=1;//for getting Location Number from Excel
	String status="RE-ACTIVATED";
	wait=new WebDriverWait(driver, 30);
	wb = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	products=new Page_LocationManageProductsTab(driver);
	site=new Page_SiteSpecificInfoTab(driver);
	basicInfoData = wb.getExcelTable();
	locationUpdate = update.getExcelTable();
	System.out.println("Scenario: Re-activating Location....");
	
	home.NavigateToLocations();
	locations.SetBusinessStatus("Deactivated");
	String locationNumber=locationUpdate[1][0];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.ReactivateLocation();
	locations.searchLocation(locationNumber);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
	products.clickOnDSOptions("NEW");
	tabs.navigateSiteSpecificInfoTab();
	site.fillSiteSpecificInfoData("ZOMATO");
	tabs.updateLocation();
	Thread.sleep(2000);
	
	System.out.println(locationNumber+" >> Location Activated.....");
	//update.setCellValue( 4, 0, locationNumber);
	//update.setCellValue( 4, 1, status);
}
*/
//Re-Open Location	
	/*
@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_ReOpenLocation() throws Exception {
	int column=17, LocationRow=1;//for getting Location Number from Excel
	String status="RE-OPEN";
	wait=new WebDriverWait(driver, 30);
	wb = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	products=new Page_LocationManageProductsTab(driver);
	site=new Page_SiteSpecificInfoTab(driver);
	basicInfoData = wb.getExcelTable();
	locationUpdate = update.getExcelTable();
	System.out.println("Scenario: Re-Open Location....");
	
	home.NavigateToLocations();
	locations.SetBusinessStatus("Closed");
	String locationNumber=locationUpdate[3][0];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.ReOpenLocation();
	locations.searchLocation(locationNumber);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
	products.clickOnDSOptions("NEW",wb,LocationRow);
	tabs.navigateSiteSpecificInfoTab();
	site.fillSiteSpecificInfoData("ZOMATO", wb, LocationRow);
	tabs.updateLocation();
	System.out.println(locationNumber+" >> Location Opened.....");
	//update.setCellValue( 5, 0, locationNumber);
	//update.setCellValue( 5, 1, status);
}*/
}
