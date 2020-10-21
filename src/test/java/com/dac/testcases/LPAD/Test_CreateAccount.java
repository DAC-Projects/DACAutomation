package com.dac.testcases.LPAD;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.main.POM_LPAD.Page_AccountsList;
import com.dac.main.POM_LPAD.Page_LPADHome;
import com.dac.main.POM_LPAD.Page_LocationBasicInfoTab;
import com.dac.main.POM_LPAD.Page_LocationBusinessInfoTab;
import com.dac.main.POM_LPAD.Page_LocationManageProductsTab;
import com.dac.main.POM_LPAD.Page_LocationNavigationTabList;
import com.dac.main.POM_LPAD.Page_ManageAccount;

import resources.BaseClass;
import resources.CurrentState;


public class Test_CreateAccount extends LaunchLPAD {
	Page_LPADHome home;
	Page_LocationBasicInfoTab basicInfo;
	Page_LocationBusinessInfoTab businessInfo;
	Page_LocationNavigationTabList tabs;
	Page_LocationManageProductsTab products;
	Page_AccountsList accountslist;
	Page_ManageAccount account;
	WebDriverWait wait;  
	public JavascriptExecutor js;
	
	
@Test(dependsOnMethods = "com.dac.testcases.LPAD.Test_LoginToLPAD.TC_Login_LPAD")
public void TC_NavigateToAccountsPage() throws Exception {
	System.out.println("Step1: Navigate To Accounts Page ");
	home=new Page_LPADHome(driver);
	home.NavigateToAccounts();
	System.out.println("Navigated to Accounts List >> Test Completed.....");
//	BaseClass.addEvidence(driver, "Navigated to Accounts Page", "yes");  
}

@Test(dependsOnMethods= {"TC_NavigateToAccountsPage"})
public void TC_CreateAccounts() throws Exception {
	System.out.println("Step2: Click on [Create Account] Button");
	accountslist= new Page_AccountsList(driver);
	wait=new WebDriverWait(driver, 30);
	accountslist.navigateCreateAccount();
	System.out.println("Navigated to Create Account >> Test Completed.....");
//	BaseClass.addEvidence(driver, "Navigated to Create Account Page", "yes");  
}

@Test(dependsOnMethods= {"TC_CreateAccounts"})
public void TC_EnterAccountInfoData() throws Exception {
	System.out.println("Step3: Enter Account Info Tab Data");
	account=new Page_ManageAccount(driver);
	js = (JavascriptExecutor) driver;
	wait=new WebDriverWait(driver, 30);
	account.setAccountData(1);

	System.out.println("Account Info Data Inserted >> Test Completed.....");
//	BaseClass.addEvidence(driver, "Account details inserted", "yes");  
}

@Test(dependsOnMethods= {"TC_EnterAccountInfoData"})
public void TC_SubmitAccountDetails() throws Exception {

	account=new Page_ManageAccount(driver);
	account.submitAccount();
	System.out.println("Account Created >> Test Completed.....");
//	BaseClass.addEvidence(driver, "Account Created...", "yes");  
}

@Test(dependsOnMethods= {"TC_SubmitAccountDetails"})
public void TC_getAccountDetails() throws Exception {
	accountslist= new Page_AccountsList(driver);
	account=new Page_ManageAccount(driver);
	home=new Page_LPADHome(driver);
	
	String acname=account.getAccountName();
	System.out.println("Account name is "+ acname);
	home.NavigateToAccounts();
	accountslist.searchAccount(acname);
	System.out.println("Account Verified >> Test Completed.....");
//	BaseClass.addEvidence(driver, "Account Created...", "yes");  
}
}
