package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Groups;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Groups_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Groups data;
	
	//Navigation to All Groups Page
	@Test(groups = { "smoke" }, description = "Test for navigating to Groups page")
	public void navigateToAllGroupsPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateToAllGroups();
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE All Groups page");
		addEvidence(CurrentState.getDriver(), "Navigate to All Groups page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	//Get data from the table
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToAllGroupsPage"}, groups = {
					"smoke" }, description = "Test to read data from the table")
		public void verifyTableDataoExport() throws Exception {
		data = new TPSEE_Groups(CurrentState.getDriver());
		data.getTableData();
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Verified Table Data", "yes");
	}


}
