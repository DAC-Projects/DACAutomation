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


public class Test_CreateLocation extends LaunchLPAD {
	Page_LocationBasicInfoTab basicInfo;
	Page_LocationBusinessInfoTab businessInfo;
	Page_LocationNavigationTabList tabs;
	Page_LocationManageProductsTab products;
	WebDriverWait wait;  
	public JavascriptExecutor js;
	
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
	
@Test(dependsOnMethods = "com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD")
public void TC_NavigateToLocationsPage() {
	System.out.println("Step1: Navigate To Locations Page ");
	Page_LPADHome home=new Page_LPADHome(driver);
	home.NavigateToLocations();
		  
}

@Test(dependsOnMethods= {"TC_NavigateToLocationsPage"})
public void TC_CreateLocations() {
	System.out.println("Step2: Click on [Create Location] Button");
	Page_LocationsListPage locations=new Page_LocationsListPage(driver);
	locations.NaviagteToCreateLocation();
}

@Test(dependsOnMethods= {"TC_CreateLocations"})
public void TC_EnterBasicInfoData() throws Exception {
	System.out.println("Step3: Enter Basic Info Tab Data");
	basicInfo=new Page_LocationBasicInfoTab(driver);
	js = (JavascriptExecutor) driver;
	wait=new WebDriverWait(driver, 30);
	basicInfo.fillBasicInfoData(1);

	System.out.println("Basic Info Data Insertion Completed.....");
}
@Test(dependsOnMethods= {"TC_EnterBasicInfoData"})
public void TC_EnterBusinessInfoData() throws Exception {
	System.out.println("Step4: Enter Business Info Tab Data");
	businessInfo=new Page_LocationBusinessInfoTab(driver);
	basicInfo=new Page_LocationBasicInfoTab(driver);
	tabs=new Page_LocationNavigationTabList(driver);
	wait=new WebDriverWait(driver, 30);
	
	tabs.navigateBusinessInfoTab();
	Thread.sleep(1000);
	businessInfo.fillBusinessInfoData(1);
	
	System.out.println("Business Info Data Insertion Completed.....");
}

@Test(dependsOnMethods= {"TC_EnterBasicInfoData"})
public void TC_EnterPFOData() throws Exception {
	System.out.println("Step4: Enter PFO Data");
	products=new Page_LocationManageProductsTab(driver);
	wait=new WebDriverWait(driver, 30);
	tabs.navigateProductsTab();
//	Thread.sleep(2000);
	products.clickOnDSOptions();
	System.out.println("PFO Data Insertion Completed.....");
}
@Test(dependsOnMethods= {"TC_EnterBusinessInfoData"})
public void TC_SubmitLocation() throws Exception {

	tabs=new Page_LocationNavigationTabList(driver);
	basicInfo=new Page_LocationBasicInfoTab(driver);
	tabs.submitLocation();
//	tabs.navigateBasicInfoTab();
	locationNumber=basicInfo.getLocationNumber();
	System.out.println("Location Number is: "+locationNumber);
	System.out.println("Location Data Submission completed.....");
	
}

}
