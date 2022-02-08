package com.dac.testcases.LPAD;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.POM_LPAD.Page_AccountsList;
import com.dac.main.POM_LPAD.Page_LPADHome;
import com.dac.main.POM_LPAD.Page_LPADLogin;
import com.dac.main.POM_LPAD.Page_LocationBasicInfoTab;
import com.dac.main.POM_LPAD.Page_LocationBusinessInfoTab;
import com.dac.main.POM_LPAD.Page_LocationManageProductsTab;
import com.dac.main.POM_LPAD.Page_LocationNavigationTabList;
import com.dac.main.POM_LPAD.Page_PresetLocationValues;

import resources.BaseClass;
import resources.CurrentState;
import resources.IAutoconst;


public class Test_CreatePresetLocationValue extends BaseClass {
	Page_LocationBasicInfoTab basicInfo;
	Page_LocationBusinessInfoTab businessInfo;
	Page_LocationNavigationTabList tabs;
	Page_LocationManageProductsTab products;
	Page_AccountsList accountsList;
	Page_PresetLocationValues preset;
	WebDriverWait wait;  
	public JavascriptExecutor js;
	String AccountName;
	
@Test(groups = { "smoke" }, description = "TC: Login to LPAD")
	public void TC_Login_LPAD() throws Exception {
		Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
		Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
		wait=new WebDriverWait(CurrentState.getDriver(), 50);
		loginPage.LoginTOLPAD();
//		String pageTitle= loginPage.getTitle();
		System.out.println("wait completes");
		
//		TakesScreenshot
//		Thread.sleep(2000);
//		CurrentState.getLogger().log(Status.PASS, "Login to LPAD Successfully");
//		addEvidence(CurrentState.getDriver(), "Login to LPAD", "yes");
		
		home.switchToDomain();
//		Thread.sleep(2000);
//		CurrentState.getLogger().log(Status.PASS, "Switch Domain");
		addEvidence(CurrentState.getDriver(), "Switch Domain Successfully", "yes");
	}
	
@Test(dependsOnMethods = {"TC_Login_LPAD"}, description = " TC: TC_Navigate To AccountsList Page")
public void TC_NavigateToAccountsListPage() throws InterruptedException {
	System.out.println("Step1: Navigate To Accounts List Page ");
	Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
	home.NavigateToAccounts();
	
}

@Test(dependsOnMethods = {"TC_NavigateToAccountsListPage"},description = "TC: Search Account From the Accounts List")
public void TC_SearchAccount() throws InterruptedException {
	System.out.println("Step2: Search Account From the Accounts List");
	AccountName=IAutoconst.LpadAccountName;
	Page_AccountsList accountsList=new Page_AccountsList(CurrentState.getDriver());
	accountsList.searchAccount(AccountName);
	
}

@Test(dependsOnMethods= {"TC_SearchAccount"},description="Navigate to Preset Location Page")
public void TC_NavigateToPresetLocation() {
	System.out.println("Step3: Navigate to Preset Location Values Page");
	Page_AccountsList accounts=new Page_AccountsList(CurrentState.getDriver());
	accounts.navigateToPresetLocationValues();
}

@Test(dependsOnMethods= {"TC_NavigateToPresetLocation"},description="Fill Preset Values")
public void TC_enterPresetData() throws Exception {
	System.out.println("Step4: Fill Preset Location Values Page");
	Page_PresetLocationValues preset=new Page_PresetLocationValues(CurrentState.getDriver());
	preset.setPresettDataBasicInfo();
}
/*
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
	businessInfo.fillBusinessInfoData();
	
	System.out.println("Business Info Data Insertion Completed.....");
}

@Test(dependsOnMethods= {"TC_EnterBasicInfoData"})
public void TC_EnterPFOData() throws Exception {
	System.out.println("Step4: Enter PFO Data");
	products=new Page_LocationManageProductsTab(driver);
	wait=new WebDriverWait(driver, 30);
	tabs.navigateProductsTab();
//	Thread.sleep(2000);
//	products.clickOnDSOptions("NEW");
	System.out.println("PFO Data Insertion Completed.....");
}
@Test(dependsOnMethods= {"TC_EnterBusinessInfoData"})
public void TC_SubmitLocation() throws Exception {

	tabs=new Page_LocationNavigationTabList(driver);
	basicInfo=new Page_LocationBasicInfoTab(driver);
	tabs.submitLocation();
	tabs.navigateBasicInfoTab();
	NewlocationNumber=basicInfo.getLocationNumber();
	System.out.println("Location Number is: "+NewlocationNumber);
	System.out.println("Location Data Submission completed.....");
	
}*/

}
