package com.dac.testcases.TPSEE;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.dac.main.Navigationpage;
import com.dac.main.POM_TPSEE.TPSEE_Accuracy_Page;
import com.dac.main.POM_TPSEE.TPSEE_Visibility_Page;
import com.selenium.testevidence.SeleniumEvidence;

import resources.BaseClass;
import resources.CurrentState;

public class TPSEE_Accuracy_Test extends BaseClass{
	
	static List<Map<String, String>> export;
	Navigationpage np;
	TPSEE_Accuracy_Page data;

	@SuppressWarnings("unchecked")
	@Test(groups = { "smoke" }, description = "Test for navigating to Visibility page")
	public void navigateToAccuracyPage() throws Exception {
		np = new Navigationpage(CurrentState.getDriver());
		np.navigateTPSEE_Accuracy();
		/*
		 */
		CurrentState.getLogger().log(Status.PASS, "Navigated successfully to TransparenSEE Visibility page");
		addEvidence(CurrentState.getDriver(), "Navigate to Visibility page from Dashboard", "yes");

		// Assert.assertFalse( "sample error", true);
	}
	
	@SuppressWarnings("unchecked")
	@Parameters({ "Filter" })
	@Test(dependsOnMethods = { "navigateToAccuracyPage" }, groups = {
			"smoke" }, description = "Verify Accuracy page loads after filter applied")
	public void verifyFilteringReportsAccuracy(String Filter) throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		String[] filter = Filter.split(",");
		data.applyFilter(filter[0], filter[1], filter[2], filter[3]);
		//data.verify_pageloadCompletely(10);
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Apply Filter from Accuracy page", "yes");
	}

	
	//Test for export and overview report in Accuracy Page
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToAccuracyPage" ,"verifyFilteringReportsAccuracy"}, groups = {
					"smoke" }, description = "Test for overview export and export verification")
		public void verifyOverviewReportnExportVisibility() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		export = data.getExportData();
		data.compareExprttoOvervw(export, data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Verified overview export for Accuracy report", "yes");
	}
			
			
	
	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "navigateToAccuracyPage" , "verifyFilteringReportsAccuracy" }, groups = { "smoke" })
	public void verifyOverviewReportnTooltipAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.compareReportnGraph(data.verifyHistoryGraph(), data.getOverviewReport());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(), "Tooltip values verified from Overview Accuracy report", "yes");
	}
	
	
	@SuppressWarnings("unchecked")
	//@Test(dependsOnMethods = { "navigateToVisibilityPage", "verifyOverviewReportnExportVisibility" }, groups = {
	@Test(dependsOnMethods = { "navigateToAccuracyPage" , "verifyOverviewReportnTooltipAccuracy"}, groups = {
			"smoke" }, description = "Test for verifying tooltips in Accuracy page")
	public void verifySiteTablenExportAccuracy() throws Exception {
		data = new TPSEE_Accuracy_Page(CurrentState.getDriver());
		data.compareExportnTable(data.verifyHistoryGraph(), data.verifyaccuracySitetable());
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 2nd line", null));
		CurrentState.getEvidenceList().add(new SeleniumEvidence("Selenium page 3rd line", null));
		addEvidence(CurrentState.getDriver(),
				"Site level scores in Accuracy site table  and overview Accuracy export found matching", "yes");
	}
	
	
}
