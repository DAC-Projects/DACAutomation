package com.dac.testcases.DTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_DTC.DTC_Apple_Tranmission;
import com.dac.main.POM_DTC.DTC_Pinit;
import com.dac.main.POM_DTC.DTC_Transmission;
import com.dac.main.POM_LPAD.Page_LPADHome;
import com.dac.main.POM_LPAD.Page_LPADLogin;
import com.dac.main.POM_LPAD.Page_LocationBasicInfoTab;
import com.dac.main.POM_LPAD.Page_LocationBusinessInfoTab;
import com.dac.main.POM_LPAD.Page_LocationDetailsTab;
import com.dac.main.POM_LPAD.Page_LocationManageProductsTab;
import com.dac.main.POM_LPAD.Page_LocationNavigationTabList;
import com.dac.main.POM_LPAD.Page_LocationsListPage;
import com.dac.main.POM_LPAD.Page_SiteSpecificInfoTab;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.BaseClass;
import resources.CurrentState;
import resources.ExcelHandler;
import resources.IAutoconst;

public class Apple_Transmission extends BaseClass {
	
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
	ExcelHandler basic;
	ExcelHandler wb, newlocations, update;
	String locationUpdate[][];
@Test()
public void TC_Login_LPAD() throws Exception {
//		GetColmunNumber number =new GetColmunNumber();
			Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
			Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
			WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
		    loginPage.LoginTOLPAD(); 
//		  String pageTitle= loginPage.getTitle();
		 System.out.println("wait completes");
		 home.switchToDomain();
			addEvidence(CurrentState.getDriver(), "Login", "yes");

//		 TakesScreenshot
}


@Test(dependsOnMethods= {"TC_Login_LPAD"})
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
		Thread.sleep(2000);
		addEvidence(CurrentState.getDriver(), "New location", "yes");
		newLocationNumber=basicInfo.getLocationNumber();
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
		basic.setCellValue( i, basic.seacrh_pattern("LocationNumber", 0).get(0).intValue(), newLocationNumber);
		basic.setCellValue( i, basic.seacrh_pattern("LocationName", 0).get(0).intValue(), locationName);
		basic.setCellValue(i, basic.seacrh_pattern("Status", 0).get(0).intValue(), status);
		basic.setCellValue(i, basic.seacrh_pattern("Latitude", 0).get(0).intValue(), latitude);
		basic.setCellValue(i, basic.seacrh_pattern("Longitude", 0).get(0).intValue(), longitude);
		addEvidence(CurrentState.getDriver(), "save", "yes");

	}
	System.out.println("Location Data Insertion Completed.....");
	 //Thread.sleep(240000);

}
/*@Test(dependsOnMethods= {"TC_EnterLocationData"})
public void launchBrowser1() {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    dtcLogin.pressYesKey();
		addEvidence(CurrentState.getDriver(), "DTC", "yes");
}

@Test( dependsOnMethods = { "launchBrowser1"},groups = { "Apple_Addrequest" })
public void Addrequest1() throws Exception {
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 Apple.apple_Trans("Abi DTC Test Account 1");
		addEvidence(CurrentState.getDriver(), "Add request", "yes");

	// Thread.sleep(240000);

 }
@Test(dependsOnMethods= {"Addrequest1"})
public void launchBrowser2() throws Exception {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	  //dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    //dtcLogin.pressYesKey();
		addEvidence(CurrentState.getDriver(), "save", "yes");

		Thread.sleep(5000);


	    }

@Test( dependsOnMethods = { "launchBrowser2"},groups = { "VerifyAppleTransmission" })
public void Verify_Tranmsission1() throws Exception {
	 Thread.sleep(30000);
	 String LO_number=null,apple_RqID=null;
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
	Thread.sleep(30000);

		ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
        LO_number = wb1.getCellValue(1, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
        //Apple.verify_apple(LO_number, apple_RqID);
       //Apple.verify_apple2(LO_number,apple_RqID);
       //Apple.verify_apple2("9000241065",apple_RqID);
		addEvidence(CurrentState.getDriver(), "Apple transmission", "yes");



 }	
@Test( dependsOnMethods = { "Verify_Tranmsission1"},groups = { "VerifyAppleTransmission" })

public void TC_Login_LPAD1() throws Exception {
//	GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
	  loginPage.LoginTOLPAD(); 
//	  String pageTitle= loginPage.getTitle();
	 System.out.println("wait completes");
	 home.switchToDomain();
		addEvidence(CurrentState.getDriver(), "Login LPAD", "yes");

	 
//	 TakesScreenshot
}

@Test(dependsOnMethods= {"TC_Login_LPAD1"})
public void TC_ManageLocation_DisableLPM() throws Exception {
    int column=17, LocationRow=1;//for getting Location Number from Excel
    String status="LPM OFF";
    System.out.println("Scenario2: Disable LPM Product....");
    wait=new WebDriverWait(CurrentState.getDriver(), 30);
    ExcelHandler wb = new ExcelHandler(IAutoconst.LocationDataExcelPath, "BasicInfo");
    basicInfoData = wb.getExcelTable();
    int TotalRow=basicInfoData.length-1;
    home=new Page_LPADHome(CurrentState.getDriver());
    locations=new Page_LocationsListPage(CurrentState.getDriver());
    tabs=new Page_LocationNavigationTabList(CurrentState.getDriver());
    basicInfo=new Page_LocationBasicInfoTab(CurrentState.getDriver());
    businessInfo=new Page_LocationBusinessInfoTab(CurrentState.getDriver());
    products=new Page_LocationManageProductsTab(CurrentState.getDriver());
	addEvidence(CurrentState.getDriver(), "Turn off", "yes");

    home.NavigateToLocations();
//    String locationNumber=basicInfoData[2][column];
    String locationNumber      = wb.getCellValue(LocationRow, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
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
        }
   
    System.out.println(locationNumber+" >> PFO Disabled....");
//    update.setCellValue( 2, 0, locationNumber);
//    update.setCellValue( 2, 1, status);
}

@Test(dependsOnMethods= {"TC_ManageLocation_DisableLPM"})
public void launchBrowser31() {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    dtcLogin.pressYesKey();
		addEvidence(CurrentState.getDriver(), "Login", "yes");
}

@Test( dependsOnMethods = { "launchBrowser31"},groups = { "Apple_Addrequest" })
public void Addrequest2() throws Exception {
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 Apple.apple_Trans("Abi DTC Test Account 1");
	// Thread.sleep(240000);
		addEvidence(CurrentState.getDriver(), "save", "yes");


 }
@Test(dependsOnMethods= {"Addrequest2"})
public void launchBrowser3() throws Exception {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	  //dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    //dtcLogin.pressYesKey();
		addEvidence(CurrentState.getDriver(), "save", "yes");

		Thread.sleep(5000);


	    }

@Test( dependsOnMethods = { "launchBrowser3"},groups = { "VerifyAppleTransmission" })
public void Verify_Tranmsission11() throws Exception {
	 Thread.sleep(30000);
	 String LO_number=null,apple_RqID=null;
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
	Thread.sleep(30000);

		ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
        LO_number = wb1.getCellValue(1, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
        //Apple.verify_apple(LO_number, apple_RqID);
       //Apple.verify_apple2(LO_number,apple_RqID);
       //Apple.verify_apple2("9000241065",apple_RqID);
		addEvidence(CurrentState.getDriver(), "save", "yes");



 }	
@Test( dependsOnMethods = { "Verify_Tranmsission11"},groups = { "VerifyAppleTransmission" })
public void TC_Login_LPAD13() throws Exception {
//	GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
	    loginPage.LoginTOLPAD(); 
//	    String pageTitle= loginPage.getTitle();
	    System.out.println("wait completes");
	    home.switchToDomain();
		addEvidence(CurrentState.getDriver(), "save", "yes");

	 
//	 TakesScreenshot
}

@Test(dependsOnMethods= {"Verify_Tranmsission11"})
public void TC_ManageLocation_EnableLPM() throws Exception {
	int column=17,LocationRow=1;//for getting Location Number from Excel
	System.out.println("Scenario3: Enable LPM Product....");
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	wb = new ExcelHandler(IAutoconst.LocationDataExcelPath, "BasicInfo");
	basicInfoData = wb.getExcelTable();
	int TotalRow=basicInfoData.length-1;
	home=new Page_LPADHome(CurrentState.getDriver());
	locations=new Page_LocationsListPage(CurrentState.getDriver());
	tabs=new Page_LocationNavigationTabList(CurrentState.getDriver());
	basicInfo=new Page_LocationBasicInfoTab(CurrentState.getDriver());
	businessInfo=new Page_LocationBusinessInfoTab(CurrentState.getDriver());
	products=new Page_LocationManageProductsTab(CurrentState.getDriver());
	home.NavigateToLocations();
//	String locationNumber=basicInfoData[2][column];
	String locationNumber  	= wb.getCellValue(LocationRow, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(5000);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
	products.clickOnDSOptions("NEW",wb,LocationRow);
	tabs.navigateSiteSpecificInfoTab();
	//site.fillSiteSpecificInfoData("ZOMATO",wb,LocationRow);
	tabs.updateLocation();
	Thread.sleep(2000);
////		boolean change=products.enableLPM();	
//		Thread.sleep(2000);
//		if(change) {
//			System.out.println("Change " + change);
//		tabs.updateLocation();
////		wb.setCellValue( 2, 18, "LPM Turned ON");
//		}
	addEvidence(CurrentState.getDriver(), "TC_ManageLocation_EnableLPM", "yes");

	System.out.println("PFO: LPM Enabled.....");
}
@Test(dependsOnMethods= {"TC_ManageLocation_EnableLPM"})
public void launchBrowser311() {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    dtcLogin.pressYesKey();}

@Test( dependsOnMethods = { "launchBrowser311"})
public void Addrequest12() throws Exception {
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 Apple.apple_Trans("Abi DTC Test Account 1");
	// Thread.sleep(240000);
		addEvidence(CurrentState.getDriver(), "Add request", "yes");


 }

@Test( dependsOnMethods = { "Addrequest12"},groups = { "VerifyAppleTransmission" })
public void Verify_Tranmsission12() throws Exception {
	 Thread.sleep(30000);
	 String LO_number=null,apple_RqID=null;
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
	Thread.sleep(30000);

		ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
        LO_number = wb1.getCellValue(1, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
        //Apple.verify_apple(LO_number, apple_RqID);
       //Apple.verify_apple2(LO_number,apple_RqID);
       //Apple.verify_apple2("9000241065",apple_RqID);
    	addEvidence(CurrentState.getDriver(), "Apple transmission", "yes");



 }	

@Test( dependsOnMethods = { "Verify_Tranmsission11"},groups = { "VerifyAppleTransmission" })
public void TC_Login_LPAD3() throws Exception {
//	GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
	    loginPage.LoginTOLPAD(); 
//	    String pageTitle= loginPage.getTitle();
	    System.out.println("wait completes");
	    home.switchToDomain();
		addEvidence(CurrentState.getDriver(), "save", "yes");

	 
//	 TakesScreenshot
}


@Test(dependsOnMethods= {"TC_Login_LPAD3"})
public void TC_DeactivateLocation() throws Exception {
	int column=17, LocationRow=1;//for getting Location Number from Excel
	String status="DEACTIVATED";
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	ExcelHandler wb = new ExcelHandler(IAutoconst.LocationDataExcelPath, "BasicInfo");
//	update = new ExcelHandler(LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(CurrentState.getDriver());
	locations=new Page_LocationsListPage(CurrentState.getDriver());
	tabs=new Page_LocationNavigationTabList(CurrentState.getDriver());
	basicInfoData = wb.getExcelTable();
	
	System.out.println("Scenario: Deactivating Location....");
	
	//Starting....
	home.NavigateToLocations();
//	String locationNumber=basicInfoData[LocationRow][column];
	String locationNumber  	= wb.getCellValue(LocationRow, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.DeactivateLocation();
	
	System.out.println(locationNumber+" >> Location Deactivated.....");
//	update.setCellValue( 1, 0, locationNumber);
//	update.setCellValue( 1, 1, status);
	addEvidence(CurrentState.getDriver(), "Deactivate", "yes");

}

@Test(dependsOnMethods= {"TC_DeactivateLocation"})
public void launchBrowser3111() {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    dtcLogin.pressYesKey();
		addEvidence(CurrentState.getDriver(), "DTC", "yes");
}

@Test( dependsOnMethods = { "launchBrowser3111"})
public void Addrequest13() throws Exception {
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 Apple.apple_Trans("Abi DTC Test Account 1");
	// Thread.sleep(240000);
		addEvidence(CurrentState.getDriver(), "DTC Request", "yes");

 }

@Test( dependsOnMethods = { "Addrequest13"},groups = { "VerifyAppleTransmission" })
public void Verify_Tranmsission14() throws Exception {
	 Thread.sleep(30000);
	 String LO_number=null,apple_RqID=null;
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
	Thread.sleep(30000);

		ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
        LO_number = wb1.getCellValue(1, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
        //Apple.verify_apple(LO_number, apple_RqID);
       //Apple.verify_apple2(LO_number,apple_RqID);
       //Apple.verify_apple2("9000241065",apple_RqID);
		addEvidence(CurrentState.getDriver(), "Verify transmission", "yes");



 }	

@Test( dependsOnMethods = { "Verify_Tranmsission14"},groups = { "VerifyAppleTransmission" })
public void TC_Login_LPAD31() throws Exception {
//	GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
	    loginPage.LoginTOLPAD(); 
//	    String pageTitle= loginPage.getTitle();
	    System.out.println("wait completes");
	    home.switchToDomain();
		addEvidence(CurrentState.getDriver(), "LPAD", "yes");

	 
//	 TakesScreenshot
}


@Test(dependsOnMethods= {"TC_Login_LPAD31"})

public void TC_ReactivateLocation() throws Exception {
	int column=17, LocationRow=1;//for getting Location Number from Excel
	String status="RE-ACTIVATED";
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	ExcelHandler wb = new ExcelHandler(IAutoconst.LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(IAutoconst.LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(CurrentState.getDriver());
	locations=new Page_LocationsListPage(CurrentState.getDriver());
	tabs=new Page_LocationNavigationTabList(CurrentState.getDriver());
	products=new Page_LocationManageProductsTab(CurrentState.getDriver());
	site=new Page_SiteSpecificInfoTab(CurrentState.getDriver());
	basicInfoData = wb.getExcelTable();
	  locationUpdate = update.getExcelTable();
	System.out.println("Scenario: Re-activating Location....");
	
	home.NavigateToLocations();
	locations.SetBusinessStatus("Deactivated");
	String locationNumber  	= wb.getCellValue(1, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
//	String locationNumber=locationUpdate[1][0];
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.ReactivateLocation();
	locations.searchLocation(locationNumber);
	locations.NavigateManageLocation();
	tabs.navigateProductsTab();
	products.clickOnDSOptions("NEW",wb,LocationRow);
	tabs.navigateSiteSpecificInfoTab();
	site.fillSiteSpecificInfoData("ZOMATO",wb,LocationRow);
	tabs.updateLocation();
	Thread.sleep(2000);
	
	System.out.println(locationNumber+" >> Location Activated.....");
//	update.setCellValue( 4, 0, locationNumber);
//	update.setCellValue( 4, 1, status);
	addEvidence(CurrentState.getDriver(), "ReactivateLocation", "yes");

}


@Test(dependsOnMethods= {"TC_ReactivateLocation"})
public void launchBrowser41() {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    dtcLogin.pressYesKey();
		addEvidence(CurrentState.getDriver(), "DTC", "yes");
}

@Test( dependsOnMethods = { "launchBrowser41"})
public void Addrequest14() throws Exception {
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 Apple.apple_Trans("Abi DTC Test Account 1");
		addEvidence(CurrentState.getDriver(), "Add request", "yes");

	// Thread.sleep(240000);

 }

@Test( dependsOnMethods = { "Addrequest14"},groups = { "VerifyAppleTransmission" })
public void Verify_Tranmsission15() throws Exception {
	 Thread.sleep(30000);
	 String LO_number=null,apple_RqID=null;
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
	Thread.sleep(30000);

		ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
        LO_number = wb1.getCellValue(1, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
        //Apple.verify_apple(LO_number, apple_RqID);
       //Apple.verify_apple2(LO_number,apple_RqID);
       //Apple.verify_apple2("9000241065",apple_RqID);
		addEvidence(CurrentState.getDriver(), "Transmission", "yes");



 }	

@Test( dependsOnMethods = { "Verify_Tranmsission14"},groups = { "VerifyAppleTransmission" })
public void TC_Login_LPAD51() throws Exception {
//	GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
	    loginPage.LoginTOLPAD(); 
//	    String pageTitle= loginPage.getTitle();
	    System.out.println("wait completes");
	    home.switchToDomain();
		addEvidence(CurrentState.getDriver(), "save", "yes");
		addEvidence(CurrentState.getDriver(), "LPAD", "yes");

	 
//	 TakesScreenshot
}
//Close Location
@Test(dependsOnMethods= {"TC_Login_LPAD51"})
public void TC_CloseLocation() throws Exception {
	int column=17, LocationRow=1;//for getting Location Number from Excel
	String status="CLOSED";
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	wb = new ExcelHandler(IAutoconst.LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(IAutoconst.LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(CurrentState.getDriver());
	locations=new Page_LocationsListPage(CurrentState.getDriver());
	tabs=new Page_LocationNavigationTabList(CurrentState.getDriver());
	basicInfoData = wb.getExcelTable();
	System.out.println("Scenario: Close Location....");
	
	home.NavigateToLocations();
//	String locationNumber=basicInfoData[LocationRow][column];
	String locationNumber  	= wb.getCellValue(LocationRow, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
	System.out.println("Location Number"+ locationNumber);
	locations.searchLocation(locationNumber);
	Thread.sleep(2000);
	locations.CloseLocation();
	
	System.out.println(locationNumber+" >> Location Closed.....");
//	update.setCellValue(3, 0, locationNumber);
//	update.setCellValue(3, 1, status);
	addEvidence(CurrentState.getDriver(), "Close location", "yes");

}
	
@Test(dependsOnMethods= {"TC_CloseLocation"})
public void launchBrowser51() {
	String url="https://beta-dtc-web.azurewebsites.net/";
	  CurrentState.getDriver().get(url);
	  Dimension d = new Dimension(1000,700);
	   System.out.println(CurrentState.getDriver().getTitle());
	   DTC_Transmission dtcLogin=new DTC_Transmission(CurrentState.getDriver());
	   dtcLogin.submitLogin("adevaraj@dacgroup.com","laptop@123");
	    dtcLogin.pressYesKey();
		addEvidence(CurrentState.getDriver(), "DTC", "yes");
}

@Test( dependsOnMethods = { "launchBrowser51"})
public void Addrequest15() throws Exception {
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 Apple.apple_Trans("Abi DTC Test Account 1");
	// Thread.sleep(240000);
		addEvidence(CurrentState.getDriver(), "Add request", "yes");


 }

@Test( dependsOnMethods = { "Addrequest15"},groups = { "VerifyAppleTransmission" })
public void Verify_Tranmsission16() throws Exception {
	 Thread.sleep(30000);
	 String LO_number=null,apple_RqID=null;
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
	Thread.sleep(30000);

		ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
        LO_number = wb1.getCellValue(1, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
        //Apple.verify_apple(LO_number, apple_RqID);
       //Apple.verify_apple2(LO_number,apple_RqID);
       //Apple.verify_apple2("9000241065",apple_RqID);

		addEvidence(CurrentState.getDriver(), "Transmission", "yes");

 }	

@Test( dependsOnMethods = { "Verify_Tranmsission16"},groups = { "VerifyAppleTransmission" })
public void TC_Login_LPAD61() throws Exception {
//	GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
	    loginPage.LoginTOLPAD(); 
//	    String pageTitle= loginPage.getTitle();
	    System.out.println("wait completes");
	    home.switchToDomain();
		addEvidence(CurrentState.getDriver(), "save", "yes");

		addEvidence(CurrentState.getDriver(), "LPAD", "yes");

//	 TakesScreenshot
}
//Re-Open Location	
@Test(dependsOnMethods= {"TC_Login_LPAD61"})
public void TC_ReOpenLocation() throws Exception {
	int column=17, LocationRow=1;//for getting Location Number from Excel
	String status="RE-OPEN";
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	wb = new ExcelHandler(IAutoconst.LocationDataExcelPath, "BasicInfo");
	update = new ExcelHandler(IAutoconst.LocationDataExcelPath, "LocationUpdates");
	home=new Page_LPADHome(CurrentState.getDriver());
	locations=new Page_LocationsListPage(CurrentState.getDriver());
	tabs=new Page_LocationNavigationTabList(CurrentState.getDriver());
	products=new Page_LocationManageProductsTab(CurrentState.getDriver());
	site=new Page_SiteSpecificInfoTab(CurrentState.getDriver());
	basicInfoData = wb.getExcelTable();
	locationUpdate = update.getExcelTable();
	System.out.println("Scenario: Re-Open Location....");
	
	home.NavigateToLocations();
	locations.SetBusinessStatus("Closed");
//	String locationNumber=locationUpdate[3][0];
	String locationNumber  	= wb.getCellValue(LocationRow, wb.seacrh_pattern("LocationNumber", 0).get(0).intValue());
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
//	update.setCellValue( 5, 0, locationNumber);
//	update.setCellValue( 5, 1, status);
	addEvidence(CurrentState.getDriver(), "ReOpenLocation", "yes");

}
@Test( dependsOnMethods = { "TC_ReOpenLocation"})
public void Addrequest16() throws Exception {
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 Apple.apple_Trans("Abi DTC Test Account 1");
	// Thread.sleep(240000);
		addEvidence(CurrentState.getDriver(), "Add request", "yes");
 }

@Test( dependsOnMethods = { "Addrequest16"},groups = { "VerifyAppleTransmission" })
public void Verify_Tranmsission17() throws Exception {
	 Thread.sleep(30000);
	 String LO_number=null,apple_RqID=null;
	 DTC_Apple_Tranmission Apple=new DTC_Apple_Tranmission(CurrentState.getDriver());
	 ExcelHandler wb1 = new ExcelHandler("./data/LocationSampleData.xlsx", "BasicInfo");
	  Thread.sleep(30000);
		ExcelHandler wb = new ExcelHandler("./data/Request_ID.xlsx", "Sheet1"); wb.deleteEmptyRows();
        apple_RqID = wb.getCellValue(1, wb.seacrh_pattern("Apple_Request_ID", 0).get(0).intValue());
        LO_number = wb1.getCellValue(1, wb1.seacrh_pattern("LocationNumber", 0).get(0).intValue());
        //Apple.verify_apple(LO_number, apple_RqID);
       //Apple.verify_apple2(LO_number,apple_RqID);
       //Apple.verify_apple2("9000241065",apple_RqID);
		addEvidence(CurrentState.getDriver(), "Transmission", "yes");
 }	

@Test( dependsOnMethods = { "Verify_Tranmsission17"},groups = { "VerifyAppleTransmission" })
public void TC_Login_LPAD71() throws Exception {
//	GetColmunNumber number =new GetColmunNumber();
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50); 
	    loginPage.LoginTOLPAD(); 
//	    String pageTitle= loginPage.getTitle();
	    System.out.println("wait completes");
	    home.switchToDomain();
		addEvidence(CurrentState.getDriver(), "save", "yes");

		addEvidence(CurrentState.getDriver(), "LPAD", "yes");

//	 TakesScreenshot
}
*/
}
	