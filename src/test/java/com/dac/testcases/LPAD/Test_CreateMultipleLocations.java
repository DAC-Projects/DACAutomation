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


public class Test_CreateMultipleLocations extends LaunchLPAD {
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
	


@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_EnterLocationData() throws Exception {
	int colLocationNumber=17, colLocationName=18, ColLocationStatus=19;
	String status="NEW",locationName="";
	System.out.println("Step1: Enter Location Data");
	wait=new WebDriverWait(driver, 30);
	wb = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
	
	basicInfoData = wb.getExcelTable();
	int TotalRow=basicInfoData.length-1;
	int vendorIdColumn=1;//Vendor ID in excel
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	basicInfo=new Page_LocationBasicInfoTab(driver);
	businessInfo=new Page_LocationBusinessInfoTab(driver);
	products=new Page_LocationManageProductsTab(driver);
	site=new Page_SiteSpecificInfoTab(driver);
	
	
	for (int i=1;i<=TotalRow;i++) {
		
		home.NavigateToLocations();
		locations.NaviagteToCreateLocation();
		Thread.sleep(1000);
		basicInfo.fillBasicInfoData(i);
		tabs.navigateBusinessInfoTab();
		businessInfo.fillBusinessInfoData();
		tabs.navigateProductsTab();
		products.clickOnDSOptions("NEW");
		tabs.submitLocation();
		Thread.sleep(2000);
		NewlocationNumber=basicInfo.getLocationNumber();
		locationName=basicInfo.getSBName();
//		System.out.println(locationName);
		tabs.navigateSiteSpecificInfoTab();
		site.fillSiteSpecificInfoData("ZOMATO");

		//		tabs.updateLocation();
		wb.setCellValue( i, colLocationNumber, NewlocationNumber);
		wb.setCellValue( i, colLocationName, locationName);
		wb.setCellValue(i, ColLocationStatus, status);
		
	}
	
	
	System.out.println("Location Data Insertion Completed.....");
}



}
