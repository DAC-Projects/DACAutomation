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
import com.dac.main.POM_LPAD.Page_ManageAccount;

import resources.BaseClass;
import resources.CurrentState;


public class Test_CreateAccount extends BaseClass {
	Page_LPADHome home;
	Page_LocationBasicInfoTab basicInfo;
	Page_LocationBusinessInfoTab businessInfo;
	Page_LocationNavigationTabList tabs;
	Page_LocationManageProductsTab products;
	Page_AccountsList accountslist;
	Page_ManageAccount account;
	WebDriverWait wait;  
	public JavascriptExecutor js;
	

	
@Test()
public void TC_Login_LPAD() throws Exception {
	Page_LPADLogin loginPage=new Page_LPADLogin(CurrentState.getDriver());
	Page_LPADHome home=new Page_LPADHome(CurrentState.getDriver());
	WebDriverWait wait=new WebDriverWait(CurrentState.getDriver(), 50);
		  
	loginPage.LoginTOLPAD();
		  
//	String pageTitle= loginPage.getTitle();
	System.out.println("wait completes");
	home.switchToDomain();
		 
//	TakesScreenshot
	CurrentState.getLogger().log(Status.PASS, "Login to LPAD Successfully");
	addEvidence(CurrentState.getDriver(), "Login to LPAD", "yes");
}

@Test(dependsOnMethods = "TC_Login_LPAD")
public void TC_NavigateToAccountsPage() throws Exception {
	System.out.println("Step1: Navigate To Accounts Page ");
	home=new Page_LPADHome(CurrentState.getDriver());
	home.NavigateToAccounts();
	System.out.println("Navigated to Accounts List >> Test Completed.....");
	addEvidence(CurrentState.getDriver(), "Navigated to Accounts Page", "yes");  
}

@Test(dependsOnMethods= {"TC_NavigateToAccountsPage"})
public void TC_CreateAccounts() throws Exception {
	System.out.println("Step2: Click on [Create Account] Button");
	accountslist= new Page_AccountsList(CurrentState.getDriver());
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	accountslist.navigateCreateAccount();
	System.out.println("Navigated to Create Account >> Test Completed.....");
	addEvidence(CurrentState.getDriver(), "Navigated to Create Account Page", "yes");  
}

@Test(dependsOnMethods= {"TC_CreateAccounts"})
public void TC_EnterAccountInfoData() throws Exception {
	System.out.println("Step3: Enter Account Info Tab Data");
	account=new Page_ManageAccount(CurrentState.getDriver());
	js = (JavascriptExecutor) CurrentState.getDriver();
	wait=new WebDriverWait(CurrentState.getDriver(), 30);
	account.setAccountData(1);

	System.out.println("Account Info Data Inserted >> Test Completed.....");
	addEvidence(CurrentState.getDriver(), "Account details inserted", "yes");  
}

@Test(dependsOnMethods= {"TC_EnterAccountInfoData"})
public void TC_SubmitAccountDetails() throws Exception {

	account=new Page_ManageAccount(CurrentState.getDriver());
	account.submitAccount();
	System.out.println("Account Created >> Test Completed.....");
	addEvidence(CurrentState.getDriver(), "Account Created...", "yes");  
}

@Test(dependsOnMethods= {"TC_SubmitAccountDetails"})
public void TC_getAccountDetails() throws Exception {
	accountslist= new Page_AccountsList(CurrentState.getDriver());
	account=new Page_ManageAccount(CurrentState.getDriver());
	home=new Page_LPADHome(CurrentState.getDriver());
	
	String acname=account.getAccountName();
	System.out.println("Account name is "+ acname);
	home.NavigateToAccounts();
	accountslist.searchAccount(acname);
	System.out.println("Account Verified >> Test Completed.....");
	addEvidence(CurrentState.getDriver(), "Account Created...", "yes");  
}
}
