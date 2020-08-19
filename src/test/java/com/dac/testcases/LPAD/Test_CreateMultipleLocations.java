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
import com.dac.main.POM_LPAD.Page_LocationDetailsTab;
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
	Page_LocationDetailsTab details;
	WebDriverWait wait;  
	JavascriptExecutor js;
	String basicInfoData[][];
	ExcelHandler newlocations,basic;
	


@Test(dependsOnMethods= {"com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD"})
public void TC_EnterLocationData() throws Exception {
	int colLocationNumber=21, colLocationName=22, ColLocationStatus=23;
	String status="NEW",locationName="",latitude="",longitude="";
	System.out.println("Step1: Enter Location Data");
	wait=new WebDriverWait(driver, 30);
	basic = new ExcelHandler(LocationDataExcelPath, "BasicInfo");
//	wb.getColHeadingNumber("MainBusinessPhoneNumber");
	basicInfoData = basic.getExcelTable();
	int TotalRow=basicInfoData.length-1;
	int vendorIdColumn=1;//Vendor ID in excel
	home=new Page_LPADHome(driver);
	locations=new Page_LocationsListPage(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	basicInfo=new Page_LocationBasicInfoTab(driver);
	businessInfo=new Page_LocationBusinessInfoTab(driver);
	details=new Page_LocationDetailsTab(driver);
	products=new Page_LocationManageProductsTab(driver);
	site=new Page_SiteSpecificInfoTab(driver);
	
	
	for (int i=1;i<=TotalRow;i++) {
		
		home.NavigateToLocations();
		locations.NaviagteToCreateLocation();
		Thread.sleep(1000);
		basicInfo.fillBasicInfoData(i);
		tabs.navigateBusinessInfoTab();
		businessInfo.fillBusinessInfoData();
		tabs.navigateDetailsTab();
		details.fillDetailsTabData();
		tabs.navigateProductsTab();
		products.clickOnDSOptions("NEW",basic,i);
		Thread.sleep(3000);
		tabs.submitLocation();
		Thread.sleep(2000);
		NewlocationNumber=basicInfo.getLocationNumber();
		locationName=basicInfo.getSBName();
		latitude=basicInfo.getLatitude();
		longitude=basicInfo.getLongitude();
//		System.out.println(locationName);
		Thread.sleep(5000);
		tabs.navigateSiteSpecificInfoTab();
		site.fillSiteSpecificInfoData("zomato",basic,i);
		site.fillSiteSpecificInfoData("APPLE",basic,i);

		//		tabs.updateLocation();
//		Vendor_IDs = data.getCellValue(row, data.seacrh_pattern("Vendro_ID_Update", 0).get(0).intValue());
		basic.setCellValue( i, basic.seacrh_pattern("LocationNumber", 0).get(0).intValue(), NewlocationNumber);
		basic.setCellValue( i, basic.seacrh_pattern("LocationName", 0).get(0).intValue(), locationName);
		basic.setCellValue(i, basic.seacrh_pattern("Status", 0).get(0).intValue(), status);
		basic.setCellValue(i, basic.seacrh_pattern("Latitude", 0).get(0).intValue(), latitude);
		basic.setCellValue(i, basic.seacrh_pattern("Longitude", 0).get(0).intValue(), longitude);
		
	}
	
	
	System.out.println("Location Data Insertion Completed.....");
}



}
