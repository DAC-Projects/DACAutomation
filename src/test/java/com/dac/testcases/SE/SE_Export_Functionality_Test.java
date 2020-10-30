package com.dac.testcases.SE;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_SE.SE_Export_Functionality;
import com.dac.main.POM_TPSEE.TPSEE_Groups;

import resources.BaseClass;
import resources.CurrentState;

public class SE_Export_Functionality_Test extends BaseClass{
	Navigationpage np;
	SE_Export_Functionality exp;
	
	@Test(priority= 1, groups = { "smoke" }, description = "Test for navigating to Manage Connections page")
	public void navigateToManageConnections() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToSE_Connections();
		np.navigateToFacebookManagePages();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to Manage Connections Page");
		addEvidence(CurrentState.getDriver(), "Navigate to Manage Connections Page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@Test(priority= 2, groups = { "smoke" }, description = "Test for Renaming Excel")
	public void manageExcel() throws Exception{
		exp = new SE_Export_Functionality(CurrentState.getDriver());
		exp.exportFunctionality();
		//exp.exportConnection();
		exp.UILocationRead();
		exp.UIPagesRead();
		exp.excelRead_UIexcelcomparison();
	//	exp.comparison();
	}

	

}
