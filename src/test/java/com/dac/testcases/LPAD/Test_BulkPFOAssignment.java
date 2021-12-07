package com.dac.testcases.LPAD;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;
import com.dac.main.POM_LPAD.Page_AccountsList;
import com.dac.main.POM_LPAD.Page_BulkPFOAssignment;
import com.dac.main.POM_LPAD.Page_LPADHome;
import com.dac.main.POM_LPAD.Page_LPADLogin;

import resources.BaseClass;
import resources.CurrentState;
import resources.IAutoconst;




public class Test_BulkPFOAssignment extends BaseClass {

	Page_BulkPFOAssignment bulkpfo;
	Page_AccountsList accountsList;
	Page_LPADHome home;
	Page_LPADLogin login;
	WebDriverWait wait;
	String AccountName=IAutoconst.LpadAccountName;
	


@Test(groups = { "Regression" }, description = "TC: Login to LPAD")
	public void TC_NavigateToAccountsPage() throws Exception {
		System.out.println("Step1: Navigate To Accounts Page ");
		login=new Page_LPADLogin(CurrentState.getDriver());
		home=new Page_LPADHome(CurrentState.getDriver());
		wait=new WebDriverWait(CurrentState.getDriver(), 50);
		
		login.LoginTOLPAD();
		System.out.println("wait completes");
		home.create_LPAD_Evidence("Login to LPAD", "Login to LPAD Successfully");
		
		home.switchToDomain();
		home.create_LPAD_Evidence("Switch Domain", "Switch Domain Successfully");
		
		home=new Page_LPADHome(CurrentState.getDriver());
		home.NavigateToAccounts();
		System.out.println("Navigated to Accounts List >> Test Completed.....");
		home.create_LPAD_Evidence("Navigated to Accounts List", "Navigate to Accounts Successfully");
	}
	
@Test(dependsOnMethods="TC_NavigateToAccountsPage", description = "TC: Navigate to Accounts list")
	public void bulkPFOAssignment() {
	accountsList.searchAccount(AccountName);
}
	

}
