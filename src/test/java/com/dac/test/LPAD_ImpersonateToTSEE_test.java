package com.dac.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.dac.POM_LPAD.LPAD_ImpersonateToTSEE_Page;

import resources.BaseClass;



public class LPAD_ImpersonateToTSEE_test extends BaseClass {
	
	LPAD_ImpersonateToTSEE_Page lpadPage;
	
	String 	LoginUser="AutomationResellerAdmin", 
			password="111111", 
			AccountName="SA Test DRS",
			ContactUser="TestAcccountOwner4";
	
@Test
public void TC_Login_LPAD() {
		lpadPage = new LPAD_ImpersonateToTSEE_Page(driver);
		lpadPage.clickLogin(LoginUser,password);
		
		 WebDriverWait wait=new WebDriverWait(driver, 50);
		 WebElement export= driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/section/div/div/div[2]/div/a"));
		 wait.until(ExpectedConditions.visibilityOf(export));
		 System.out.println("wait completes");
}

@SuppressWarnings("unchecked")
@Test(dependsOnMethods = "TC_Login_LPAD")
public void LoadAccountList() throws Exception {
	lpadPage = new LPAD_ImpersonateToTSEE_Page(driver);
	lpadPage.NavigateToAccounts();
	

}

@SuppressWarnings("unchecked")
@Test(dependsOnMethods = { "LoadAccountList"}, groups = {"smoke" }, description = "TC: Account Contact List Loaded")
public void LoadAccountContactList() throws Exception {
	lpadPage = new LPAD_ImpersonateToTSEE_Page(driver);
	lpadPage.loadAccountContactList(AccountName);

}

@SuppressWarnings("unchecked")
@Test(dependsOnMethods = { "LoadAccountContactList"}, groups = {"smoke" }, description = "TC: Impersonate to TSEE")
public void ImpersonateToTSEE() throws Exception {
	
	lpadPage = new LPAD_ImpersonateToTSEE_Page(driver);
	lpadPage.ImpersonateUser(ContactUser);

}

}
