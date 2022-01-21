package com.dac.testcases.LPAD;

import java.io.IOException;

import org.jfree.base.BasicProjectInfo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.POM_LPAD.Page_LPADHome;
import com.dac.main.POM_LPAD.Page_LPADLogin;
import com.dac.main.POM_LPAD.Page_LocationBasicInfoTab;
import com.dac.main.POM_LPAD.Page_LocationBusinessInfoTab;
import com.dac.main.POM_LPAD.Page_LocationDetailsTab;
import com.dac.main.POM_LPAD.Page_LocationManageProductsTab;
import com.dac.main.POM_LPAD.Page_LocationNavigationTabList;
import com.dac.main.POM_LPAD.Page_LocationsListPage;
import com.dac.main.POM_LPAD.Page_SiteSpecificInfoTab;

import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.IAutoconst;


public class Test_CreateMultipleLocations extends BaseClass {
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
	String basicInfoData[][],newLocationNumber="";
	ExcelHandler newlocations,basic;
	
@Test(groups = { "smoke" }, description = "TC: Login to LPAD")
public void TC_Login_LPAD() throws Exception {
	Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
	Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
	wait=new WebDriverWait(CurrentState.getDriver(), 50);
	loginPage.LoginTOLPAD();
//	String pageTitle= loginPage.getTitle();
	System.out.println("wait completes");
	
	home.create_LPAD_Evidence("Login to LPAD", "Login to LPAD Successfully");
		 
//	TakesScreenshot
//	Thread.sleep(2000);
//	CurrentState.getLogger().log(Status.PASS, "Login to LPAD Successfully");
//	addEvidence(CurrentState.getDriver(), "Login to LPAD", "yes");
	
	home.switchToDomain();
	home.create_LPAD_Evidence("Switch Domain", "Switch Domain Successfully");
//	Thread.sleep(2000);
//	CurrentState.getLogger().log(Status.PASS, "Switch Domain");
//	addEvidence(CurrentState.getDriver(), "Switch Domain Successfully", "yes");
}


@Test(dependsOnMethods= {"TC_Login_LPAD"}, description = "TC: Creating Location on LPAD")
public void TC_EnterLocationData() throws Exception {
	int colLocationNumber=21, colLocationName=22, ColLocationStatus=23;
	String status="NEW",locationName="",latitude="",longitude="";
	System.out.println("Step1: Enter Location Data");
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	basic = new ExcelHandler(IAutoconst.LocationDataExcelPath, "BasicInfo");
//	wb.getColHeadingNumber("MainBusinessPhoneNumber");
	basicInfoData = basic.getExcelTable();
	int TotalRow=basicInfoData.length-1;
	int vendorIdColumn=1;//Vendor ID in excel
	home=new Page_LPADHome(CurrentState.getDriver());
	locations=new Page_LocationsListPage(CurrentState.getDriver());
	tabs=new Page_LocationNavigationTabList(CurrentState.getDriver());
	basicInfo=new Page_LocationBasicInfoTab(CurrentState.getDriver());
	businessInfo=new Page_LocationBusinessInfoTab(CurrentState.getDriver());
	details=new Page_LocationDetailsTab(CurrentState.getDriver());
	products=new Page_LocationManageProductsTab(CurrentState.getDriver());
	site=new Page_SiteSpecificInfoTab(CurrentState.getDriver());
	
	
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
//		Thread.sleep(3000);
//		home.create_LPAD_Evidence("Create Location", "Location Created");
		newLocationNumber=basicInfo.getLocationNumber();
//		CurrentState.getLogger().log(Status.PASS, "Create new Location");
//		addEvidence(CurrentState.getDriver(), "Location Created", "yes");
		locationName=basicInfo.getSBName();
		latitude=basicInfo.getLatitude();
		longitude=basicInfo.getLongitude();
//		System.out.println(locationName);
		Thread.sleep(5000);
		tabs.navigateSiteSpecificInfoTab();
		site.fillSiteSpecificInfoData("zomato",basic,i);
		site.fillSiteSpecificInfoData("APPLE",basic,i);
//		home.create_LPAD_Evidence("Attributes", "Attributes Added");
//		CurrentState.getLogger().log(Status.PASS, "Assign Attributes");
//		addEvidence(CurrentState.getDriver(), "Attributes added", "yes");
//		tabs.updateLocation();
//		Vendor_IDs = data.getCellValue(row, data.seacrh_pattern("Vendro_ID_Update", 0).get(0).intValue());
		basic.setCellValue( i, basic.seacrh_pattern("LocationNumber", 0).get(0).intValue(), newLocationNumber);
		basic.setCellValue( i, basic.seacrh_pattern("LocationName", 0).get(0).intValue(), locationName);
		basic.setCellValue(i, basic.seacrh_pattern("Status", 0).get(0).intValue(), status);
		basic.setCellValue(i, basic.seacrh_pattern("Latitude", 0).get(0).intValue(), latitude);
		basic.setCellValue(i, basic.seacrh_pattern("Longitude", 0).get(0).intValue(), longitude);
		
	}
	
	
	System.out.println("Location Data Insertion Completed.....");
}



}
