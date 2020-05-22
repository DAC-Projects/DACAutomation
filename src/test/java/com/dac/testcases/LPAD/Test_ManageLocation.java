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
	ExcelHandler wb, newlocations;
	


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
	String locationNumber=basicInfoData[1][column];
	System.out.println("Location Number"+ locationNumber);
	
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
		products.clickOnDSOptions("Manage");
		Thread.sleep(2000);
		tabs.navigateSiteSpecificInfoTab();
		site.fillSiteSpecificInfoData();
		tabs.updateLocation();
		wb.setCellValue( 1, 18, "PFOUpdate");
	
	System.out.println("LPM Options Updated.....");
}
/*
@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_ManageLocation_DisableLPM() throws Exception {
	int column=17;//for getting Location Number from Excel
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
	locations.searchLocation(locationNumber);
	Thread.sleep(5000);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
		boolean change=products.disableLPM();	
		Thread.sleep(2000);
		if(change) {
			System.out.println("Change " + change);
		tabs.updateLocation();
		wb.setCellValue( 2, 18, "LPM Turned OFF");
		}
	
	System.out.println("PFO: LPM Disabled.....");
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
}*/

}
